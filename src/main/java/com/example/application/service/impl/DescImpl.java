package com.example.application.service.impl;

import com.example.application.model.Desc;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import com.example.application.repository.DescRepository;
import com.example.application.service.IFactoryService;
import com.example.application.service.IProductService;
import com.example.application.model.Product;
import org.json.JSONArray;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;
import com.example.application.utility.Util;

@Component
@Primary
public class DescImpl implements IFactoryService {

	@Autowired
	DescRepository descRepository;
	@Autowired
	IProductService productService;

	public DescImpl() {
		super();
	}

	public DescImpl(DescRepository descRepository) {
		super();
		this.descRepository = descRepository;
	}

	public DescImpl(DescRepository descRepository,IProductService productService) {
		super();
		this.descRepository = descRepository;
		this.productService = productService;
	}

	@Override
	@Transactional(readOnly = true)
	public JSONArray getProduct(Product product) {

		Util util = new Util();
		JSONArray searchDesc = new JSONArray();
		try {
			Stream<Desc> streamDescList = descRepository.findByProduct(product);

			ArrayList<Desc> descList = util.getArrayListFromStream(streamDescList);

			for (int i = 0; i < descList.size(); i++) {
				searchDesc.put(descList.get(i).getValue());
			}
		} catch (NullPointerException np) {
			System.out.println(np.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return searchDesc;
	}

	@Override
	public String getFulfillmentRate() {
		String descFulfillmentRate = "";
		try {
			long productSize = productService.getAllProductNumber();

			long nullDesc = descRepository.findByValue();

			descFulfillmentRate = String.format("%2.01f", (((float) ((productSize - nullDesc)) / productSize)) * 100);
		} catch (NullPointerException np) {
			System.out.println(np.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return descFulfillmentRate;
	}

}
