package com.example.application.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.application.model.Review;
import com.example.application.model.Product;

@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {

	public Review findById(long id);

	public Stream<Review> findAll();

	public Stream<Review> findByProduct(Product product);

	@Query(value = "select count(*)as num FROM review a WHERE a.value is null", nativeQuery = true)
	public long findByValue();

}
