package com.brandon.animalapi.services;

import com.brandon.animalapi.data.StorageController;
import com.brandon.animalapi.models.Animal;
import com.brandon.animalapi.models.IDataModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnimalService {
    private final StorageController data;

    public AnimalService(StorageController data){
        this.data = data;
    }

    public HashMap<Integer, IDataModel> getAnimals(){
        return data.getDataTable(StorageController.ANIMAL_KEY);
    }

    public Animal getAnimal(int index){
        return (Animal) data.getDataRecord(StorageController.ANIMAL_KEY, index);
    }
}
