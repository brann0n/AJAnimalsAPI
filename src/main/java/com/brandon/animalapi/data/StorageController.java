package com.brandon.animalapi.data;

import com.brandon.animalapi.models.Animal;
import com.brandon.animalapi.models.IDataModel;
import com.brandon.animalapi.models.Owner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StorageController {
    private static final Map<String, HashMap<Integer, IDataModel>> map = new HashMap<>();
    public static final String ANIMAL_KEY = "Animals";
    public static final String OWNER_KEY = "Owners";

    public StorageController(){
        map.put(ANIMAL_KEY, new HashMap<Integer, IDataModel>());
        map.get(ANIMAL_KEY).put(1, new Animal("Fluffy", "Dog", 4));
        map.get(ANIMAL_KEY).put(2, new Animal("Boomer", "Dog", 2));
        map.get(ANIMAL_KEY).put(3, new Animal("Mr. Biggles", "Cat", 7));

        //set owners in memory
        map.put(OWNER_KEY, new HashMap<Integer, IDataModel>());
        map.get(OWNER_KEY).put(1, new Owner("John Doe", "small street 3944b, BigCity", false, 2));
        map.get(OWNER_KEY).put(2, new Owner("Ben Al", "big street 24, SmallTown", true, 8));
    }

    public IDataModel getDataRecord(String key, int index){
        if(map.containsKey(key)){
            if(map.get(key).containsKey(index)){
                return map.get(key).get(index);
            }
        }

        throw new DataNotFoundException(key, index);
    }

    public HashMap<Integer, IDataModel> getDataTable(String key){
        if(map.containsKey(key)){
            return map.get(key);
        }

        throw new DataNotFoundException(key);
    }
}
