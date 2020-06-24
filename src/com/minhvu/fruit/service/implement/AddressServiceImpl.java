package com.minhvu.fruit.service.implement;

import com.minhvu.fruit.dao.implement.AddressDaoImpl;
import com.minhvu.fruit.dao.interfaces.AddressDao;
import com.minhvu.fruit.model.City;
import com.minhvu.fruit.model.District;
import com.minhvu.fruit.model.Ward;
import com.minhvu.fruit.service.interfaces.AddressService;

import java.sql.SQLException;
import java.util.List;

public class AddressServiceImpl implements AddressService {
    private AddressDao addressDao = new AddressDaoImpl();

    @Override
    public List<City> getAllCities() {
        try {
            return addressDao.getAllCities();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<District> getAllDistrictsInCity(int id_city) {
        try {
            return addressDao.getAllDistrictsInCity(id_city);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ward> getAllWardsInDistrict(int id_district) {
        try {
            return addressDao.getAllWardsInDistrict(id_district);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
