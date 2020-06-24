package com.minhvu.fruit.service.interfaces;

import com.minhvu.fruit.model.Address;
import com.minhvu.fruit.model.City;
import com.minhvu.fruit.model.District;
import com.minhvu.fruit.model.Ward;

import java.util.List;

public interface AddressService {
    List<City> getAllCities();
    List<District> getAllDistrictsInCity(int id_city);
    List<Ward> getAllWardsInDistrict(int id_district);
}
