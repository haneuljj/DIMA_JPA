package com.kdigital.jpa04.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// lombok이 생성해주는 생성자, 세터, 개터, 투스티링오버라이딩 메서드
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
public class Product {
    @Id
    @Column(name="prod_id")
    @SequenceGenerator( // 시퀀스 객체 발생시키기
        name="product_seq_generator",
        sequenceName = "product_seq", // 오라클의 시퀀스 객체를 이용
        initialValue = 1, // 초기값
        allocationSize = 1 // 중복되지않도록, 기본값
    )
    @GeneratedValue(generator = "product_seq_generator") // 시퀀스 객체를 어디에 적용시킬 것인지, 연동값을 입력
    private Long prodId; 
    @Column(name="prod_name")
    private String prodName;

    // DB -> 0, 사용자 -> 봄 (Converter 코드 필요, 성능상 더 괜찮지만 귀찮음)
    // DB -> 봄, 사용자 -> 봄 (Converter 코드 불필요, 성능상 별로지만 편함)
    // enum 타입 매핑
    @Enumerated(EnumType.STRING) // EnumType.ORDINAL -> 위치값
    private Season season; 

    @Column(name="unit_price")
    private int unitPrice;

    public Product(String prodName, Season season, int unitPrice){
        super();
        this.prodName = prodName;
        this.season = season;
        this.unitPrice = unitPrice;
    }


}
