package com.example.application.service;


import org.springframework.stereotype.Service;
import com.example.application.model.Product;

import org.json.JSONArray;
import org.json.JSONObject;

@Service
public interface IAttrsService {

	public JSONArray getAttrsByProduct(Product product);

	public String getAttrsFulfillmentRate();

}
