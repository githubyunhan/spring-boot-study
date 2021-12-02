package cn.itcast.springbootstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@ImportResource(locations = {"classpath:beans.xml"})//导入外部配置文件，.xml文件
@MapperScan(basePackages = {"cn.itcast.springbootstudy.generator"})
@EnableCaching/*开启缓存*/
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30 * 60 * 1000)/*Session有效期是30分钟在启动类上加上该注解：配置启用redis的httpSession*/
public class SpringBootStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }

}