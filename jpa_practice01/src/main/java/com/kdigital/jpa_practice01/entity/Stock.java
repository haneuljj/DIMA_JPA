package com.kdigital.jpa_practice01.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name="stocks")
public class Stock {
    @SequenceGenerator(
        name = "pro_id_gen",
        sequenceName = "stocks_seq",
        initialValue = 1,
        allocationSize = 1
    )

    @Id
    @Column(name="product_id")
    @GeneratedValue(generator = "pro_id_gen")
    private Long productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="price")
    private int price;

    @Column(name="inventory_amount")
    private int inventoryAmount;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Stock(String productName, int price, int inventoryAmount, Category category){
        super();
        this.productName = productName;
        this.price = price;
        this.inventoryAmount = inventoryAmount;
        this.category = category;
    }
}
