    package com.kdigital.jpa04.Service;

import com.kdigital.jpa04.entity.Product;

public interface ProductService {
    // insert, update, delete, selectOne
    public void insert();
    public boolean update();
    public boolean delete();
    public Product selectOne();
}
