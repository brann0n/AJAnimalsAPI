package com.brandon.animalapi.services;

import com.brandon.animalapi.data.StorageController;
import com.brandon.animalapi.models.IDataModel;
import com.brandon.animalapi.models.Owner;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class OwnerService {
    private final StorageController data;

    public OwnerService(StorageController data){
        this.data = data;
    }

    public HashMap<Integer, IDataModel> getOwners(){
        return data.getDataTable(StorageController.OWNER_KEY); //list index should now be actual index
    }

    public Owner getOwner(int id){
        return (Owner) data.getDataRecord(StorageController.OWNER_KEY, id);
    }
}
