package com.hecticus.gpaapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alta")
public class Alta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "modo")
    private String modo;

    @Column(name = "clickid")
    private String clickid;

    @Column(name = "pid")
    private String pid;

    @Column(name = "msisdn")
    private String msisdn;

    public Alta() {
    }

    public Alta(String modo, String clickid, String pid, String msisdn) {
        this.modo = modo;
        this.clickid = clickid;
        this.pid = pid;
        this.msisdn = msisdn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClickid() {
        return clickid;
    }

    public void setClickid(String clickid) {
        this.clickid = clickid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    @Override
    public String toString() {
        return "Alta{" +
                "id=" + id +
                ", modo='" + modo + '\'' +
                ", clickid='" + clickid + '\'' +
                ", pid='" + pid + '\'' +
                ", msisdn='" + msisdn + '\'' +
                '}';
    }
}
