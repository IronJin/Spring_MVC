package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v2.controllder.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controllder.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controllder.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 프로그램 흐름
 * Map에 컨트롤러 구현부에 해당하는 url 을 넣어놓고 그 값으로 각자 객체를 넣어준다.
 * requestURI 를 통해 받아온 URL 을 맵에서 꺼내서 인터페이스 구현체로 변수를 선언해준다
 * 그 후 각자 구현체에 해당하는 PROCESS 메소드를 호출해준다.
 */
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    //key, value 로 path를 요청하면 객체를 생성하는 구조임
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        String requestURI = request.getRequestURI();
        ControllerV2 controller = controllerMap.get(requestURI);
        //예외처리
        if(controller == null){
            //404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(request, response);
        view.render(request, response);

    }
}
