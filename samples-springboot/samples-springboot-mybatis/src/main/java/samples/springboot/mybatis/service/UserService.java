package samples.springboot.mybatis.service;

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samples.springboot.mybatis.repository.mapper.RoleDynamicSqlSupport;
import samples.springboot.mybatis.repository.mapper.UserMapper;
import samples.springboot.mybatis.repository.model.User;

import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.mybatis.dynamic.sql.render.RenderingStrategies.MYBATIS3;
import static samples.springboot.mybatis.repository.mapper.RoleDynamicSqlSupport.role;
import static samples.springboot.mybatis.repository.mapper.UserDynamicSqlSupport.user;
import static samples.springboot.mybatis.repository.mapper.UserDynamicSqlSupport.username;

/**
 * @author: daibin
 * @date: 2021/8/8 9:22 下午
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserByRole(String roleName) {
        SelectStatementProvider ssp= select(UserMapper.selectList)
                .from(user,"u")
                .join(role,"r")
                .on(username,equalTo(RoleDynamicSqlSupport.username))
                .where(role.roleName,isEqualTo(roleName))
                .build()
                .render(MYBATIS3);
        return userMapper.selectMany(ssp);



    }
}
