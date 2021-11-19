package cn.itcast.springbootstudy.controller;

import cn.itcast.springbootstudy.config.exception.AjaxResponse;
import cn.itcast.springbootstudy.model.ArticleVO;
import cn.itcast.springbootstudy.service.ArticleRestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/rest")
public class ArticleRestController {

    @Resource(name="articleMybatisRestServiceImpl")
    ArticleRestService articleRestService;


    @ApiOperation(value = "添加文章", notes = "添加新的文章", tags = "ArticleVO",httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code=200,message="成功",response=AjaxResponse.class),
            @ApiResponse(code=400,message="用户输入错误",response=AjaxResponse.class),
            @ApiResponse(code=500,message="系统内部错误",response=AjaxResponse.class)
    })
    //@RequestMapping(value = "/article", method = POST, produces = "application/json")
    @PostMapping("/article")
    public @ResponseBody  AjaxResponse saveArticle(@RequestBody ArticleVO article) {
    /*public @ResponseBody  AjaxResponse saveArticle(@RequestParam String  id,
                                                   @RequestParam String  author) {*/

        log.info("saveArticle：{}",article);

        log.info("articleRestService return :" + articleRestService.saveArticle(article));

        return  AjaxResponse.success(article);
    }

    //@RequestMapping(value = "/article/{id}",method = DELETE,produces = "application/json")
    @DeleteMapping("/article/{id}")
    public @ResponseBody AjaxResponse deleteArticle(@PathVariable Long id){
        articleRestService.deleteArticle(id);
        return AjaxResponse.success(id);
    }

    //@RequestMapping(value = "/article/{id}",method = PUT,produces = "application/json")
    @PutMapping("/article/{id}")
    public @ResponseBody AjaxResponse updateArticle(@PathVariable Long id, @Valid @RequestBody ArticleVO article){
        article.setId(id);
        articleRestService.updateArticle(article);
        return AjaxResponse.success();
    }

    //@RequestMapping(value = "/article/{id}",method = GET,produces = "application/json")
    @GetMapping("/article/{id}")
    public @ResponseBody AjaxResponse getArticle(@PathVariable Long id){
        return AjaxResponse.success(articleRestService.getArticle(id));
    }

    @GetMapping("/articles")
    public @ResponseBody AjaxResponse getAllArticle(){
        return AjaxResponse.success(articleRestService.getAll());
    }
}
/*@Valid与@Validate的区别
1.在需要注解的“属性装配类”上加@Validate注解，只有加上才会触发数据校验，与@ConfigurationProperties共同进行属性验证
2.@Validate是@Valid的一次封装，是spring提供的校验机制使用，@Valid不提供分组功能
3.在需要校验的地方（方法）加上@Valid，在需要加上约束的属性上加上校验提供的约束注解
注：校验注解中的message属性会在校验失败抛出异常时赋值给默认defaulMessage属性*/