package samples.springboot.shardingjdbc.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import samples.springboot.shardingjdbc.model.Order;

import java.util.List;

/**
 * @author: daibin
 * @date: 2021/7/31 2:20 上午
 */
public interface OrderMapper {


    @Insert(value = " insert into t_order (id,type,state) values (2,#{order.type},#{order.state})")
    int insert(@Param("order") Order order);

    @Select("select * from t_order")
    List<Order> selectAll();

    @Select("select id,type,state from t_order where id = #{id}")
    Order selectByPrimaryKey(Long id);
}
