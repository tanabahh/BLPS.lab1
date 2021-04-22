package blp.lab1.repository;

import blp.lab1.model.Food;
import blp.lab1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
}