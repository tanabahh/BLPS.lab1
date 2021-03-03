package blp.lab1.service;

import blp.lab1.model.Food;
import blp.lab1.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food saveFood (Food food) {
        return foodRepository.save(food);
    }

    public Set<Food> fetchOrderedFoodBySetOfId(Set<Long> orderedFoodId){
        HashSet<Food> orderedFood = new HashSet<>();
        Iterator<Long> iterator = orderedFoodId.iterator();
        while (iterator.hasNext()) {
            long id = iterator.next();
            orderedFood.add(foodRepository.findById(id).isPresent() ? foodRepository.findById(id).get() : null);
        }
        System.out.println(orderedFood);
        return orderedFood;
    }

}
