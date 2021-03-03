package blp.lab1.controller;

import blp.lab1.model.Food;
import blp.lab1.service.FoodService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodController {
    private FoodService foodService;

    @Autowired
    public FoodController (FoodService foodService) {
        this.foodService = foodService;
    }

    @ApiOperation(value = "${FoodController.addFood}")
    @PostMapping("/add")
    public Food addFood (@ApiParam("name") @RequestParam(name = "name") String name) {
        return foodService.saveFood(new Food()
                .setName(name));
    }
}
