package com.example.application.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.application.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

	public Product findById(long id);

	@Query(value = "select count(*)as num FROM product p", nativeQuery = true)
	public long findAllByNum();
	

	public Product findByJan(String jan);

	@Query(value = "select avg( p.product_name is not null) * 100 FROM product p", nativeQuery = true)
	public long findByProduct_name();

	@Query(value = "select avg( p.maker is not null) * 100 FROM product p", nativeQuery = true)
	public long findByMaker();

	@Query(value = "select avg( p.brand is not null) * 100 FROM product p", nativeQuery = true)
	public long findByBrand();

}
