package com.example.application.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.application.model.Product;
import com.example.application.model.Attrs;
import com.example.application.model.Desc;
import com.example.application.model.Review;
import com.example.application.repository.AttrsRepository;
import com.example.application.repository.DescRepository;
import com.example.application.repository.ProductRepository;
import com.example.application.repository.ReviewRepository;

@Component
public class DataEntry {

	@Autowired
	DescRepository descRepository;
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	AttrsRepository attrsRepository;

//	@Transactional
//	public static void main(String[] args) throws IOException, ParseException {
//
//		DataEntry dt = new DataEntry();
//		dt.dataEntry();
//
//	}
	public DataEntry(ProductRepository productRepository, AttrsRepository attrsRepository,
			DescRepository descRepository, ReviewRepository reviewRepository) {
		super();
		this.productRepository = productRepository;
		this.attrsRepository = attrsRepository;
		this.descRepository = descRepository;
		this.reviewRepository = reviewRepository;
	}

	public void dataSave() throws IOException, ParseException, NullPointerException {

		ArrayList<JSONObject> jsons = ReadJSON(new File("D:/sample.jsonl"), "UTF-8");

		for (int js = 0; js < jsons.size(); js++) {
			JSONObject obj = jsons.get(js);
			Product product = new Product();
			product.setJan(obj.get("jan").toString());

			try {
				product.setProduct_name(obj.get("product_name").toString());
				// if(obj.get("maker").toString().) {
				product.setMaker(obj.get("maker").toString());
				// }
				// if(obj.get("brand").toString().equals(null)) {
				product.setBrand(obj.get("brand").toString());
				// }

			} catch (NullPointerException e) {

			}
			Product p = productRepository.save(product);
			try {
				JSONArray jsonArray = (JSONArray) obj.get("attributes");
				for (int t = 0; t < jsonArray.size(); t++) {
					JSONObject attobj = (JSONObject) jsonArray.get(t);
					Attrs desc = new Attrs();
					desc.setProduct(p);
					String valueBdy = attobj.get("values").toString();
					if (valueBdy.contains(",")) {
						String[] valueBody = attobj.get("values").toString().split(",");
						for (int v = 0; v < valueBody.length; v++) {
							{
								desc.setAttrsKey(attobj.get("key").toString());
								desc.setValue(valueBody[v].toString());
							}

							attrsRepository.save(desc);
						}
					} else {
						desc.setAttrsKey(attobj.get("key").toString());
						desc.setValue(valueBdy);
						attrsRepository.save(desc);
					}
				}
			} catch (NullPointerException e) {
				Attrs desc = new Attrs();
				desc.setProduct(p);
				desc.setAttrsKey(null);
				desc.setValue(null);
				attrsRepository.save(desc);
			}
			try {
				String desBdy = obj.get("tags_from_description").toString();
				if (desBdy.contains(",")) {
					String[] hourBody = obj.get("tags_from_description").toString().split(",");
					for (int d = 0; d < hourBody.length; d++) {
						Desc dsc = new Desc();
						dsc.setProduct(p);
						dsc.setValue(hourBody[d]);
						descRepository.save(dsc);
					}
				} else {
					Desc dsc = new Desc();
					dsc.setProduct(p);
					dsc.setValue(desBdy);
					descRepository.save(dsc);
				}
			} catch (NullPointerException e) {
				Desc dsc = new Desc();
				dsc.setProduct(p);
				dsc.setValue(null);
				descRepository.save(dsc);
			}
			try {
				String rvBdy = obj.get("tags_from_review").toString();
				if (rvBdy.contains(",")) {
					String[] rv = obj.get("tags_from_review").toString().split(",");
					for (int r = 0; r < rv.length; r++) {
						Review rev = new Review();
						rev.setProduct(p);
						rev.setValue(rv[r]);
						reviewRepository.save(rev);
					}
				} else {
					Review rev = new Review();
					rev.setProduct(p);
					rev.setValue(rvBdy);
					reviewRepository.save(rev);
				}
			} catch (NullPointerException e) {
				Review rev = new Review();
				rev.setProduct(p);
				rev.setValue(null);
				reviewRepository.save(rev);
			}
		}
	}

	public static synchronized ArrayList<JSONObject> ReadJSON(File MyFile, String Encoding)
			throws FileNotFoundException, ParseException {
		Scanner scn = new Scanner(MyFile, Encoding);
		ArrayList<JSONObject> json = new ArrayList<JSONObject>();

		while (scn.hasNext()) {
			JSONObject obj = (JSONObject) new JSONParser().parse(scn.nextLine());
			json.add(obj);
		}

		return json;
	}

}
