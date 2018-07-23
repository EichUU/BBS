package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartListService { // 장바구니 목록보기 요청을 처리하는 비즈니스 로직

	public ArrayList<Cart> getCartList(HttpServletRequest request){
		
		HttpSession session=request.getSession();
		ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList"); // 세션영역에 공유되어 있는 장바구니 목록 객체를 얻어옴
		
		return cartList;
	}
}
