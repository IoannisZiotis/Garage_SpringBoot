package com.example.demo1.repository;
import com.example.demo1.Vehicle;
import  com.example.demo1.repository.VehicleRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


@Repository
@Transactional
public class VehicleRepositoryImpl implements VehicleRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void DeliverVehicle(String plates) {
        Query query = entityManager.createNativeQuery("DELETE FROM vehicle " +
                "WHERE plates = ?1", Vehicle.class);
        query.setParameter(1, plates).executeUpdate();;
    }
}
