package com.nda.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name ="Employee_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name ="Employee_Name")
    @NotEmpty
    private String name;
    @Id
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;
    @NotEmpty
    private String mobile;
    @NotEmpty
    private String DOB;


}
