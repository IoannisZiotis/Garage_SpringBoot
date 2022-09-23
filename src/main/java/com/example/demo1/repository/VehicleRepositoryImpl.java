package com.example.demo1.repository;
import com.example.demo1.Vehicle;
import  com.example.demo1.repository.VehicleRepositoryCustom;
import org.springframework.stereotype.Repository;
import com.example.demo1.Garage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


@Repository
@Transactional
public class VehicleRepositoryImpl implements VehicleRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    @Override
    public void DeliverVehicle(String plates) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String end_date = sdf1.format(timestamp);

        Query query = entityManager.createNativeQuery("SELECT start_date FROM vehicle " +
               "WHERE plates = ?1");
        String start_date = (String) query.setParameter(1, plates).getSingleResult();


        String[] start = start_date.split("[.]",6); //the date and time that the vehicles entered the garage
        String[] end = end_date.split("[.]",6); //the date and time that the vehicles exits the garage

        query = entityManager.createNativeQuery("SELECT owner FROM vehicle " + "WHERE plates = ?1");
        String owner = (String) query.setParameter(1, plates).getSingleResult();

        query = entityManager.createNativeQuery("SELECT count(plates) FROM vehicle " +
                "WHERE owner = ?1");
        BigInteger count = (BigInteger) query.setParameter(1, owner).getSingleResult();

        int counter = count.intValue();

        query = entityManager.createNativeQuery("SELECT charge FROM vehicle " +
                "WHERE plates = ?1");
        float charge = (float) query.setParameter(1, plates).getSingleResult();

        //----------------Add the income------------------------
//        return count.intValue();

        query = entityManager.createNativeQuery("SELECT total_income FROM garage ");
        double total_income = (double) query.getSingleResult();

        if (counter > 2){
            if (Math.abs(Integer.parseInt(start[4]) - Integer.parseInt(end[4])) < 30 && Math.abs(Integer.parseInt(start[3]) - Integer.parseInt(end[3])) == 0){
                total_income = total_income + 0;
            }
            else if( Math.abs(Integer.parseInt(start[4]) - Integer.parseInt(end[4])) >= 30 && Math.abs(Integer.parseInt(start[4]) - Integer.parseInt(end[4])) <= 59 && Math.abs(Integer.parseInt(start[3]) - Integer.parseInt(end[3])) == 0){
                total_income = total_income + ((Math.abs(Integer.parseInt(start[3]) - Integer.parseInt(end[3])) + 1) * charge)-((Math.abs(Integer.parseInt(start[3]) - Integer.parseInt(end[3])) + 1) * charge)*0.3;
            }
            else{
                total_income = total_income + ((Math.abs(Integer.parseInt(start[3]) - Integer.parseInt(end[3]))) * charge)-((Math.abs(Integer.parseInt(start[3]) - Integer.parseInt(end[3])) + 1) * charge)*0.3;
            }
        }
        else{
            if (Math.abs(Integer.parseInt(start[4]) - Integer.parseInt(end[4])) > 0) {
                total_income = total_income + (Math.abs(Integer.parseInt(start[3]) - Integer.parseInt(end[3])) + 1) * charge;
            } else {
                total_income = total_income + (Math.abs(Integer.parseInt(start[3]) - Integer.parseInt(end[3]))) * charge;
            }
        }

//        query = entityManager.createNativeQuery("DELETE FROM vehicle " +
//                "WHERE plates = ?1", Vehicle.class);
//        query.setParameter(1, plates).executeUpdate();


    }
}
