package cn.itcast.springbootstudy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonPropertyOrder(value = {"content","title"})
public class ArticleVO implements Serializable {

    private static final long serialVersionUID = -4495466809361347031L;
    /**
     * id : 1
     * author : zimug
     * title : 手摸手教你kaifaspringboot
     * content : c
     * createTime :
     * reader : [{"name":"zimug","age":18},{"name":"kobe","age":37}]
     */
    //@JsonIgnore
    private Long id;

    //@JsonProperty("auther")
    private String author;
    private String title;

    @NotEmpty(message = "文章内容不能为空")/*校验注解中的message属性会在校验失败抛出异常时赋值给默认defaulMessage属性*/
    private String content;

    // @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private List<Reader> reader;
}