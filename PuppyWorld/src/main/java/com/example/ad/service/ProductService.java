package com.example.ad.service;

import java.util.ArrayList;

import org.springframework.data.repository.query.Param;

import com.example.ad.domain.Product;
import com.example.ad.domain.ProductType;
import com.example.ad.domain.Services;

public interface ProductService {
	
	public void saveProduct(Product product);
	public ArrayList<Product> findAllProducts();
	public Product findProductById(int Id);
	public void deleteProductById(int Id);
	
	public ArrayList<Product> searchProductByKeyword(String keyword);
	public ArrayList<Product> searchProductByKeywordAndProductType(String keyword, String productType);
}
