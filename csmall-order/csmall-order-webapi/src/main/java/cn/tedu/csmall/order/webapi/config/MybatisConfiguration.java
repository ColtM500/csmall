package cn.tedu.csmall.order.webapi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//Mybatis框架要求扫描指定的包 包中生成操作数据库接口的实现类
@MapperScan("cn.tedu.csmall.order.webapi.mapper")
public class MybatisConfiguration {
}
