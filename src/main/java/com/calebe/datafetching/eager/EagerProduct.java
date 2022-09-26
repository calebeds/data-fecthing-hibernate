package com.calebe.datafetching.eager;

import javax.persistence.*;

@Table(name = "products")
@Entity
public class EagerProduct {
    @Id
    public int id;

    @JoinColumn(name = "company_id")
    @ManyToOne(fetch = FetchType.EAGER)
    public EagerCompany company;
    public int stock;

    public EagerProduct() {
    }

    public EagerProduct(int id, int stock) {
        this.id = id;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
