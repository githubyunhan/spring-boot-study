package cn.itcast.springbootstudy.config;

import cn.itcast.springbootstudy.model.ArticleVO;
import cn.itcast.springbootstudy.service.ArticleRestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/template")
public class TemplateController {

    @Resource(name = "articleMybatisRestServiceImpl")
    ArticleRestService articleRestService;

    @GetMapping("/freemarker")
    public String index(String name, Model model){
        List<ArticleVO> articles = articleRestService.getAll();
        model.addAttribute("articles",articles);
        //模板名称，实际的目录为：resources/templates/freemarkertemp.html
        return "freemarkertemp";
    }
}