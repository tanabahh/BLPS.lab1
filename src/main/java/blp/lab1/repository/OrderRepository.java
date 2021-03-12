package blp.lab1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import blp.lab1.model.Order;

import javax.transaction.Transactional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>, JpaRepository<Order, Long> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update orders set status = ( :new_status) where orders.id = ( :order_id)")
    void changeStatus(@Param("order_id") Long order_id, @Param("new_status") String new_status);
}
