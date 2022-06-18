package com.example.projectbe.domain.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    BUYER("BUYER"),ADMIN("ADMIN");
    private final String representation;
    UserRole(String representation){
        this.representation = representation;
    }
}
