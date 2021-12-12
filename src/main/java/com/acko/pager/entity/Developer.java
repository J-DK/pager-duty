package com.acko.pager.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;
}
