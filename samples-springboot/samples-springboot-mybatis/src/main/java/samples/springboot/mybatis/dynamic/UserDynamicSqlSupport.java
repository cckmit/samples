package samples.springboot.mybatis.dynamic;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

/**
 * @author: daibin
 * @date: 2021/8/6 10:09 下午
 */
public final class UserDynamicSqlSupport {
//
    public static final User user = new User();
    public static final SqlColumn<String> username = user.username;
    public static final SqlColumn<String> password = user.password;
    public static final SqlColumn<Boolean> enabled = user.enabled;

    public static final class User extends SqlTable {
        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);
        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);
        public final SqlColumn<Boolean> enabled = column("enabled", JDBCType.BOOLEAN);

        protected User() {
            super("t_user");
        }
    }
}
