package blp.lab1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import blp.lab1.model.Food;

@Repository
public interface FoodRepository extends CrudRepository<Food, Long>, JpaRepository<Food, Long> {
}
