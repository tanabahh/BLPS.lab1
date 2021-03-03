package blp.lab1.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Table(name = "USER")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public User setName (String name) {
        this.name = name;
        return this;
    }
}
