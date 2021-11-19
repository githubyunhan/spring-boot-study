package cn.itcast.springbootstudy.config.exception;

public class ModelViewException extends RuntimeException {/*新定义一个异常类ModelViewException*/
    //异常错误信息
    private int code;
    //异常信息
    private String message;

    public static ModelViewException transfer(CustomException e){
        return new ModelViewException(e.getCode(),e.getMessage());
    }

    private ModelViewException(int code,String message){
        this.code=code;
        this.message=message;
    }

    public int getCode(){
        return code;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
