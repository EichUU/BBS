package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyDownService;
import vo.ActionForward;

public class DogCartQtyDownAction implements Action {	// 장바구니 항목의 수량을 감소시키는 요청을 처리

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String kind=request.getParameter("kind");
		
		DogCartQtyDownService dogCartQtyDownService=new DogCartQtyDownService();
		dogCartQtyDownService.downCartQty(kind, request);
		
		ActionForward forward=new ActionForward(); 
		forward.setPath("dogCartList.jsp");	//포워딩 페이지
		forward.setRedirect(true);	//포워딩 방식은 리다이렉트방식으로		
		
		return forward;
	}

	
}
