package com.kdigital.jpa02;

import java.time.LocalDateTime;

import com.kdigital.jpa02.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MainJPA {
    public static void main(String[] args){
        // 1) persistence.xml의 <persistence-unit name="persistence unit명 ">값을 읽어 EntityManagerFactory생성
        // DB 연동을 위한 설정값을 읽어옴
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpastudy");

        // 2) EntityManagerFactory 객체를 통해 EntityManager 생성 (DB연동 처리)
        EntityManager manager = factory.createEntityManager();

        // 3) EntityManager 객체를 통해 EntityTransaction 객체 생성
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin(); // 트랜잭션 시작

            User user = new User("bbb@bbb.com", "홍길등", LocalDateTime.now()); // 매개변수 값 넣어 user 객체 생성
            manager.persist(user); // 데이터 객체를 저장하는 메서드, insert하는 명령

            tx.commit(); // 오류없이 잘 처리되어야 커밋가능

            System.out.println("저장완료!");
        } catch (Exception e) {
            tx.rollback(); // 트랜젝션 실행 중 오류나면 롤백
        } finally {
            manager.close();
        }

        // 리소스 정리
        factory.close();
    }
}
