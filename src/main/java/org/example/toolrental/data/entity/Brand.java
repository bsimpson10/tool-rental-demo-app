package org.example.toolrental.data.entity;

import jakarta.persistence.*;

@Entity
public class Brand {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "brand_seq"
    )
    @SequenceGenerator(
            name = "brand_seq",
            allocationSize = 5
    )
    Long id;

    @Column(unique=true)
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
