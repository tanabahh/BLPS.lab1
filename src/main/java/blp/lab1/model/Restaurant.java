package blp.lab1.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Data
@Table(name = "RESTAURANT")
@Entity
public class Restaurant implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    Set<Food> havingFood;

    public Restaurant setName(String name) {
        this.name = name;
        return this;
    }

    public Restaurant setHavingFood(Set<Food> havingFood) {
        this.havingFood = havingFood;
        return this;
    }
}
