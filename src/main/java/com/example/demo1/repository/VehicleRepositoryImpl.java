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
    public Vehicle savevehicle(Vehicle vehicle){
        Query query = entityManager.createNativeQuery("select  from vehicle " +
                "values (?1,?2,?3,?4,?5)", Vehicle.class);
        Query query = entityManager.createNativeQuery("insert into vehicle " +
                "values (?1,?2,?3,?4,?5)", Vehicle.class);
        query.setParameter(1, vehicle.getPlates());
        query.setParameter(2, vehicle.getOwner());
        query.setParameter(3, vehicle.getType());
        query.setParameter(4, vehicle.getEmployee());
        query.setParameter(5, vehicle.getStart_date());
        query.executeUpdate();
        query = entityManager.createNativeQuery("update vehicle set num_used_spots = num_used_spots + 1");
        query.executeUpdate();
        return vehicle;
    }
    @Override
    public void DeliverVehicle(Vehicle vehicle) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String end_date = sdf1.format(timestamp);

//        Query query = entityManager.createNativeQuery("SELECT start_date FROM vehicle " +
//               "WHERE plates = ?1");
//        String start_date = (String) query.setParameter(1, plates).getSingleResult();
        String start_date = vehicle.getStart_date();

        String[] start = start_date.split("[.]",6); //the date and time that the vehicles entered the garage
        String[] end = end_date.split("[.]",6); //the date and time that the vehicles exits the garage

//        query = entityManager.createNativeQuery("SELECT owner FROM vehicle " + "WHERE plates = ?1");
//        String owner = (String) query.setParameter(1, plates).getSingleResult();
        String owner = vehicle.getOwner();
        Query query = entityManager.createNativeQuery("SELECT count(plates) FROM vehicle " +
                "WHERE owner = ?1");
        BigInteger count = (BigInteger) query.setParameter(1, owner).getSingleResult();

        int counter = count.intValue();

//        query = entityManager.createNativeQuery("SELECT charge FROM vehicle " +
//                "WHERE plates = ?1");
//        float charge = (float) query.setParameter(1, plates).getSingleResult();
        double charge = vehicle.getCharge();
        //----------------Add the income------------------------
//        return count.intValue();


        double income;
        if (counter > 2){
            if (Math.abs(Integer.parseInt(start[4]) - Integer.parseInt(end[4])) < 30 && Integer.parseInt(start[3]) == Integer.parseInt(end[3])  ){
                income =  0;
            }
            else if( Math.abs(Integer.parseInt(start[4]) - Integer.parseInt(end[4])) >= 30 && Math.abs(Integer.parseInt(start[4]) - Integer.parseInt(end[4])) <= 59 && Integer.parseInt(start[3]) == Integer.parseInt(end[3]) ){
                income =  ((Math.abs(Integer.parseInt(start[3]) - Integer.parseInt(end[3])) + 1) * charge)-((Math.abs(Integer.parseInt(start[3]) - Integer.parseInt(end[3])) + 1) * charge)*0.3;
            }
            else{
                income =  (Math.abs(Integer.parseInt(start[2]) - Integer.parseInt(end[2]))*24 +(Math.abs(Integer.parseInt(start[3]) - Integer.parseInt(end[3]))) * charge)-((Math.abs(Integer.parseInt(start[3]) - Integer.parseInt(end[3])) + 1) * charge)*0.3;
            }
        }
        else{
            if (Integer.parseInt(start[3]) == Integer.parseInt(end[3])  && (Integer.parseInt(start[2]) == Integer.parseInt(end[2])) ) {
                income =  charge;
            } else {
                income =  (Math.abs(Integer.parseInt(start[2]) - Integer.parseInt(end[2]))*24 + Math.abs(Integer.parseInt(start[3]) - Integer.parseInt(end[3]))) * charge;
            }
        }
        query = entityManager.createNativeQuery("update garage set total_income = total_income+" +"?1"+" , num_used_spots = num_used_spots - 1");
        query.setParameter(1,income).executeUpdate();
        query = entityManager.createNativeQuery("DELETE FROM vehicle " +
                "WHERE plates = ?1", Vehicle.class);
        query.setParameter(1, vehicle.getPlates()).executeUpdate();


    }
}
