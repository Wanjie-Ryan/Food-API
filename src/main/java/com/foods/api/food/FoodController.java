package com.foods.api.food;


import org.springframework.beans.factory.annotation.Autowired;
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

    public void createFood(@RequestBody FoodModel food){
        foodservice.addFood(food);
    }






}
