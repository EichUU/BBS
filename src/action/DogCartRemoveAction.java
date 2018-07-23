package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartRemoveService;
import vo.ActionForward;

public class DogCartRemoveAction implements Action {	// 장바구니 항목 삭제 요청을 처리

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String[] kindArray=request.getParameterValues("remove");
		
		DogCartRemoveService dogCartRemoveService=new DogCartRemoveService();
		dogCartRemoveService.cartRemove(request, kindArray);
		
		ActionForward forward=new ActionForward(); 
		forward.setPath("dogCartList.jsp");	//포워딩 페이지
		forward.setRedirect(true);	//포워딩 방식은 리다이렉트방식으로
		
		return forward;
		
		
	}

}
