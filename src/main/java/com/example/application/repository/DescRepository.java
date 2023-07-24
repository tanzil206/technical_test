package com.example.application.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.application.model.Desc;
import com.example.application.model.Product;

@Repository
public interface DescRepository extends CrudRepository<Desc, Long> {

	public Desc findById(long id);

	public Stream<Desc> findByProduct(Product product);

	@Query(value = "select count(*)as num FROM description a WHERE a.value is null or a.value ='[]'", nativeQuery = true)
	public long findByValue();
}
