package com.delivery.address.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller to expose delivery methods
 */
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    public static final String DELIVERY = "Delivery ";

    private static final List<String> stringArrayList = new ArrayList<>();

    /**
     * This method gets all deliveries pending by the store
     * @return List with all deliveries
     */
    @GetMapping("/")
    public List<String> getAllDeliveries() {
        List<String> stringArrayList = getDeliveries();
        return stringArrayList;
    }


    /**
     * This method gets one delivery by identifier
     * Examples of correct and incorrect requests
     * Correct http://localhost:8080/delivery/1 this should return Delivery 1
     * Incorrect http://localhost:8080/delivery/12 this should return Delivery not found
     * @return A delivery that has an specific identifier
     */
    @GetMapping("/{id}")
    public String getDeliveryById(@PathVariable("id") String id) {
        List<String> stringArrayList = getDeliveries();

        for(int i = 0; i< stringArrayList.size(); i++) {
            if (stringArrayList.get(i).contains(id)){
                return stringArrayList.get(i);
            }
        }

        return "Delivery not found";
    }

    /**
     * This method let to modify an specific delivery
     * Example
     * @param id delivery identifier to update
     * @param deliveryToUpdate delivery to update
     * @return message with the update result
     */
    @PutMapping("/{id}")
    public String modifyDeliveryById(@PathVariable(value = "id", required = true) String id, @Validated @RequestBody(required = true) String deliveryToUpdate) {
        List<String> stringArrayList = getDeliveries();

        for(int i = 0; i< stringArrayList.size(); i++) {
            if (stringArrayList.get(i).contains(id)){
                stringArrayList.set(i, deliveryToUpdate + " " + i);
                return "Update success";
            }
        }
        return "Update not allowed";
    }

    /**
     * This method let to create an delivery
     * @param deliveryToCreate delivery to create
     * @return message with the creation result
     */
    @PostMapping("/")
    public String createDelivery(@Validated @RequestBody(required = true) String deliveryToCreate) {
        List<String> stringArrayList = getDeliveries();

        stringArrayList.add(deliveryToCreate + " "+stringArrayList.size());

        return "Delivery created successfully";
    }

    /**
     * This method let to delete an specific delivery
     * @param id delivery identifier to delete
     * @return message with the delete result
     */
    @DeleteMapping("/{id}")
    public String deleteDeliveryById(@PathVariable(value = "id", required = true) String id) {
        List<String> stringArrayList = getDeliveries();

        for(int i = 0; i< stringArrayList.size(); i++) {
            if (stringArrayList.get(i).contains(id)){
                stringArrayList.remove(i);
                return "Delete success";
            }
        }
        return "Delete not allowed";
    }

    /**
     * Handle internally the delivery list
     * @return a list with deliveries
     */
    private static List<String> getDeliveries() {
        if(stringArrayList.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                stringArrayList.add(DELIVERY + i);
            }
        }
        return stringArrayList;
    }
}
