package com.example.testjpa.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_resource")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
