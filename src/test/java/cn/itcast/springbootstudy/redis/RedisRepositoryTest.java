package cn.itcast.springbootstudy.redis;

import cn.itcast.springbootstudy.dao.PersonRepository;
import cn.itcast.springbootstudy.model.Address;
import cn.itcast.springbootstudy.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisRepositoryTest {
/*使用RedisRepository，在项目入口方法加上注解@EnableRedisRepositories*/
    @Autowired
    PersonRepository personRepository;

    @Test
    public void test(){
        Person rand = new Person("yunhan", "hanhan");
        rand.setAddress(new Address("xian","中国"));
        personRepository.save(rand);
        Optional<Person> op = personRepository.findById(rand.getId());
        Person p2 = op.get();
        personRepository.count();
        //personRepository.delete(rand);
    }

    @Test
    public void testDelete(){
        personRepository.deleteById("af283bf4-994f-4c57-9676-4eb1c68c464f");

    }
}
