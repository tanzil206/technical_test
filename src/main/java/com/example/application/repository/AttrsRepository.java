package com.example.application.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.application.model.Attrs;
import com.example.application.model.Product;

@Repository
public interface AttrsRepository extends PagingAndSortingRepository<Attrs, Long> {

	public Attrs findById(long id);

	public Stream<Attrs> findAll();

	public Stream<Attrs> findByProduct(Product product);

	@Query(value = "select count(*)as num FROM attrs a WHERE a.attrs_key is null", nativeQuery = true)
	public long findByKey();

}