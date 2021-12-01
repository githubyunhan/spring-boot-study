package cn.itcast.springbootstudy.service;

import cn.itcast.springbootstudy.generator.testdb.Article;
import cn.itcast.springbootstudy.generator.testdb.ArticleMapper;
import cn.itcast.springbootstudy.model.ArticleVO;
import cn.itcast.springbootstudy.utils.DozerUtils;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ArticleMybatisRestServiceImpl implements ArticleRestService{

    @Resource
    protected Mapper dozerMapper;

    @Resource
    private ArticleMapper articleMapper;


    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(value = "article",key = "#article.getId()"),
                    @CacheEvict(value = "articleAll",allEntries = true)
            }
    )
    /*@Caching(
            evict = {
                    @CacheEvict(value = "articleAll",allEntries = true)
            },
            put={@CachePut(value = "article",key = "#article.getId()")}
    )*/
    public ArticleVO saveArticle(ArticleVO article) {
        Article articlePO = dozerMapper.map(article,Article.class);
        articleMapper.insert(articlePO);

        //TODO 把reader存到数据库里(redis 返回值数据类型要和数据库的保持一致)
        article.setId(articlePO.getId());
        //int i=5/0;
        return article;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "article",key = "#id"),
                    @CacheEvict(value = "articleAll",allEntries = true)
            }
    )
    public void deleteArticle(Long id) {
        articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "article",key = "#article.getId()"),
                    @CacheEvict(value = "articleAll",allEntries = true)
            }
    )
    public ArticleVO updateArticle(ArticleVO article) {
        Article articlePO = dozerMapper.map(article,Article.class);
        articleMapper.updateByPrimaryKeySelective(articlePO);

        //TODO 把reader存到数据库里(redis 返回值数据类型要和数据库的保持一致)
        article.setId(articlePO.getId());
        return article;
    }

    @Override
    @Cacheable(value = "article",key = "#id",condition = "#id>3")
    public ArticleVO getArticle(Long id) {
        //TODO 把读者信息查询出来赋值给ArticleVO(redis 返回值数据类型要和数据库的保持一致)
        return dozerMapper.map(articleMapper.selectByPrimaryKey(id),ArticleVO.class);
    }

    @Override
    @Cacheable(value = "articleAll")
    public List<ArticleVO> getAll() {
        List<Article> articles = articleMapper.selectByExample(null);
        return DozerUtils.mapList(articles,ArticleVO.class);
    }
}