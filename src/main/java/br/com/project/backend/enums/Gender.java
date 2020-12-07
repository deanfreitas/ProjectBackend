package br.com.project.backend.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {
    @JsonProperty("Masculino")
    MASCULINO("Masculino"),

    @JsonProperty("Feminino")
    FEMININO("Feminino");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
