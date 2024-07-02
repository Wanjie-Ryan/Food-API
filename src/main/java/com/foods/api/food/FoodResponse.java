package com.foods.api.food;

// this class acts as a DTO (Data Transfer Object) is a simple object that is used to transfer data between different layers of an application. It is often used to encapsulate the data that needs to be sent over the network or between layers, ensuring that the data structure remains consistent and providing a clear contract between the layers.


// Create a response object to hold the message and the food details, then return this response in the controller.
public class FoodResponse {
    private String message;
    private FoodModel food;


    public FoodResponse(String message, FoodModel food) {
        this.message = message;
        this.food = food;

    }

    public String getMessage(){
        return message;
    }

    public FoodModel getFood(){
        return food;
    }
}
