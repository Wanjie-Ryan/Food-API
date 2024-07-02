package com.foods.api.food;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {

    private FoodRepository repository;

    @Autowired

    public FoodService(FoodRepository repository) {
        this.repository = repository;
    }

    // POSTING DATA TO THE DB
    public FoodModel addFood(FoodModel food) {

        Optional<FoodModel> findFood = repository.findFoodByName(food.getName());

        if(findFood.isPresent()){
               throw new IllegalStateException("Food Already Exists");
        }

        return repository.save(food);
    }





}
