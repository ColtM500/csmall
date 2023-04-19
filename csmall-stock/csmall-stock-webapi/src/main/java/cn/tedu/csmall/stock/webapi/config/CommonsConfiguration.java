package cn.tedu.csmall.stock.webapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//当前类是配置Spring扫描环境的配置类 必须添加@Configuration注解
@Configuration
//扫描全局异常处理类所在的包 也就是commons模块中的exception包，使其生效
@ComponentScan("cn.tedu.csmall.commons")
public class CommonsConfiguration {
}
