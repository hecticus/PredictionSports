package com.hecticus.gpaapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente_appland")
public class ClienteAppland {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "msisdn", nullable = false)
    public String msisdn;

    @Column(name = "identifier", nullable = false)
    public String identifier;

    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "status", nullable = false)
    public long status;
}
