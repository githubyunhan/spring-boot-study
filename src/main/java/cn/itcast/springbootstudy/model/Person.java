package cn.itcast.springbootstudy.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
@Data
@NoArgsConstructor
@RedisHash("people")/*set people Id,在Person中Id为Key*/
public class Person implements Serializable {
    private static final long serialVersionUID=-8985545025228238754L;

    @Id
    String id;
    String firstname;
    String lastname;
    Address address;

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
