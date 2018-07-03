package com.kyrosoft.inventory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ApplicationUser")
@Getter
@Setter
@NoArgsConstructor
public class User extends IdentifiableEntity {
    private String username;
    private String password;
}