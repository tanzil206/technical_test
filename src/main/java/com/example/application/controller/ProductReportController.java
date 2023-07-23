package com.example.application.controller;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.application.factory.ProductServiceFactory;
import com.example.application.service.IAttrsService;
import com.example.application.repository.*;
import com.example.application.utility.Keyword;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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

	@RequestMapping(method = RequestMethod.GET, value = "/reports")
	public JsonNode productSearch(HttpServletRequest httpRequest) throws JsonMappingException, JsonProcessingException {

		JSONObject searchproduct = new JSONObject();

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

		return mapper.readTree(searchproduct.toString());

	}

}