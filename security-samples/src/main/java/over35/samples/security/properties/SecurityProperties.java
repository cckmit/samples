package over35.samples.security.properties;

import lombok.Data;

/**
 * @author: daibin
 * @date: 2021/7/28 3:07 下午
 */
@Data
public class SecurityProperties {

    private Ignore ignore;

    private Login login;

    @Data
    public static class Ignore {

        private String[] urls;
    }

    @Data
    public static class Login {

        private String loginUrl;
    }
}
