package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DogDAO;
import vo.Cart;
import vo.Dog;

public class DogCartAddService {	// 새로운 장바구니 항목을 추가하는 요청을 처리하는 비즈니스 로직을 구현

	public Dog getCartDog(int id) { //파라미터 값으로 전송된 id값을 가지고 있는 개 상품 정보를 얻어온다
		
		DogDAO dogDAO=DogDAO.getInstance();
		Connection conn=getConnection();
		dogDAO.setConnection(conn);
		
		Dog dog=dogDAO.selectDog(id);
		
		close(conn);
			
		return dog;
	}
	
	public void addCart(HttpServletRequest request, Dog cartDog){	//장바구니 항목을 추가하는 메소드
		
		HttpSession session=request.getSession();
		ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList"); //현재 세션영역에 저장되어 있는 장바구니 목록을 얻어옴
		
		if(cartList==null) {	//장바구니 요청을 처음 실행하는 경우에 해당 객체를 세션 영역의 속성을 공유해준다
			cartList=new ArrayList<Cart>();
			session.setAttribute("cartList", cartList);
		}
		
		boolean isNewCart=true; //지금 장바구니에 담는 항목이 새로 추가되는 항목인지, 이미 존재하는 항목인지를 판단하고 저장할 변수
								//true 는 기본적으로 요청에서 지정한 항목이 처음으로 추가되는 장바구니 항목으로 지정되게 한다
		for(int i=0; i<cartList.size(); i++) {
			if(cartDog.getKind().equals(cartList.get(i).getKind())) {
				isNewCart=false;
				cartList.get(i).setQty(cartList.get(i).getQty()+1);
				break;
			}
		}
		
		if(isNewCart) {
			Cart cart=new Cart();
			cart.setImage(cartDog.getImage());
			cart.setKind(cartDog.getKind());
			cart.setPrice(cartDog.getPrice());
			cart.setQty(1);
			cartList.add(cart);
			
		}
		
		
		
	}
}
