package br.com.project.backend.utils;

import br.com.project.backend.enums.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UsersMapper implements AttributeConverter<Gender, String> {


    public static final String MASCULINO = "Masculino";
    public static final String FEMININO = "Feminino";

    @Override
    public String convertToDatabaseColumn(Gender x) {
        return String.valueOf(x.getGender());
    }

    @Override
    public Gender convertToEntityAttribute(String y) {
        if (y == null) return null;
        if (MASCULINO.equalsIgnoreCase(y)) return Gender.MASCULINO;
        if (FEMININO.equalsIgnoreCase(y)) return Gender.FEMININO;

        throw new IllegalStateException("Gender invalid: " + y);
    }

}
