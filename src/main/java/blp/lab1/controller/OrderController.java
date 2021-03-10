package blp.lab1.controller;

import blp.lab1.model.Order;
import blp.lab1.service.FoodService;
import blp.lab1.service.OrderService;
import blp.lab1.service.RestaurantService;
import blp.lab1.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final FoodService foodService;

    @Autowired
    public OrderController (OrderService orderService, RestaurantService restaurantService, UserService userService, FoodService foodService){
        this.restaurantService = restaurantService;
        this.userService = userService;
        this.orderService = orderService;
        this.foodService = foodService;
     }

     @ApiOperation(value = "${OrderController.getOrder}")
     @GetMapping("/{id}")
     public Boolean getOrder(@ApiParam("id") @PathVariable(name = "id") Long orderId) {
         System.out.println(orderId);
        return orderService.fetchOrderById(orderId).getPaid();
     }

     @ApiOperation(value = "${OrderController.addOrder}")
     @PostMapping("/add")
     public HttpStatus addOrder(@ApiParam("user_id") @RequestParam(name = "user_id") Long userId,
                           @ApiParam("restaurant_id") @RequestParam(name = "restaurant_id") Long restaurantId,
                           @ApiParam("ordered_food") @RequestParam(name = "ordered_food") Set<Long> orderedFood) {
        orderService.saveOrder(new Order(false,
                userService.fetchUserById(userId),
                restaurantService.fetchRestaurantById(restaurantId),
                foodService.fetchOrderedFoodBySetOfId(orderedFood)));
        return HttpStatus.OK;
     }

     @ApiOperation(value = "${OrderController.pay}")
     @PostMapping("/{id}/pay")
     public HttpStatus pay(@ApiParam("id") @PathVariable(name = "id") Long id) {
         System.out.println("Paid" + id);
        orderService.paidForOrder(id);
        if (orderService.fetchOrderById(id).getPaid()) {
            return HttpStatus.OK;
        } else return HttpStatus.BAD_REQUEST;
     }

}
