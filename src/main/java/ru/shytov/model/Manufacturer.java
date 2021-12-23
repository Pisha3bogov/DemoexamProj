package ru.shytov.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false,unique = true)
    private String name;

    @Column(name = "startDate", nullable = false,unique = true)
    private Date startDate;

    @OneToMany(mappedBy = "manufacturer")
    Set<Product> product;

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
