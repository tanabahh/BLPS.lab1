package blp.lab1.controller;

import blp.lab1.model.Order;
import blp.lab1.model.Status;
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
     public String getOrder(@ApiParam("id") @PathVariable(name = "id") Long orderId) {
         System.out.println("status " + orderService.fetchOrderById(orderId).getStatus().toString());
        return "status " + orderService.fetchOrderById(orderId).getStatus().toString();
     }

     @ApiOperation(value = "${OrderController.addOrder}")
     @PostMapping("/add")
     public HttpStatus addOrder(@ApiParam("user_id") @RequestParam(name = "user_id") Long userId,
                           @ApiParam("restaurant_id") @RequestParam(name = "restaurant_id") Long restaurantId,
                           @ApiParam("ordered_food") @RequestParam(name = "ordered_food") Set<Long> orderedFood) {
        orderService.saveOrder(new Order(Status.CREATE,
                userService.fetchUserById(userId),
                restaurantService.fetchRestaurantById(restaurantId),
                foodService.fetchOrderedFoodBySetOfId(orderedFood)));
        return HttpStatus.OK;
     }

     @ApiOperation(value = "${OrderController.pay}")
     @PostMapping("/{id}/pay")
     public String pay(@ApiParam("id") @PathVariable(name = "id") Long id) {
         System.out.println("Paid" + id);
        orderService.changeStatus(id, Status.PAID);
        return "OK";
     }

    @ApiOperation(value = "${OrderController.to_restaurant}")
    @PostMapping("/{id}/to_restaurant")
    public String toRestaurant(@ApiParam("id") @PathVariable(name = "id") Long id) {
        if (orderService.fetchOrderById(id).getStatus() == Status.PAID){
            orderService.changeStatus(id, Status.IN_RESTAURANT);
            return "OK";
        } else return "You dont pay for this order";
    }

    @ApiOperation(value = "${OrderController.to_cook}")
    @PostMapping("/{id}/to_cook")
    public String toCook(@ApiParam("id") @PathVariable(name = "id") Long id) {
        if (orderService.fetchOrderById(id).getStatus() == Status.IN_RESTAURANT) {
            orderService.changeStatus(id, Status.COOKING);
            return "OK";
        } else return "Order is not yet in the restaurant";
    }

    @ApiOperation(value = "${OrderController.courier_assigned}")
    @PostMapping("/{id}/courier_assigned")
    public String courierAssigned(@ApiParam("id") @PathVariable(name = "id") Long id) {
        if (orderService.fetchOrderById(id).getStatus() == Status.COOKING) {
            orderService.changeStatus(id, Status.COURIER_ASSIGNED);
            return "OK";
        } else return  "The order has not yet been received by the chef";
    }
}
