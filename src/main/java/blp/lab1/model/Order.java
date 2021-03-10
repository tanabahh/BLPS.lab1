package blp.lab1.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Table(name = "orders")
@Entity
public class Order implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paid")
    private Boolean paid;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToMany
    @JoinTable(
            name = "ordered_food",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "food_id", referencedColumnName = "id")
    )
    Set<Food> orderedFood;

    public Order(){};

    public Order(Boolean paid, User user, Restaurant restaurant, Set<Food> foods) {
        this.paid = paid;
        this.user = user;
        this.restaurant = restaurant;
        this.orderedFood = foods;
        this.orderedFood.forEach(x -> x.getOrdered().add(this));
    }


    public Set<Food> getOrderedFood() {
        return orderedFood;
    }

    public Order setPaid (Boolean paid) {
        this.paid = paid;
        return this;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }

    public Order setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
        return this;
    }

    public Order setOrderedFood(Set<Food> orderedFood){
        this.orderedFood = orderedFood;
        return this;
    }
}
