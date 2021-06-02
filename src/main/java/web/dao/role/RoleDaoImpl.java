package web.dao.role;

import org.springframework.stereotype.Repository;
import web.exception.RoleException;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(String nameRole, User user) {

    }

    @Override
    public Role getRole(String roleName) {
        List<Role> roles = entityManager.createQuery("select r from Role r where r.name = :roleName", Role.class)
                                        .setParameter("roleName", roleName)
                                        .getResultList();
        if (roles.size() > 1) {
            throw new RoleException("In database found roles with same name!!!");
        } else if (roles.size() == 0) {
            throw new RoleException("In database not found this role: " + roleName);
        }
        return roles.get(0);
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(
                entityManager.createQuery("select r from Role r", Role.class)
                                        .getResultList()
        );
    }

    @Override
    public Role getRoleByName(String nameRole) {
        List<Role> roles = entityManager.createQuery("select r from Role r where r.name = :roleName", Role.class)
                .setParameter("roleName", nameRole)
                .getResultList();
        return roles.get(0);
    }
}
