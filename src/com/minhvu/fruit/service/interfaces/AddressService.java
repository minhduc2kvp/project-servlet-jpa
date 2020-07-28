package com.minhvu.fruit.service.interfaces;

import com.minhvu.fruit.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> getAllCities();
    List<AddressDTO> getAllDistrictsInCity(int id_city);
    List<AddressDTO> getAllWardsInDistrict(int id_district);
}
