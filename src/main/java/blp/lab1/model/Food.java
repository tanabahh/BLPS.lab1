package blp.lab1.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "food")
public class Food implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "orderedFood")
    Set<Order> ordered;

    @ManyToMany
    @JoinTable(
            name = "restaurant_food",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    Set<Restaurant> having;

    public Food(){};

    public Set<Restaurant> getHaving(){ return this.having; }

    public Set<Order> getOrdered() {
        return this.ordered;
    }

    public Food setName(String name) {
        this.name = name;
        return this;
    }
}
