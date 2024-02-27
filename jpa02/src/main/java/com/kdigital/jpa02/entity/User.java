package com.kdigital.jpa02.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// lombok이 생성해주는 생성자, 세터, 개터, 투스티링오버라이딩 메서드
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Entity // DB 테이블과 매핑대상(Entity 단위로 클래스와 DB가 연동된다.)
@Table(name="myuser") // 데이터 베이스의 user테이블과 User 클래스가 매핑됨
public class User {
    @Id // 식별자에 대응
    private String email;
    private String username;
    
    @Column(name="join_date") // 컬럼명과 다를 경우 @Column의 name으로
    private LocalDateTime joinDate;
}