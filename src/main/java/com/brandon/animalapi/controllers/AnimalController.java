package com.brandon.animalapi.controllers;

import com.brandon.animalapi.dto.AnimalDto;
import com.brandon.animalapi.dto.OwnerDto;
import com.brandon.animalapi.services.AnimalService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("animals")
public class AnimalController {

    private final AnimalService service;

    public AnimalController(AnimalService animalService) {
        this.service = animalService;
    }

    @GetMapping
    public ResponseEntity<List<AnimalDto>> get(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.getAnimals(search));
    }

    @GetMapping("/{animalId}")
    public ResponseEntity<AnimalDto> get(@PathVariable("animalId") final Integer animalId) {
        return ResponseEntity.ok(service.getAnimal(animalId));
    }

    @GetMapping("/{animalId}/owner")
    public ResponseEntity<OwnerDto> getAnimalOwner(@PathVariable("animalId") final Integer animalId) {
        return ResponseEntity.ok(service.getAnimalOwner(animalId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> post(@Valid @RequestBody AnimalDto animalDto) {
        long createdObject = service.createAnimal(animalDto);
        return ResponseEntity.created(URI.create(String.format("/animals/%d", createdObject))).build();
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<Void> put(@PathVariable("animalId") final Integer animalId, @Valid @RequestBody AnimalDto animalDto) {
        service.updateAnimal(animalDto, animalId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity<String> delete(@PathVariable("animalId") final Integer animalId) {
        service.deleteAnimal(animalId);
        return ResponseEntity.noContent().build();
    }
}
