package com.example.application.utility;

import java.util.ArrayList;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.*;
import org.springframework.stereotype.Component;

@Component
public class Util {

	public static <T> ArrayList<T> getArrayListFromStream(Stream<T> stream) {

		ArrayList<T> arrayList = stream.collect(Collectors.toCollection(ArrayList::new));

		return arrayList;
	}

	public JSONObject createResponse(long code, String message, JSONObject jsonobject) {
		JSONObject response = new JSONObject();
		response.put("code", code);
		response.put("message", message);
		response.put("data", jsonobject);

		return response;
	}

	public JSONObject createResponseData(long code, String message, String data) {
		JSONObject response = new JSONObject();
		response.put("code", code);
		response.put("message", message);
		response.put("data", data);
		return response;
	}

}
