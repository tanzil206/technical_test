package com.example.application.controller;

import java.io.IOException;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.factory.ProductServiceFactory;
import com.example.application.model.Product;
import com.example.application.service.IAttrsService;
import com.example.application.service.IProductService;
import com.example.application.utility.Keyword;
import com.example.application.utility.Util;
import com.example.application.service.IFactoryService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import com.example.application.utility.DataEntry;

/**
 * Created by Tanzil.
 */
@RestController
public class ProductSearchController {

	@Autowired
	IProductService productService;
	@Autowired
	IAttrsService attrsService;
	@Autowired
	IFactoryService factoryService;

	@Autowired
	ProductServiceFactory serviceFactory;

	@Autowired
	ObjectMapper mapper;
	@Autowired
	Util utility;

	@RequestMapping(method = RequestMethod.GET, value = "/products")
	public JsonNode productSearch(HttpServletRequest httpRequest, @RequestParam("jan") String jan)
			throws IOException, ParseException {

		JSONObject response = new JSONObject();
		JSONObject searchproduct = new JSONObject();
		try {
			Product product = productService.getProductByJan(jan);
			searchproduct.put("jan", jan);
			searchproduct.put("product_name", product.getProduct_name());
			searchproduct.put("maker", product.getMaker());
			searchproduct.put("brand", product.getBrand());

			searchproduct.put("attributes", attrsService.getAttrsByProduct(product));
			searchproduct.put("tags_from_description",
					serviceFactory.getProduct(Keyword.DESCRIPTION).getProduct(product));
			searchproduct.put("tags_from_review", serviceFactory.getProduct(Keyword.REVIEW).getProduct(product));
			response = utility.createResponse(200, Keyword.SUCCESS, searchproduct);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response = utility.createResponseData(500, Keyword.ERROR,Keyword.NOT_FOUND);
		}

		return mapper.readTree(response.toString());

	}

}
