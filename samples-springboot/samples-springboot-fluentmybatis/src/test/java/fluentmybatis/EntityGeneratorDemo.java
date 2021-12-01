package fluentmybatis;

import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;
import cn.org.atool.generator.database.DbTypeOfGenerator;
import org.junit.jupiter.api.Test;

/**
 * @author: daibin
 * @date: 2021/7/29 10:16 上午
 */
public class EntityGeneratorDemo {

    public static final String url = "jdbc:h2:file:~/h2/db-samples;MODE=MYSQL";

    @Test
    public void generate() throws Exception {
        FileGenerator.build(Empty.class);
    }


    @Tables(
            dbType = DbTypeOfGenerator.H2,
            driver = "org.h2.Driver",
            schema = "TEST.PUBLIC",
            // 设置数据库连接信息
            url = url, username = "test", password = "",
            // 设置entity类生成src目录, 相对于 user.dir
            srcDir = "src/main/java",
            // 设置entity类的package值
            basePack = "samples.fluentmybatis.repository",
            // 设置dao接口和实现的src目录, 相对于 user.dir
            daoDir = "src/main/java",
            tablePrefix = {"T_"},
            // 设置哪些表要生成Entity文件
            tables = {@Table(value = {"T_USER"}), @Table(value = {"T_ROLE"}), @Table(value = {"T_PERMISSION"})}
    )
    static class Empty {
    }
}
