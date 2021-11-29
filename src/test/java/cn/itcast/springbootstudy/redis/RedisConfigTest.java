package cn.itcast.springbootstudy.redis;

import cn.itcast.springbootstudy.model.Address;
import cn.itcast.springbootstudy.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /*@Autowired
    private RedisTemplate redisTemplate;*/

    @Resource(name = "redisTemplate")
    private ValueOperations<String,Object> valueOperations;

    @Resource(name = "redisTemplate")
    private HashOperations<String,String,Object> hashOperations;

    @Resource(name = "redisTemplate")
    private ListOperations<String,Object> listOperations;

    @Resource(name = "redisTemplate")
    private SetOperations<String,Object> setOperations;

    @Resource(name = "redisTemplate")
    private ZSetOperations<String,Object> zSetOperations;

    @Test
    public void testValueObj() throws Exception{
        Person person = new Person("kobe", "bvrant");
        person.setAddress(new Address("南京","中国"));
        /*ValueOperations<String,Object> operations=redisTemplate.opsForValue();
        operations.set("player:1",person,20, TimeUnit.SECONDS);*/
        //valueOperations.set("player:1",person,20, TimeUnit.SECONDS);//20秒之后数据消失
        valueOperations.set("player:1",person);

        Person getBack=(Person) valueOperations.get("player:1");
        System.out.println(getBack);
    }

    @Test
    public void testSetOperations() throws Exception{
        Person person = new Person("kobe", "bvrant");
        Person person2 = new Person("curry", "stephen");

        setOperations.add("playerset",person,person2);
        Set<Object> result = setOperations.members("playerset");

        System.out.println(result);
    }

    @Test
    public void testHashOperations() throws Exception{
        Person person = new Person("kobe", "bvrant");
        //hset "hash:player" "firstname" person.getFirstname()
        hashOperations.put("hash:player","firstname",person.getFirstname());
        hashOperations.put("hash:player","lastname",person.getLastname());
        hashOperations.put("hash:player","address",person.getAddress());

        System.out.println(hashOperations.get("hash:player","firstname"));
    }

    @Test
    public void testListOperations() throws Exception{
        listOperations.leftPush("list:player",new Person("kebo","byrant"));
        listOperations.leftPush("list:player",new Person("jordan","mikel"));
        listOperations.leftPush("list:player",new Person("curry","stephen"));

        System.out.println(listOperations.leftPop("list:player"));
    }

    @Resource(name = "redisTemplate")
    private HashOperations<String,String,Object> jacksonHashOperations;
    private HashMapper<Object,String,Object> jackson2HashMapper=new Jackson2HashMapper(false);
    @Test
    public void testHashPutAll(){
        Person person = new Person("kobe", "bvrant");
        person.setId("10");
        person.setAddress(new Address("南京","中国"));

        //将对象以hash的形式放入数据库
        Map<String, Object> mappedHash = jackson2HashMapper.toHash(person);
        jacksonHashOperations.putAll("player"+person.getId(),mappedHash);

        //将对象从数据库取出来
        Map<String, Object> loadedHash = jacksonHashOperations.entries("player" + person.getId());
        Object map = jackson2HashMapper.fromHash(loadedHash);
        Person getback = new ObjectMapper().convertValue(map, Person.class);
        Assert.assertEquals(person.getFirstname(),getback.getFirstname());
    }
}