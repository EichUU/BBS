package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyUpService;
import vo.ActionForward;

public class DogCartQtyUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String kind=request.getParameter("kind");	//수량을 증가시킬 대상이 되는 장바구니 항목의 kind값을 파라미터로 받는다
		
		DogCartQtyUpService dogCartQtyUpService=new DogCartQtyUpService();
		dogCartQtyUpService.upCartQty(kind, request); //장바구니 수량을 증가시키는 메소드를 호출
		
		ActionForward forward=new ActionForward(); 
		forward.setPath("dogCartList.dog");	//포워딩 페이지
		forward.setRedirect(true);				
		
		return forward;
	}

}
