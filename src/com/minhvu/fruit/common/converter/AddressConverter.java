package com.minhvu.fruit.common.converter;

import com.minhvu.fruit.dto.AddressDTO;
import com.minhvu.fruit.model.City;
import com.minhvu.fruit.model.District;
import com.minhvu.fruit.model.Ward;

public class AddressConverter {

    public AddressDTO toDTO(Ward ward){
        AddressDTO wardDTO = new AddressDTO(ward.getId(),ward.getName());
        return wardDTO;
    }

    public AddressDTO toDTO(District district){
        AddressDTO districtDTO = new AddressDTO(district.getId(),district.getName());
        return districtDTO;
    }

    public  AddressDTO toDTO(City city){
        AddressDTO cityDTO = new AddressDTO(city.getId(),city.getName());
        return cityDTO;
    }

}
