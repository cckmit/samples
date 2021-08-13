package over35.samples.fluentmybatis.service;

import cn.org.atool.fluent.mybatis.segment.JoinQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import over35.samples.fluentmybatis.repository.dao.intf.UserDao;
import over35.samples.fluentmybatis.repository.entity.UserEntity;
import over35.samples.fluentmybatis.repository.wrapper.RoleQuery;
import over35.samples.fluentmybatis.repository.wrapper.UserQuery;

import java.util.List;

/**
 * @author: daibin
 * @date: 2021/7/30 5:30 下午
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserEntity getUserByUsername(String username) {
        return userDao.selectById(username);
    }

    public List<UserEntity> getUserByRole(String role) {
        UserQuery userQuery = new UserQuery("u");
        RoleQuery roleQuery = new RoleQuery("r")
                .where()
                .role()
                .eq(role)
                .end();

        JoinQuery joinQuery = userQuery.selectAll().join(roleQuery).on(l -> l.where().username(), r -> r.where().username())
                .endJoin().build();

        return userDao.mapper().listEntity(joinQuery);
    }
}
