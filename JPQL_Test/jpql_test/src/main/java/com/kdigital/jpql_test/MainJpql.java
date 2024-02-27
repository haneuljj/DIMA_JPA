package com.kdigital.jpql_test;

import java.util.Iterator;
import java.util.List;

import com.kdigital.jpql_test.entity.Member;
import com.kdigital.jpql_test.util.ConnectionManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class MainJpql {
    public static void main(String[] args) {
        // 1) 멤버를 여러명 저장하기
        for(int i=1; i<=50; ++i) {
            insertMember(i);
        }

        // 2) 유저아이디가 15번인 데이터 조회하기
        //findById(15L); // select * from test_user where userid = 15;

        // 3) 유저아이디가 50번인 회원 삭제
        // deleteMember(50L);

        // 4) 5번 회원의 데이터 수정
        // Member member = new Member("Kim", "pwd_kim", "kim@naver.com");
        // member.setUserid(5L);
        // updateMember(member);

        // JPQL(Java Persistence Query Language) : 전체 조회
        // selectAllMember();

        // JPQL(Java Persistence Query Language) : 1명만 조회
        // findById();
        
    }

    
    // JPQL : 전체 회원 명단 조회하는 메소드
    private static void selectAllMember() {
        System.out.println("--------------- select All Member");

        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            String query = "select m from Member"; // 실제 테이블명이 아닌 entity클래스명 사용, 대소문자지켜서
            TypedQuery<Member> tq = manager.createQuery(query, Member.class); // 쿼리문, 레코드 타입
            List<Member> members = tq.getResultList(); // 각 레코드를 리스트로 가져오기

            // iterator로 변환
            Iterator<Member> iter = members.iterator();
            while(iter.hasNext()) System.out.println(iter.next());

            tx.commit();
        } catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }

    }

    // 회원을 저장하는 메소드
    private static void insertMember(int i) {
        System.out.println("--------------- insert Member");
        
        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            Member member = new Member("name_"+i, "pwd_"+i, "email_"+i+"@naver.com");
            manager.persist(member);

            tx.commit();
            System.out.println("커밋완료");
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            manager.close();
        }
    }

    // JPQL : 한 명 조회하는 메소드
    private static void findById() {
        System.out.println("--------------- find Member by ID(JPQL)");
        
        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        Long uid = 15L;
        try {
            tx.begin();

            String query = "select m from Member m where m.userid = :uid";

            // Code Chaining 
            Member myMember = manager.createQuery(query, Member.class)
                .setParameter("uid", uid)
                .getSingleResult();

            // TypedQuery<Member> member = manager.createQuery(query, Member.class);
            // member.setParameter("uid", uid); // placeholder 자리에 값 넣기
            // Member myMember = member.getSingleResult();


            System.out.println(myMember);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    // 회원 1명 조회하는 메소드 
    private static void findById(long userid) {
        System.out.println("--------------- find Member by ID");
        
        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            Member member = manager.find(Member.class, userid);
            System.out.println(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    // 회원 삭제하는 메소드
    private static void deleteMember(long userid) {
        System.out.println("--------------- delete Member");
        
        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            Member member = manager.find(Member.class, userid);
            if(member == null) System.out.println("## 존재하지 않는 사용자");
            else {
                System.out.println(member);
                manager.remove(member);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    // 회원 정보 수정하는 메소드 
    private static void updateMember(Member member) {
        System.out.println("--------------- update Member");
        
        EntityManager manager = ConnectionManager.getManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();
            
            
            Member m = manager.find(Member.class, member.getUserid());
            if(m == null) System.out.println("## 존재하지 않는 사용자");
            else {
                m.setUsername(member.getUsername());
                m.setPwd(member.getPwd());
                m.setEmail(member.getEmail());

                System.out.println(m);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

}
