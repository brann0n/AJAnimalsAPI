package com.brandon.animalapi.dto;

import lombok.Setter;
import lombok.Getter;

import javax.validation.constraints.*;

public class OwnerDto {
    @Getter @Setter private long id;

    @NotBlank
    @Size(max = 50)
    @Getter @Setter private String name;

    @NotBlank
    @Getter @Setter private String address;

    @Positive
    @Getter @Setter private int familySize;

    public OwnerDto() {

    }

}
