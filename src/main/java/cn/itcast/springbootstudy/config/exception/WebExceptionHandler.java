package cn.itcast.springbootstudy.config.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice/*表明当前的类是一个全局的异常处理类*/
public class WebExceptionHandler {

    @ExceptionHandler(ModelViewException.class)
    public ModelAndView viewExceptionHandler(HttpServletRequest req,ModelViewException e){
        ModelAndView modelAndView = new ModelAndView();

        //将异常信息设置
        modelAndView.addObject("exception",e);
        modelAndView.addObject("url",req.getRequestURL());
        modelAndView.setViewName("error");/*跳转到error.html页面*/

        //返回ModelAndView
        return modelAndView;
    }

    /*数据校验失败，会抛出MethodArgumentNotValidException和BindException异常，因此需要做全局处理*/
    @ExceptionHandler(MethodArgumentNotValidException.class)/*参数校验错误异常处理器*/
    @ResponseBody
    public AjaxResponse customerException(MethodArgumentNotValidException e){
        FieldError fieldError = e.getBindingResult().getFieldError();
        return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,fieldError.getDefaultMessage()));
    }

    @ExceptionHandler(BindException.class)/*绑定异常（约束异常）异常处理器*/
    @ResponseBody
    public AjaxResponse customerException(BindException e){
        FieldError fieldError = e.getBindingResult().getFieldError();
        return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,fieldError.getDefaultMessage()));
    }

    @ExceptionHandler(CustomException.class)/*自定义异常处理器*/
    @ResponseBody
    public AjaxResponse customerException(CustomException e){
        if (e.getCode()==CustomExceptionType.SYSTEM_ERROR.getCode()){
            //400异常不需要持久化，将异常信息以友好的方式告知用户就可以
            //TODO 将500异常信息持久化处理，方便运维人员处理
        }
        return AjaxResponse.error(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResponse exception(Exception e){
        //TODO 将异常信息持久化处理，方便运维人员处理
        //没有被程序员发现，并转换为CustomException的异常，都是其他异常或者未知异常
        return AjaxResponse.error(new CustomException(CustomExceptionType.OTHER_ERROR,"未知异常"));
    }
}
