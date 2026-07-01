package com.project.banking_api.src.infra.models;


import com.project.banking_api.src.enums.Roles;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToOne(mappedBy = "user")
    private BankAccountModel bankAccountModel;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public BankAccountModel getBankAccountModel() {
        return bankAccountModel;
    }

    public void setBankAccountModel(BankAccountModel bankAccountModel) {
        this.bankAccountModel = bankAccountModel;
    }
}
