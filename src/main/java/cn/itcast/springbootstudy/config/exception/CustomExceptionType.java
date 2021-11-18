package cn.itcast.springbootstudy.config.exception;

public enum CustomExceptionType {/*异常分类的枚举，把异常分类固化下来*/

    USER_INPUT_ERROR(400,"用户输入异常"),
    SYSTEM_ERROR(500,"系统服务异常"),
    OTHER_ERROR(999,"其他未知异常");

    CustomExceptionType(int code,String typeDesc){
        this.code=code;
        this.typeDesc=typeDesc;
    }

    private String typeDesc;

    private int code;

    public String getTypeDesc(){
        return typeDesc;
    }

    public int getCode(){
        return code;
    }
}
