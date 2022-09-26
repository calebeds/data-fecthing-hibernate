package com.calebe.datafetching.lazy;

import com.calebe.datafetching.eager.EagerCompany;

import javax.persistence.*;

@Table(name = "products")
@Entity
public class LazyProduct {
    @Id
    public int id;

    @JoinColumn(name = "company_id")
    @ManyToOne(fetch = FetchType.LAZY)
    public EagerCompany company;
    public int stock;

    public LazyProduct() {
    }

    public LazyProduct(int id, int stock) {
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
