package com.calebe.datafetching.eager;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
public class EagerCompany {
    @Id
    private int id;
    private String name;
    private String founderName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company")
    private Set<EagerProduct> products = new HashSet<>();

    public EagerCompany() {
    }

    public EagerCompany(int id, String name, Set<EagerProduct> products) {
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

    public Set<EagerProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<EagerProduct> products) {
        this.products = products;
    }

    public String getFounderName() {
        return founderName;
    }

    public void setFounderName(String founderName) {
        this.founderName = founderName;
    }
}
