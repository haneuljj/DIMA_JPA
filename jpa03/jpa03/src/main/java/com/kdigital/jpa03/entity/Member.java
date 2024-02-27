package com.kdigital.jpa03.entity;

import java.time.LocalDate;

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

@Entity
@Table(name="members")
public class Member {
    @Id
    private String email;
    private String username;
    private LocalDate birthday;
    private int age;

}
