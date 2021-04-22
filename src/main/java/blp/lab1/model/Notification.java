
package blp.lab1.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "time")
    private LocalDate localDate;

    public Notification(){}

    public Notification(Order order, User user, LocalDate localDate){
        this.order = order;
        this.user = user;
        this.localDate = localDate;
    }

    public Order getOrder() {
        return order;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}