package cn.itcast.springbootstudy.model.yaml;


import cn.itcast.springbootstudy.config.MixPropertySourceFactory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
//@Validated/*与@ConfigurationProperties共同使用，通过对属性添加注解进行属性校验判断*/
@Component
@ConfigurationProperties(prefix = "family")/*与@Component一起使用，批量注入属性*/
@PropertySource(value = {"classpath:family.yml"},factory = MixPropertySourceFactory.class)/*导入外部配置文件，.properties文件不使用factory属性，与@Component和@ConfigurationProperties共同使用*/
public class Family {
    //@Value("${family.familyName}")/*与@Component一起使用，注入单一属性*/
    private String familyName;
    private Father father;
    private Mother mother;
    private Child child;
}