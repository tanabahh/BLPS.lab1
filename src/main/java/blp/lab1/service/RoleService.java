package blp.lab1.service;

import blp.lab1.model.Food;
import blp.lab1.model.Role;
import blp.lab1.repository.FoodRepository;
import blp.lab1.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole (Role role) {
        return roleRepository.save(role);
    }

    public Set<Role> fetchRolesdBySetOfId(Set<Long> rolesId){
        HashSet<Role> roles = new HashSet<>();
        Iterator<Long> iterator = rolesId.iterator();
        while (iterator.hasNext()) {
            long id = iterator.next();
            roles.add(roleRepository.findById(id).isPresent() ? roleRepository.findById(id).get() : null);
        }
        System.out.println(roles);
        return roles;
    }

}
