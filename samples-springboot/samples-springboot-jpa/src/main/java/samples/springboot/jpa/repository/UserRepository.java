package samples.springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import samples.springboot.jpa.model.User;

import java.util.List;

/**
 * @author: daibin
 * @date: 2021/7/31 1:09 上午
 */
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u left join Role r on u.username = r.username where r.role = :role")
    List<User> selectAllByRole(@Param("role") String role);
}
