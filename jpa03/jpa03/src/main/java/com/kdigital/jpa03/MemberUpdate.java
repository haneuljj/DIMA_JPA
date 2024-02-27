package com.kdigital.jpa03;

//import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

import com.kdigital.jpa03.entity.Member;

public class MemberUpdate {

	public static void main(String[] args) {
		// 1) xml 파일에서 지정한 설정가져오기
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpastudy");

		// 2) 저장한 객체를 저장할 메모리 공간
		EntityManager manager = factory.createEntityManager();
		// 3) 트랜젝션 객체 
		EntityTransaction tx = manager.getTransaction();

		try {
			tx.begin();

			// 수정하려고하는 정보가 있는지 확인
			Member member = manager.find(Member.class, "f9@naver.com");
	
			if(member == null){
				System.out.println("멤버 정보 없음");
			}	else {
				// settter로 직접 정보 수정 -> DB정보까지 모두 직접 변경시켜서 사용 주의 필요
				member.setUsername("전우치");
				member.setBirthday(LocalDate.of(1998, 01, 05));
				member.setAge(27);
				System.out.println("변경 완료 !");
			}
			
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			manager.close();
		}
		factory.close();
	}

}
