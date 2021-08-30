package samples.springboot.fluentmybatis.repository.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import cn.org.atool.fluent.mybatis.metadata.DbType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * RoleEntity: 数据映射实体定义
 *
 * @author Powered By Fluent Mybatis
 */
@Data
@Accessors(
    chain = true
)
@EqualsAndHashCode(
    callSuper = false
)
@FluentMybatis(
    table = "T_ROLE",
    dbType = DbType.H2
)
public class RoleEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   */
  @TableField("ROLE")
  private String role;

  /**
   */
  @TableField("USERNAME")
  private String username;

  @Override
  public final Class<? extends IEntity> entityClass() {
    return RoleEntity.class;
  }
}
