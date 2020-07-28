package com.minhvu.fruit.dao.implement;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dao.interfaces.AddressDao;
import com.minhvu.fruit.model.City;
import com.minhvu.fruit.model.District;
import com.minhvu.fruit.model.Ward;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {
    private EntityManager entityManager = AppConfig.ENTITY_MANAGER;

    @Override
    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        try{
            cities = entityManager.createQuery("from City ").getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return cities;
    }

    @Override
    public List<District> getAllDistrictsInCity(int id_city) {
        List<District> districts = new ArrayList<>();
        try{
            districts = entityManager.createQuery("select dt from District as dt where dt.idCity = :id")
                    .setParameter("id",id_city)
                    .getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return districts;
    }

    @Override
    public List<Ward> getAllWardsInDistrict(int id_district) {
        List<Ward> wards = new ArrayList<>();
        try{
            wards = entityManager.createQuery("select wr from Ward as wr where wr.idDistrict = :id")
                    .setParameter("id",id_district)
                    .getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return wards;
    }

    @Override
    public Ward getWardById(int id_ward) {
        Ward ward = null;
        try{
            ward = entityManager.find(Ward.class,id_ward);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return ward;
    }
}
