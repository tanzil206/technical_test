package com.example.application.controller;


import org.json.JSONArray;
import org.json.JSONObject;
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
import com.example.application.service.IFactoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;


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


	@RequestMapping(method = RequestMethod.GET, value = "/products")
	public JsonNode  productSearch(HttpServletRequest httpRequest, @RequestParam("jan") String jan) throws JsonMappingException, JsonProcessingException  {



		JSONObject searchproduct = new JSONObject();

		Product product = productService.getProductByJan(jan);

		searchproduct.put("jan", jan);
		searchproduct.put("product_name", product.getProduct_name());
		searchproduct.put("maker", product.getMaker());
		searchproduct.put("brand", product.getBrand());

		searchproduct.put("attributes", attrsService.getAttrsByProduct(product));
		searchproduct.put("tags_from_description", serviceFactory.getProduct(Keyword.DESCRIPTION).getProduct(product));
		searchproduct.put("tags_from_review", serviceFactory.getProduct(Keyword.REVIEW).getProduct(product));

		return mapper.readTree(searchproduct.toString());

	}

}
