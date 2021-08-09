package over35.samples.redisson.repository.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import over35.samples.redisson.model.Stock;

/**
 * @author: daibin
 * @date: 2021/8/3 12:14 下午
 */
public interface StockMapper {

    /**
     * @return
     */
    @Select("select * from t_stock where product_id = #{productId}")
    Stock queryByProductId(Long productId);

    /**
     * 增加记录
     *
     * @param stock
     * @return
     */
    @Insert("insert into t_stock(id,productId,quantity) values(#{id},#{productId},#{quantity});")
    int save(Stock stock);

    /**
     * 更新库存数量
     *
     * @param stockId
     * @param quantity
     * @return
     */
    @Update("UPDATE t_stock SET quantity = #{quantity} WHERE id = #{stockId}")
    int updateQuantityById(@Param("stockId") Long stockId,@Param("quantity") Long quantity);
}
