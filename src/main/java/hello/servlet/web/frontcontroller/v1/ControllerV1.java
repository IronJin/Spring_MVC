package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//여러가지 기능을 하는 컨트롤러를 구현하기 위해 인터페이스로 구현
public interface ControllerV1 {

    void process(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException;

}
