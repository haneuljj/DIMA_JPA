package com.kdigital.jpa04.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionManager {
    // 항상 쓰는 factory를 웹으로 빼놓기
    private static EntityManagerFactory factory;

    // static 생성자
    static {
        // factory로 manager 가져오기
        factory = Persistence.createEntityManagerFactory("jpastudy");
    }

    public static EntityManager getManager() {
        return factory.createEntityManager();
    }

    // factory 닫기
    public static void close() {
        factory.close();
    }
}   
