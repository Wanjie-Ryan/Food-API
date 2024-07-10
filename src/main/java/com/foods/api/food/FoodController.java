package com.foods.api.food;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        FoodResponse response = new FoodResponse("Food added successfully", savedFood);
        return ResponseEntity.ok(response);
    }

    // GET ALL THE FOODS
    @GetMapping

    //The method returns a ResponseEntity containing a list (List<FoodResponse>) of FoodResponse objects.
    public ResponseEntity<List<FoodResponse>> getAllFoods(){

        List<FoodModel> allfoods = foodservice.getAllFoods();

        // map each FoodModel to the FoodResponse

        //  the purpose of mapping FoodModel objects to FoodResponse objects is to transform or convert the data retrieved from your service layer (FoodService) into a format that is suitable for sending back to clients as an HTTP response.

              List<FoodResponse> responses = allfoods.stream().map(food-> new FoodResponse("AllFoods", food)).collect(Collectors.toList());

              // Uses Java streams (allfoods.stream()) to process each FoodModel in the allfoods list.
        //The map(food -> new FoodResponse("AllFoods", food)) function transforms each FoodModel object (food) into a corresponding FoodResponse object.
        //Here, new FoodResponse("AllFoods", food) constructs a new FoodResponse object with a message ("AllFoods") and the FoodModel object (food).

        // collect(Collectors.toList()) gathers the transformed FoodResponse objects into a List<FoodResponse> named responses.


        // Purpose of Mapping:
        //Data Transformation: The mapping process (map(food -> new FoodResponse("AllFoods", food))) converts each FoodModel object into a FoodResponse object. This allows you to control what data is included in the response sent to clients.
        //
        //Consistency and Clarity: By using FoodResponse objects, you can ensure that the structure of your API responses remains consistent. Clients consuming your API can expect responses in a standardized format (FoodResponse), which includes both a message and the relevant FoodModel data.
        //
        //Separation of Concerns: It separates the concern of data retrieval (FoodService) from data presentation (FoodController). The controller's responsibility is to format and deliver data in a way that meets the requirements of the API contract.
        //
        //In summary, the mapping process in your getAllFoods method is crucial for transforming FoodModel data into FoodResponse objects, ensuring that the API returns structured and meaningful responses to client requests. This approach enhances readability, maintainability, and consistency in your Spring Boot application.

              return ResponseEntity.ok(responses);
    }

    // GET A FOOD BY ITS ID
    @GetMapping(path = "{id}")

    public ResponseEntity <FoodResponse> getSingleFood (@PathVariable("id") String id){

        FoodModel food = foodservice.getFoodById(id);
        FoodResponse response = new FoodResponse("Food of id " +id + " is available", food);
        return ResponseEntity.ok(response);

    }

    // UPDATING A FOOD BY ITS ID

    @PutMapping(path = "{id}")

    public ResponseEntity <FoodResponse> updateFood(@PathVariable("id") String id, @RequestBody FoodModel food){

        FoodModel foods = foodservice.updateFoodById(id, food);
        FoodResponse response = new FoodResponse("Food of id " +id+ " has been updated successfully", foods);
        return ResponseEntity.ok(response);

    }



}
