package blp.lab1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import blp.lab1.model.Restaurant;


@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long>, JpaRepository<Restaurant, Long> {
}
