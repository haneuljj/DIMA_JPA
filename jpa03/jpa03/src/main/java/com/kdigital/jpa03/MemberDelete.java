package com.kdigital.jpa03;

//import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import com.kdigital.jpa03.entity.Member;

public class MemberDelete {

	public static void main(String[] args) {
		// 1) xml 파일에서 지정한 설정가져오기
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpastudy");

		// 2) 저장한 객체를 저장할 메모리 공간
		EntityManager manager = factory.createEntityManager();
		// 3) 트랜젝션 객체 
		EntityTransaction tx = manager.getTransaction();

		try {
			tx.begin();

			// 삭제하려고 하는 정보가 있는지 확인후 삭제
			Member member = manager.find(Member.class, "f1@naver.com");

			// 정보가 없으면 삭제 안함
			if(member == null){
				System.out.println("멤버 정보 없음");
			}	else {
				manager.remove(member);
			}
			
			tx.commit();
			System.out.println("삭제 완료 !");
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			manager.close();
		}
		factory.close();
	}

}
