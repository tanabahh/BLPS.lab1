package blp.lab1.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Data
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

    @ManyToMany(mappedBy = "ordered")
    Set<Food> orderedFood;

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
