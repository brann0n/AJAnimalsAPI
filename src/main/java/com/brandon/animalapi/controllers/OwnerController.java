package com.brandon.animalapi.controllers;

import com.brandon.animalapi.dto.AnimalDto;
import com.brandon.animalapi.dto.OwnerDto;
import com.brandon.animalapi.services.OwnerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("owners")
public class OwnerController {

    private final OwnerService service;

    public OwnerController(OwnerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> get() {
        return ResponseEntity.ok(service.getOwners());
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerDto> get(@PathVariable(value = "ownerId") final long ownerId) {
        return ResponseEntity.ok(service.getOwner(ownerId));
    }

    @GetMapping("/{ownerId}/animals")
    public ResponseEntity<List<AnimalDto>> getOwnerAnimals(@PathVariable(value = "ownerId") final int ownerId) {
        return ResponseEntity.ok(service.getOwnerAnimals(ownerId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> post(@Valid @RequestBody OwnerDto ownerDto) {
        Long createdObject = service.createOwner(ownerDto);
        return ResponseEntity.created(URI.create(String.format("/owners/%d", createdObject))).build();
    }

    @PutMapping("/{ownerId}")
    @ResponseBody
    public ResponseEntity<Void> put(@PathVariable("ownerId") final Long ownerId, @Valid @RequestBody OwnerDto ownerDto) {
        service.updateOwner(ownerDto, ownerId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{ownerId}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("ownerId") final Integer ownerId) {
        service.deleteOwner(ownerId);
        return ResponseEntity.noContent().build();
    }
}
