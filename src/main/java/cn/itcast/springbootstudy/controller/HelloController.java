package cn.itcast.springbootstudy.controller;

import cn.itcast.springbootstudy.config.exception.AjaxResponse;
import cn.itcast.springbootstudy.model.ArticleVO;
import cn.itcast.springbootstudy.service.ExceptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class HelloController {
    @Resource
    ExceptionService exceptionService;

    @RequestMapping("/hello")
    public ArticleVO hello(){
        /*ArticleVO article=new ArticleVO(1L,"zimuge");
        article.setAuthor("字母哥");*/
        ArticleVO article1= ArticleVO.builder().id(3L).author("莫言").build();
        log.info("测试一下"+article1);
        return article1;
    }

    @RequestMapping("/ex/system")
    public @ResponseBody AjaxResponse system(){
        exceptionService.systemBizError();
        return AjaxResponse.success();
    }

    @RequestMapping("/ex/user")
    public @ResponseBody AjaxResponse user(Integer input){
        return AjaxResponse.success(exceptionService.userBizError(input));
    }
}