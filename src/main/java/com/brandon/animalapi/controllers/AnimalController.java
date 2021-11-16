package com.brandon.animalapi.controllers;

import com.brandon.animalapi.models.Animal;
import com.brandon.animalapi.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public @ResponseBody ResponseEntity<Animal> get() {
        return new ResponseEntity<>(new Animal("test", "test", 4), HttpStatus.OK);
    }

    @GetMapping("/{animalId}")
    public @ResponseBody ResponseEntity<String> get(@PathVariable(value="animalId") final String animalId) {
        return new ResponseEntity<>("GET Response. Animal: " + animalId, HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<String> post() {
        return new ResponseEntity<>("POST Response", HttpStatus.OK);
    }

    @PutMapping
    public @ResponseBody ResponseEntity<String> put() {
        return new ResponseEntity<String>("PUT Response", HttpStatus.OK);
    }

    @DeleteMapping()
    public @ResponseBody ResponseEntity<String> delete() {
        return new ResponseEntity<>("DELETE Response", HttpStatus.OK);
    }
}
