package com.kdigital.jpa_practice01.Service;

import com.kdigital.jpa_practice01.entity.Stock;

public interface StockService {
    public void insert();
    public boolean update();
    public boolean delete();
    public Stock selectOne();
    
}
