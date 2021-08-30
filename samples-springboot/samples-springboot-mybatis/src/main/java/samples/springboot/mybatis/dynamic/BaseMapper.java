package samples.springboot.mybatis.dynamic;

import org.mybatis.dynamic.sql.util.mybatis3.*;

/**
 * @author: daibin
 * @date: 2021/8/6 9:24 下午
 */
public interface BaseMapper<T> extends CommonInsertMapper<T>, CommonDeleteMapper, CommonUpdateMapper, CommonSelectMapper, CommonCountMapper {
}
