package com.minhvu.fruit.dao.interfaces;

import com.minhvu.fruit.model.City;
import com.minhvu.fruit.model.District;
import com.minhvu.fruit.model.Ward;

import java.sql.SQLException;
import java.util.List;

public interface AddressDao {
    List<City> getAllCities() throws SQLException;
    List<District> getAllDistrictsInCity(int id_city) throws SQLException;
    List<Ward> getAllWardsInDistrict(int id_district) throws SQLException;
    Ward getWardById(int id_ward) throws SQLException;
}
