package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public interface Action {

	ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//각 요청을 처리하는 Action 클래스들을 다형성을 이용해서 동일한 타입으로 참조하기위해 Action 인터페이스를 설계
	//각 요청을 처리하는 Action 클래스들이 공통적으로 구현해야하는 execute 메소드를 정의
	//웹 요청을 처리하고 응답하기 위해서 HttpServletRequest request와 HttpServletResponse response를 파라미터 변수로 처리
}
