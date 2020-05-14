package com.digitalacademy.customer.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//Create Setter & Getter by use lombok plugin
//@Setter
//@Getter
@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100, message = "Please type your first name size between 1 - 100")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 100, message = "Please type your last name size between 1 - 100")
    private String lastName;

    @Email(message = "Please type valid email")
    private String email;

    @NotNull
    private String phone;
    private Integer age;
}
