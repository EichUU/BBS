package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogViewService;
import vo.ActionForward;
import vo.Dog;

public class DogViewAction implements Action {	//특정 개 상품의 상세 정보보기 요청을 처리

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		DogViewService dogViewService=new DogViewService();		
		int id=Integer.parseInt(request.getParameter("id")); //상품상세정보를 출력할 대상 개 상품의 id값을 파라미터로 받는다
		Dog dog=dogViewService.getDogView(id);	//파라미터로 전송된 id값을 가지고 있는 개의 정보를 Dog클래스 객체 타입으로 반환받는다
		request.setAttribute("dog", dog);	//request영역에 dog객체를 송성으로 공유한다
		
		Cookie todayImageCookie=new Cookie("today"+id, dog.getImage());
			//개 상품 정보의 이미지 이름 문자열을 today문자열 뒤에 해당 개 상품의 id값을 연결하여 ("today"+id)쿠키이름을 지정한 후 쿠키 객체를 생성하여 저장한다
		todayImageCookie.setMaxAge(60*60*24);//오늘 본 상품 이미지를 저장한 쿠키 객체가 클라이언트 시스템에 저장되어 있을 기간을 24시간으로 설정
		response.addCookie(todayImageCookie);	//응답에 쿠키 객체를 추가
		
		ActionForward forward=new ActionForward();
		forward.setPath("dogView.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
