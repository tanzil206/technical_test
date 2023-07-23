package com.example.application.service;


import org.springframework.stereotype.Service;
import com.example.application.model.Product;
import org.json.JSONArray;

@Service
public interface IFactoryService {

	public JSONArray getProduct(Product product);

	public String getFulfillmentRate();

}
