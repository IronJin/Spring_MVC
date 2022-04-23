package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

//롬복을 사용한 게터 세터 설정
@Getter @Setter
public class HelloData {

    private String username;
    private int age;


}
