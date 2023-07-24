package com.example.application.controller;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.application.factory.ProductServiceFactory;
import com.example.application.service.IAttrsService;
import com.example.application.repository.*;
import com.example.application.utility.DataEntry;
import com.example.application.utility.Keyword;
import com.example.application.utility.Util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Created by Tanzil.
 */
@RestController
public class ProductReportController {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	IAttrsService attrsService;
	@Autowired
	ObjectMapper mapper;
	@Autowired
	ProductServiceFactory serviceFactory;
	@Autowired
	Util utility;

	@RequestMapping(method = RequestMethod.GET, value = "/reports")
	public JsonNode productSearch(HttpServletRequest httpRequest) throws IOException, ParseException {

		JSONObject response = new JSONObject();

		JSONObject searchproduct = new JSONObject();
		try {
			float product = productRepository.findByProduct_name();

			float maker = productRepository.findByMaker();

			float brand = productRepository.findByBrand();

			String attrs = attrsService.getAttrsFulfillmentRate();

			searchproduct.put("product_name", String.valueOf(product) + "%");
			searchproduct.put("maker", String.valueOf(maker) + "%");
			searchproduct.put("brand", String.valueOf(brand) + "%");

			searchproduct.put("attributes", attrs + "%");
			searchproduct.put("tags_from_description",
					serviceFactory.getRate(Keyword.DESCRIPTION).getFulfillmentRate() + "%");
			searchproduct.put("tags_from_review", serviceFactory.getRate(Keyword.REVIEW).getFulfillmentRate() + "%");
			response = utility.createResponse(200, Keyword.SUCCESS, searchproduct);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			response = utility.createResponseData(500, Keyword.ERROR,Keyword.NOT_FOUND);
		}
		return mapper.readTree(response.toString());

	}

}
