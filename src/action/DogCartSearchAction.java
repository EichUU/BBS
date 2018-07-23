package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartSearchService;
import vo.ActionForward;
import vo.Cart;

public class DogCartSearchAction implements Action {	// 가격으로 장바구니 항목을 검색

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		DogCartSearchService dogCartSearchService=new DogCartSearchService();
		
		int startMoney=Integer.parseInt(request.getParameter("startMoney"));
		int endMoney=Integer.parseInt(request.getParameter("endMoney"));
		ArrayList<Cart> cartList=dogCartSearchService.getCartSearchList(startMoney, endMoney, request);
		
		request.setAttribute("cartList", cartList);
		request.setAttribute("startMoney", startMoney);
		request.setAttribute("endMoney", endMoney);
		
		int totalMoney=0;
		int money=0;
		
		for(int i=0; i<cartList.size(); i++) {
			money=cartList.get(i).getPrice()*cartList.get(i).getQty();
			totalMoney+=money;
		}
		
		request.setAttribute("totalMoney", totalMoney);
		
		ActionForward forward=new ActionForward(); 
		forward.setPath("dogCartList.jsp");	//포워딩 페이지
		forward.setRedirect(false);	//포워딩 방식은 리다이렉트방식으로		
		
		return forward;
	}

}
