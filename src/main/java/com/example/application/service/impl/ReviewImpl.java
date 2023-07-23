package com.example.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.application.model.Attrs;
import com.example.application.model.Product;
import com.example.application.model.Review;
import com.example.application.repository.ReviewRepository;
import com.example.application.service.IProductService;
import com.example.application.utility.Util;
import com.example.application.service.IFactoryService;

@Component
public class ReviewImpl implements IFactoryService {

	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	IProductService productService;

	public ReviewImpl() {
		super();
	}

	public ReviewImpl(ReviewRepository reviewRepository) {
		super();
		this.reviewRepository = reviewRepository;
	}

	public ReviewImpl(ReviewRepository reviewRepository, IProductService productService) {
		super();
		this.reviewRepository = reviewRepository;
		this.productService = productService;
	}

	/**
	 * get Review by Product.
	 *
	 * 
	 */

	@Override
	@Transactional(readOnly = true)
	public JSONArray getProduct(Product product) {
		Util util = new Util();
		JSONArray searchReview = new JSONArray();
		try {
			Stream<Review> streamReviewList = reviewRepository.findByProduct(product);
			ArrayList<Review> reviewList = util.getArrayListFromStream(streamReviewList);
			for (int i = 0; i < reviewList.size(); i++) {
				searchReview.put(reviewList.get(i).getValue());
			}
		} catch (NullPointerException np) {
			System.out.println(np.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return searchReview;
	}

	@Override
	public String getFulfillmentRate() {

		String reviewFulfillmentRate = "";

		try {

			long productSize = productService.getAllProductNumber();

			long nullReview = reviewRepository.findByValue();

			reviewFulfillmentRate = String.format("%2.01f",
					(((float) ((productSize - nullReview)) / productSize)) * 100);
		} catch (NullPointerException np) {
			System.out.println(np.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return reviewFulfillmentRate;

	}

}
