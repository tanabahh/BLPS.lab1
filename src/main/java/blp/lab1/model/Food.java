package blp.lab1.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Data
@Entity
@Table(name = "FOOD")
public class Food implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "ordered",
            joinColumns = @JoinColumn(name = "orderedFood"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    Set<Order> ordered;

    @ManyToMany
    @JoinTable(
            name = "havind",
            joinColumns = @JoinColumn(name = "havingFood"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    Set<Restaurant> having;

    public Food setName(String name) {
        this.name = name;
        return this;
    }
}
