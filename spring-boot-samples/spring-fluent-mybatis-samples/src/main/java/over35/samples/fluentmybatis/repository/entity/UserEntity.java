package over35.samples.fluentmybatis.repository.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import cn.org.atool.fluent.mybatis.metadata.DbType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * UserEntity: 数据映射实体定义
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
    table = "T_USER",
    dbType = DbType.H2
)
public class UserEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   */
  @TableId(
      value = "USERNAME",
      auto = false
  )
  private String username;

  /**
   */
  @TableField("ENABLED")
  private Boolean enabled;

  /**
   */
  @TableField("PASSWORD")
  private String password;

  @Override
  public Serializable findPk() {
    return this.username;
  }

  @Override
  public final Class<? extends IEntity> entityClass() {
    return UserEntity.class;
  }
}
