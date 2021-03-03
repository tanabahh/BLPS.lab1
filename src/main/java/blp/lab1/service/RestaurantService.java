package blp.lab1.service;

import blp.lab1.model.Restaurant;
import blp.lab1.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant fetchRestaurantById(Long id){
        return restaurantRepository.findById(id).isPresent() ? restaurantRepository.findById(id).get() : null;
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
