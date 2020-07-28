package com.minhvu.fruit.service.implement;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.common.converter.AddressConverter;
import com.minhvu.fruit.dao.implement.AddressDaoImpl;
import com.minhvu.fruit.dao.interfaces.AddressDao;
import com.minhvu.fruit.dto.AddressDTO;
import com.minhvu.fruit.model.City;
import com.minhvu.fruit.model.District;
import com.minhvu.fruit.model.Ward;
import com.minhvu.fruit.service.interfaces.AddressService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressServiceImpl implements AddressService {
    private AddressDao addressDao = new AddressDaoImpl();
    private AddressConverter addressConverter = AppConfig.ADDRESS_CONVERTER;

    @Override
    public List<AddressDTO> getAllCities() {
        try {
            List<AddressDTO> addressDTOList = new ArrayList<>();
            List<City> cities = addressDao.getAllCities();
            for (City city : cities){
                addressDTOList.add(addressConverter.toDTO(city));
            }
            return addressDTOList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AddressDTO> getAllDistrictsInCity(int id_city) {
        try {
            List<AddressDTO> addressDTOList = new ArrayList<>();
            List<District> districts = addressDao.getAllDistrictsInCity(id_city);
            for (District district : districts){
                addressDTOList.add(addressConverter.toDTO(district));
            }
            return addressDTOList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AddressDTO> getAllWardsInDistrict(int id_district) {
        try {
            List<AddressDTO> addressDTOList = new ArrayList<>();
            List<Ward> wards = addressDao.getAllWardsInDistrict(id_district);
            for (Ward ward : wards){
                addressDTOList.add(addressConverter.toDTO(ward));
            }
            return addressDTOList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
