package ru.shytov.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "cost",nullable = false)
    private double cost;

    @Column(name = "description")
    private String description;

    @Column(name = "MainImagePath",nullable = false)
    private String mainImagePath;

    @Column(name = "IsActive",nullable = false)
    private int isActive;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "ManufacturerID")
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "product")
    Set<ProductSales> productSales;
}
