package blp.lab1.controller;

import blp.lab1.model.Restaurant;
import blp.lab1.service.FoodService;
import blp.lab1.service.RestaurantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final FoodService foodService;

    @Autowired
    public RestaurantController (RestaurantService restaurantService, FoodService foodService) {
        this.foodService = foodService;
        this.restaurantService = restaurantService;
    }

    @ApiOperation(value = "${RestaurantControlle.addRestaurant}")
    @PostMapping("/add")
    public Restaurant addRestaurant(@ApiParam("name") @RequestParam(name = "name") String name,
                                    @ApiParam("restaurant_food") @RequestParam(name = "restaurant_food") Set<Long> restaurantFood) {
        return restaurantService.saveRestaurant(new Restaurant()
                .setName(name)
                .setHavingFood(foodService.fetchOrderedFoodBySetOfId(restaurantFood)));
    }
}
