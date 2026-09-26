package com.hecticus.gpaapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 16, nullable = false)
    private String name;

    @Column(name = "identifier", length = 16, nullable = false)
    private String identifier;

    @Column(name = "sms", length = 150, nullable = false)
    private String sms;

    @Column(name = "short_code")
    private int shortCode;

    @Column(name = "product_identifier")
    private String productIdentifier;

    @Column(name = "descripcion_producto")
    private String descripcionProducto;

    public Services() {
    }

    public Services(String name, String identifier, String sms, int shortCode, String productIdentifier, String descripcionProducto) {
        this.name = name;
        this.identifier = identifier;
        this.sms = sms;
        this.shortCode = shortCode;
        this.productIdentifier = productIdentifier;
        this.descripcionProducto = descripcionProducto;
    }

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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public int getShortCode() {
        return shortCode;
    }

    public void setShortCode(int shortCode) {
        this.shortCode = shortCode;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }
}
