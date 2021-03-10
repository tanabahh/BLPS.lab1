package blp.lab1.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Table(name = "restaurant")
@Entity
public class Restaurant implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "having")
    Set<Food> havingFood;

    public Restaurant(){};

    public Restaurant(String name, Set<Food> havingFood) {
        this.name = name;
        this.havingFood = havingFood;
        this.havingFood.forEach(x -> x.getHaving().add(this));
    }

    public Restaurant setName(String name) {
        this.name = name;
        return this;
    }

    public Restaurant setHavingFood(Set<Food> havingFood) {
        this.havingFood = havingFood;
        return this;
    }
}
