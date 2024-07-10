package com.foods.api.food;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    // UPDATING A FOOD BY ITS ID

    @Transactional

    public FoodModel updateFoodById(String id, FoodModel food){

        Optional<FoodModel> checkFood = repository.findById(UUID.fromString(id));

        if(!checkFood.isPresent()){
            throw new IllegalStateException("Food of id " + id + " does not exist");
        }

        if(food.getName() !=null && food.getName().length() > 0 && !Objects.equals(checkFood.get().getName(), food.getName())){
            checkFood.get().setName(food.getName());

        }

        if(food.getCountry() != null && food.getCountry().length() >0 && !Objects.equals(checkFood.get().getCountry(), food.getCountry())){
            checkFood.get().setCountry(food.getCountry());
        }

        return repository.save(checkFood.get());

    }

    // DELETING A FOOD BASED ON ITS ID

    public FoodModel deleteFoodById(String id){

        Optional <FoodModel> checkfood = repository.findById(UUID.fromString(id));

        if(!checkfood.isPresent()){
            throw new IllegalStateException("Food of id " +id+ " does not exist");
        }

        FoodModel foodToDelete = checkfood.get(); // store the food to be deleted
        repository.deleteById(UUID.fromString(id));
        return foodToDelete;

    }





}
