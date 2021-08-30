package samples.springboot.mybatis.repository.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class OrderDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Order order = new Order();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = order.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Short> type = order.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Short> state = order.state;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Order extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Short> type = column("type", JDBCType.SMALLINT);

        public final SqlColumn<Short> state = column("state", JDBCType.SMALLINT);

        public Order() {
            super("t_order_0");
        }
    }
}