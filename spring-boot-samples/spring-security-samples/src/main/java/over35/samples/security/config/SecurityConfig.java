package over35.samples.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import over35.samples.security.properties.SecurityProperties;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: daibin
 * @date: 2021/7/28 3:01 下午
 */
@EnableConfigurationProperties
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void configure(WebSecurity web) throws Exception {
        String[] urls = securityProperties.getIgnore().getUrls();

        if (urls != null) {
            web.ignoring().mvcMatchers(urls);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String loginUrl = "/auth/login";

        http.authorizeRequests()
//                匹配的允许提交
//                .antMatchers(loginUrl).permitAll()
//                  其余的需要登录认证
                .anyRequest().authenticated();

        http.logout().logoutUrl("/logout").permitAll();
        http.oauth2Login().loginProcessingUrl(loginUrl).permitAll();

//                http
//                .formLogin()
////                登录处理
//                .loginProcessingUrl(loginUrl)
////                登录页面
//                .loginPage("/login.html")
////                登录失败跳转
//                .failureForwardUrl("/failure.html")
////                登录成功调整
//                .successForwardUrl("/success.html")
////                  登录的用户名参数名
//                .usernameParameter("username")
////                  登录的密码参数名
//                .passwordParameter("password");

        http.cors().disable().csrf().disable();

        http.addFilter(usernamePasswordAuthenticationFilter());
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        return new UsernamePasswordAuthenticationFilter(authenticationManagerBean());
    }

    @ConfigurationProperties(prefix = "security")
    @Bean
    SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

    @Bean
    ClientRegistrationRepository clientRegistrationRepository() {
        List<String> clients = Arrays.asList("weixin", "alipay");

        List<ClientRegistration> registrations = clients.stream()
                .map(c -> getRegistration(c))
                .filter(registration -> registration != null)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository();
    }

    private ClientRegistration getRegistration(String client) {

        if ("weixin".equals(client)) {
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
//                    .clientId()
//                    .clientSecret()
                    .build();
        }
        if ("alipay".equals(client)) {
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
//                    .clientId()
//                    .clientSecret()
                    .build();
        }
        return null;
    }

}
