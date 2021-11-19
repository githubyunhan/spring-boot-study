package cn.itcast.springbootstudy.config.exception;

import java.lang.annotation.*;
/*ModelView注解，只起到标记的作用*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})//只能在方法上使用此注解
public @interface ModelView {

}
