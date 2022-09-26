package com.calebe.datafetching.lazy;

import com.calebe.datafetching.eager.EagerProduct;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
public class LazyCompany {
    @Id
    private int id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private Set<LazyProduct> products = new HashSet<>();

    public LazyCompany() {
    }

    public LazyCompany(int id, String name, Set<LazyProduct> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LazyProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<LazyProduct> products) {
        this.products = products;
    }
}
