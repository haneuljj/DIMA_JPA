package com.kdigital.jpa_practice01.Service;

import java.util.Scanner;

import com.kdigital.jpa_practice01.entity.Category;
import com.kdigital.jpa_practice01.entity.Stock;
import com.kdigital.jpa_practice01.util.ConnectionManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class StockServiceImpl implements StockService{
    Scanner scanner = new Scanner(System.in);
    public StockServiceImpl() {
        String choice;
        while (true) {
            menu();
            choice = scanner.nextLine();

            switch (choice) {
                case "1": insert(); break;
                case "2": selectOne(); break;
                case "3": update(); break;
                case "4": delete(); break;
                case "0":
                    System.out.println("> 프로그램 종료");
                    ConnectionManager.close();
                    return;
            }
        }
    }

    private void menu() {
        System.out.println("===== Stock Management System =====");
        System.out.println("1) 상품 등록");
        System.out.println("2) 상품 조회");
        System.out.println("3) 상품 수정");
        System.out.println("4) 상품 삭제");
        System.out.println("0) 종료");
    }

    @Override
    public void insert() {
        String productName, c;
        int price, amount;

        System.out.println("> 제품명: "); productName = scanner.nextLine();
        System.out.println("> 카테고리: "); c = scanner.nextLine();
        System.out.println("> 가격: "); price = scanner.nextInt();
        System.out.println("> 재고 수량: "); amount = scanner.nextInt();
        scanner.nextLine();

        Category category =  Category.valueOf(c);
        Stock stock = new Stock(productName, price, amount, category);

        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            manager.persist(stock);

            tx.commit();
            System.out.println("> 상품 등록 완료");
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            manager.close();
        }
        
    }

    @Override
    public Stock selectOne() {
        int productId;

        System.out.println("> 제품번호: "); productId = scanner.nextInt();
        scanner.nextLine();

        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            Stock stock = manager.find(Stock.class, productId);
            if (stock == null)
                System.out.println("> 존재하지 않는 제품입니다.");
            else
                System.out.println(stock);

            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            manager.close();
        }
        return null;
    }

    @Override
    public boolean update() {
        int price, plusStock, minusStock;
        int productId;

        System.out.println("> 제품번호: "); productId = scanner.nextInt();
        scanner.nextLine();

        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            Stock stock = manager.find(Stock.class, productId);
            if (stock == null)
                System.out.println("> 존재하지 않는 제품입니다.");

            String choice;
            System.out.println("==== 수정 항목 선택 ====");
            System.out.println("1) 제품 입고량");
            System.out.println("2) 제품 출고량");
            System.out.println("3) 제품 가격");
            System.out.println("> 선택:"); choice = scanner.nextLine();
            
            
            switch (choice) {
                case "1":
                    System.out.println("> 입고량"); plusStock = scanner.nextInt();
                    stock.setInventoryAmount(stock.getInventoryAmount()+plusStock);
                    System.out.println("> 수정 완료 !");
                case "2":
                    System.out.println("> 출고량"); minusStock = scanner.nextInt();
                    stock.setInventoryAmount(stock.getInventoryAmount()-minusStock);
                    System.out.println("> 수정 완료 !");
                case "3":
                    System.out.println("> 가격"); price = scanner.nextInt();
                    stock.setPrice(price);
                    System.out.println("> 수정 완료 !");
            }

            scanner.nextLine();
            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            manager.close();
        }
        return false;
    }

    @Override
    public boolean delete() {
        int productId;

        System.out.println("> 제품번호: "); productId = scanner.nextInt();
        scanner.nextLine();

        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            Stock stock = manager.find(Stock.class, productId);
            if (stock == null)
                System.out.println("> 존재하지 않는 제품입니다.");
            else
                manager.remove(stock);
                System.out.println("> 삭제 완료 ! ");

            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            manager.close();
        }

        return false;
    }

    
    

}
