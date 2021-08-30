package samples.tools;

import cn.hutool.core.bean.BeanUtil;
import org.yaml.snakeyaml.Yaml;
import samples.tools.model.User;

import java.util.Map;

/**
 * @author: daibin
 * @date: 2021/8/21 11:33 上午
 */
public class YamlSamples {

    public static void main(String[] args) {
        Yaml yaml = new Yaml();

        Map<String, Object> map = yaml.load(ClassLoader.getSystemResourceAsStream("samples.yaml"));

        String prefix = "conf.user";

        Map<String, Object> valueMap = null;
        for (String name : prefix.split("\\.")) {
            if (valueMap == null) {
                valueMap = map;
            }
            Object value = valueMap.get(name);
            if (value instanceof Map) {
                valueMap = (Map<String, Object>) value;
            }
        }
        System.out.println(valueMap);

        User user = BeanUtil.toBean(valueMap, User.class);
        System.out.println(user);


    }
}
