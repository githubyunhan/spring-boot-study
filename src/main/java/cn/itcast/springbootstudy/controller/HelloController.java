package cn.itcast.springbootstudy.controller;

import cn.itcast.springbootstudy.model.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public ArticleVO hello(){
        /*ArticleVO article=new ArticleVO(1L,"zimuge");
        article.setAuthor("字母哥");*/
        ArticleVO article1= ArticleVO.builder().id(3L).author("莫言").build();
        log.info("测试一下"+article1);
        return article1;
    }
}