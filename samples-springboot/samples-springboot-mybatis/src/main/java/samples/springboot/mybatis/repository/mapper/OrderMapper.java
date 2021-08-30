package samples.springboot.mybatis.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;
import samples.springboot.mybatis.repository.model.Order;

import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface OrderMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(OrderDynamicSqlSupport.id, OrderDynamicSqlSupport.type, OrderDynamicSqlSupport.state);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Order> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OrderResult")
    Optional<Order> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OrderResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.SMALLINT),
        @Result(column="state", property="state", jdbcType=JdbcType.SMALLINT)
    })
    List<Order> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, OrderDynamicSqlSupport.order, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, OrderDynamicSqlSupport.order, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(OrderDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Order record) {
        return MyBatis3Utils.insert(this::insert, record, OrderDynamicSqlSupport.order, c ->
            c.map(OrderDynamicSqlSupport.type).toProperty("type")
            .map(OrderDynamicSqlSupport.state).toProperty("state")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Order record) {
        return MyBatis3Utils.insert(this::insert, record, OrderDynamicSqlSupport.order, c ->
            c.map(OrderDynamicSqlSupport.type).toPropertyWhenPresent("type", record::getType)
            .map(OrderDynamicSqlSupport.state).toPropertyWhenPresent("state", record::getState)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Order> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, OrderDynamicSqlSupport.order, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Order> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, OrderDynamicSqlSupport.order, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Order> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, OrderDynamicSqlSupport.order, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Order> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(OrderDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, OrderDynamicSqlSupport.order, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Order record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OrderDynamicSqlSupport.type).equalTo(record::getType)
                .set(OrderDynamicSqlSupport.state).equalTo(record::getState);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Order record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OrderDynamicSqlSupport.type).equalToWhenPresent(record::getType)
                .set(OrderDynamicSqlSupport.state).equalToWhenPresent(record::getState);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Order record) {
        return update(c ->
            c.set(OrderDynamicSqlSupport.type).equalTo(record::getType)
            .set(OrderDynamicSqlSupport.state).equalTo(record::getState)
            .where(OrderDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Order record) {
        return update(c ->
            c.set(OrderDynamicSqlSupport.type).equalToWhenPresent(record::getType)
            .set(OrderDynamicSqlSupport.state).equalToWhenPresent(record::getState)
            .where(OrderDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}