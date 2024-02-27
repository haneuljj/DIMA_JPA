package com.kdigital.jpa03;

import java.time.LocalDate;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import com.kdigital.jpa03.entity.Member;

@SpringBootApplication
@EntityScan( basePackages = {"com.kdigital.jpa03.entity"} )
public class MemberInsert {

	public static void main(String[] args) {
		// 1) persistence.xml의 <persistence-unit name="persistence unit명 ">값을 읽어 EntityManagerFactory생성
        // DB 연동을 위한 설정값을 읽어옴
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpastudy");

        // 2) EntityManagerFactory 객체를 통해 EntityManager 생성 (DB연동 처리)
        EntityManager manager = factory.createEntityManager();

        // 3) EntityManager 객체를 통해 EntityTransaction 객체 생성
        EntityTransaction tx = manager.getTransaction();

		try {
			tx.begin();

			for(int i=1; i<=10; ++i) {
				Member member = new Member("f"+i+"@naver.com", "이름: "+i, LocalDate.of(1999,01,20), 26);
				manager.persist(member);
			}
			
			tx.commit();
			System.out.println("저장 완료 !");
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			manager.close();
		}
		factory.close();
	}

}
