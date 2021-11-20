package com.brandon.animalapi.controllers;

import com.brandon.animalapi.models.IDataModel;
import com.brandon.animalapi.models.Owner;
import com.brandon.animalapi.services.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("owners")
public class OwnerController {

    private final OwnerService service;

    public OwnerController(OwnerService service){
        this.service = service;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<HashMap<Integer, IDataModel>> get() {
        return ResponseEntity.ok(service.getOwners());
    }

    @GetMapping("/{ownerId}")
    public @ResponseBody ResponseEntity<Owner> get(@PathVariable(value="ownerId") final int ownerId) {
        return ResponseEntity.ok(service.getOwner(ownerId));
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
