package com.example.application.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.example.application.model.Product;


@Service
public interface IProductService {

	public long getAllProductNumber();

	public Product getProductByJan(String jan);

}
