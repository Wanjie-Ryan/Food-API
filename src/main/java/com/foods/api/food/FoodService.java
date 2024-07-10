package com.foods.api.food;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    // GETTING ALL THE DATA FROM THE DB

    public List<FoodModel> getAllFoods (){
        return repository.findAll();
    }

    // GETTING A FOOD BY ITS ID
    // we return the foodmodel directly and throw an exception in case the food does not exist.
    // in the case I used sth like this: public optional <FoodModel>........  i would not have thrown the exception because the optional would have handled the case where the food does not exist.
    public FoodModel getFoodById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalStateException("Food of Id " + id + " does not exist"));
    }






}
