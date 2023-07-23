package com.example.application.factory.service;

import org.springframework.stereotype.Service;

import com.example.application.service.IFactoryService;

@Service
public interface AbstractServiceFactory {

	public IFactoryService getProduct(String modelType);

	public IFactoryService getRate(String modelType);

}
