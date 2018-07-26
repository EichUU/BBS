package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartRemoveService {	// 장바구니 항목 삭제 요청을 처리하는 비즈니스 로직

	public void cartRemove(HttpServletRequest request, String[] kindArray) {	
														//동시에 여러개의 장바구니 항목을 삭제할 수 있기 때문에 장바구니 항목의 kind를 배열로		
		
		HttpSession session=request.getSession();
		ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");
		
		for(int i=0; i<kindArray.length; i++) {	//클라이언트가 삭제할 대상으로 선택한 항목의 kind 값들을 반복해서 처리
			
			for(int j=0; j<cartList.size(); j++) {
				
				if(cartList.get(j).getKind().equals(kindArray[i])) {
					cartList.remove(cartList.get(j));					
					
				}
			}
		}
	}
}
