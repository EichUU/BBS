package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class DogRegistFormAction implements Action {  // 새로운 개 상품 정보 등록 페이지를 보여주는 요청을 처리

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false); 
		forward.setPath("dogRegistForm.jsp");
				
		return forward;
	}

}	
