package com.brandon.animalapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

public class AnimalDto {
    @Getter
    @Setter
    private long id;

    @Size(max = 50)
    @NotBlank
    @Getter @Setter private String name;

    @Size(max = 20)
    @NotBlank
    @Getter @Setter private String type;

    @PositiveOrZero
    @Getter @Setter private int age;

    @Positive
    @NotNull
    @Getter @Setter private long ownerId;

    public AnimalDto() {

    }


}
