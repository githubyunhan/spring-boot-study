package cn.itcast.springbootstudy.config.exception;

import lombok.Data;

@Data
public class AjaxResponse {

    private boolean isok;
    private int code;
    private String message;
    private Object data;

    public AjaxResponse() {
    }

    //请求出现异常时的响应数据封装
    public static AjaxResponse error(CustomException e){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(e.getCode());
        if (e.getCode()== CustomExceptionType.USER_INPUT_ERROR.getCode()){
            resultBean.setMessage(e.getMessage());
        }else if (e.getCode()== CustomExceptionType.SYSTEM_ERROR.getCode()){
            resultBean.setMessage(e.getMessage()+"系统出现异常。请联系管理电话：123456789进行处理");
        }else {
            resultBean.setMessage("系统出现未知异常。请联系管理电话：987654321进行处理");
        }
        return resultBean;
    }

    public static AjaxResponse success() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }

    public static AjaxResponse success(Object data) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }
}