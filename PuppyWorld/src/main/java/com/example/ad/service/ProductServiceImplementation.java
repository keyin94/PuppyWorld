package com.example.ad.service;

import java.util.ArrayList;
import java.util.Iterator;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;



import com.example.ad.domain.Product;
import com.example.ad.domain.ProductType;
import com.example.ad.domain.Services;
import com.example.ad.repo.ProductRepository;

@Service
@Transactional
public class ProductServiceImplementation implements ProductService {
	
	@Autowired
	ProductRepository prepo;

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		prepo.save(product);
	}

	@Override
	public ArrayList<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return (ArrayList<Product>) prepo.findAll();
	}

	@Override
	public Product findProductById(int Id) {
		// TODO Auto-generated method stub
		ArrayList<Product> pList = findAllProducts();
		Product searchProduct = null;
		for (Iterator <Product>iterator = pList.iterator(); iterator.hasNext();) {
			Product product = iterator.next();
			if(product.getProductId() == Id) {
				searchProduct = product;
			}
		}
		return searchProduct;
	}

	@Override
	public void deleteProductById(int Id) {
		// TODO Auto-generated method stub
		Product searchProduct = findProductById(Id);
		prepo.delete(searchProduct);
	}

	@Override
	public ArrayList<Product> searchProductByKeyword(String keyword) {
		if(keyword!=null) {
			return prepo.searchProduct(keyword);
		}
		return (ArrayList<Product>) prepo.findAll();
	}

	@Override
	public ArrayList<Product> searchProductByKeywordAndProductType(String keyword, String productType) {
		// TODO Auto-generated method stub
		if (keyword != null) {
			return prepo.searchProductByType(keyword, productType);
		}
		
		return (ArrayList<Product>) prepo.findAll();
	}

	/*
	 * @Override public ArrayList<Product> findByProductType(ProductType
	 * productType) { // TODO Auto-generated method stub if (productType != null) {
	 * return prepo.findByProductType(productType); } return (ArrayList<Product>)
	 * prepo.findAll(); }
	 */

}
