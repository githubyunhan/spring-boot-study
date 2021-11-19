package cn.itcast.springbootstudy.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect//方向
@Component
@Slf4j
public class ModelViewAspect {/*以@ModelView注解为切入点，面向切面编程*/
    //设置切入点：这里直接拦截被@ModelView注解的方法
    @Pointcut("@annotation(cn.itcast.springbootstudy.config.exception.ModelView)")
    public void pointcut(){

    }

    /*
    * 当有ModelView的注解的方法抛出异常的时候，做如下的处理
    * */
    @AfterThrowing(pointcut = "pointcut()",throwing = "e")
    public void afterThrowable(Throwable e){
        log.error("切面发生了异常:",e);
        if (e instanceof CustomException){
            throw ModelViewException.transfer((CustomException) e);
        }
    }
}
