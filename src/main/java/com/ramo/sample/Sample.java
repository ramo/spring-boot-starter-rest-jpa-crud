package com.ramo.sample;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "sample")
public class Sample implements Serializable {
    /**
     *  Serialization version control
     */
    private static final long serialVersionUID = 1L;

    public Sample() {
    }

    public Sample(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "value", nullable = false)
    private String  value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
