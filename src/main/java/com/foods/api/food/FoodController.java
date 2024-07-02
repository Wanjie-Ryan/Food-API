package com.foods.api.food;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/food")
public class FoodController {

    private FoodService foodservice;

    @Autowired
    public FoodController(FoodService foodservice) {
        this.foodservice = foodservice;
    }

    // POSTING DATA TO THE DB
    @PostMapping

    // ResponseEntity is a powerful class provided by Spring that allows you to control the HTTP status code, headers, and body of the response.
    public ResponseEntity <FoodResponse> createFood(@RequestBody FoodModel food){


        FoodModel savedFood = foodservice.addFood(food);
        FoodResponse response = new FoodResponse("Food added suucessfully", savedFood);
        return ResponseEntity.ok(response);
    }






}
