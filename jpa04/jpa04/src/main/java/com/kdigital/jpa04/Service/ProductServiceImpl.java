package com.kdigital.jpa04.Service;

import java.util.Scanner;

import com.kdigital.jpa04.entity.Product;
import com.kdigital.jpa04.entity.Season;
import com.kdigital.jpa04.util.ConnectionManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ProductServiceImpl implements ProductService {
    Scanner keyin = new Scanner(System.in);
    public ProductServiceImpl() {
        String choice;
        while(true) {
            menu();
            choice = keyin.nextLine();
            
            switch(choice) {
                case "1": insert(); break;
                case "2": selectOne(); break;
                case "3": update(); break;
                case "4": delete(); break;
                case "0": 
                    System.out.println("## 종료합니다."); 
                    ConnectionManager.close();
                    return;
            }
        }
    }

    private void menu() {
        System.out.println(" ### 메뉴 ###");
        System.out.print("1) 입력   2) 조회   3) 수정   4) 삭제   0) 종료 : ");
    }

    @Override
    public void insert() {
        String productName, s;
        int price;

        System.out.print("> 제품명: "); productName = keyin.nextLine();
        System.out.print("> 계  절: "); s = keyin.nextLine();
        System.out.print("> 가  격: "); price = keyin.nextInt();
        keyin.nextLine();

        // 입력받은 문자열 타입을 Season enum타입으로 받기
        Season season = Season.valueOf(s);
        Product product = new Product(productName, season, price);

        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            manager.persist(product);

            tx.commit();
            System.out.println("저장완료");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            manager.close();
        }

    }

    @Override
    public Product selectOne() {
        int productId;
        System.out.print("> 제품번호: "); productId = keyin.nextInt();
        keyin.nextLine();

        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            Product product = manager.find(Product.class, productId);
            if (product == null)
                System.out.println("## 제품이 존재하지 않습니다 ! ");
            else
                System.out.println(product);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            manager.close();
        }

        return null;
    }

    @Override
    public boolean update() {
        String productName, s;
        int productId, price;

        System.out.print("> 제품번호: "); productId = keyin.nextInt();
        keyin.nextLine();

        keyin.nextLine();

        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            Product product = manager.find(Product.class, productId);
            if (product == null)
                System.out.println("## 제품이 존재하지 않습니다 ! ");
            else {
                System.out.println("## 수정할 정보를 입력하세요 ! ");
                System.out.print("> 제품명: "); productName = keyin.nextLine();
                System.out.print("> 계  절: "); s = keyin.nextLine();
                System.out.print("> 가  격: "); price = keyin.nextInt();

                // 입력받은 문자열을 enum타입으로 바꾸기
                Season season = Season.valueOf(s);

                product.setProdName(productName);
                product.setSeason(season);
                product.setUnitPrice(price);

                System.out.println("변경 완료 !");
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            manager.close();
        }
        return false;
    }

    @Override
    public boolean delete() {
        int productId;

        System.out.print("> 제품번호: "); productId = keyin.nextInt();
        keyin.nextLine();

        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            Product product = manager.find(Product.class, productId);
            if (product == null)
                System.out.println("## 제품이 존재하지 않습니다 ! ");
            else
                manager.remove(product);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            manager.close();
        }

        return false;
    }
    
}
