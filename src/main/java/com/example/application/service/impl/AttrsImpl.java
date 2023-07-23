package com.example.application.service.impl;

import com.example.application.model.Attrs;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.application.service.IAttrsService;
import com.example.application.utility.Util;
import com.example.application.repository.AttrsRepository;
import com.example.application.model.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AttrsImpl implements IAttrsService {

	@Autowired
	AttrsRepository attrsRepository;

	@Autowired
	ProductImpl productService;

	/**
	 * get Attrs by Product.
	 *
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public JSONArray getAttrsByProduct(Product product) {
		Util util = new Util();
		JSONArray searchAttr = new JSONArray();
		JSONObject searchAttrObject = new JSONObject();
		try {
			Stream<Attrs> streamAttrstrsList = attrsRepository.findByProduct(product);

			ArrayList<Attrs> attrsList = util.getArrayListFromStream(streamAttrstrsList);
			

			for (int i = 0; i <attrsList.size(); i++) {
				searchAttrObject.put("key", attrsList.get(i).getAttrsKey());
				for (int v = 0; v <attrsList.size(); v++) {
					if (attrsList.get(v).getAttrsKey().equalsIgnoreCase(attrsList.get(i).getAttrsKey()))
						searchAttrObject.put("value",attrsList.get(v).getValue());

				}
				searchAttr.put(searchAttrObject);
			}
		} catch (NullPointerException np) {
			System.out.println(np.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return searchAttr;
	}

	@Override
	public String getAttrsFulfillmentRate() {

		String attrsFulfillmentRate = "";
		try {
			long productSize = productService.getAllProductNumber();

			long nullAtrrs = attrsRepository.findByKey();

			attrsFulfillmentRate = String.format("%2.01f", (((float) ((productSize - nullAtrrs)) / productSize)) * 100);
		} catch (NullPointerException np) {
			System.out.println(np.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return attrsFulfillmentRate;
	}

}
