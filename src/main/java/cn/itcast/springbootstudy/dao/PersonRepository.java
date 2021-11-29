package cn.itcast.springbootstudy.dao;

import cn.itcast.springbootstudy.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,String> {

    /*继承CrudRepository，获取基本的CRUD操作*/
}
