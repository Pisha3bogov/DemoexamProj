package ru.shytov.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "product_sales")
public class ProductSales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sale_date",nullable = false)
    private Date saleDate;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "ProductID")
    private Product product;
}
