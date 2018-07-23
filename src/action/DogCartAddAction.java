package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartAddService;
import vo.ActionForward;
import vo.Dog;

public class DogCartAddAction implements Action {	// 장바구니 담기 요청을 처리

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		DogCartAddService dogCartAddService=new DogCartAddService();	//장바구니 서비스 객체 생성
		int id=Integer.parseInt(request.getParameter("id"));	//장바구니 항목으로 추가될 개 상품의 id를 파라미터값으로 받아옴
		Dog cartDog=dogCartAddService.getCartDog(id);	// 장바구니 항목으로 추가될 개 상품의 정보를 얻어옴
		dogCartAddService.addCart(request, cartDog);	// 특정 개 상품을 장바구니 항목으로 추가하는 메소드
													//세션 영역 객체에 장바구니 항목을 추가해야 하기 때문에 파라미터 값으로 request객체를 던진다
		
		ActionForward forward=new ActionForward();
		forward.setPath("dogCartList.dog");
		forward.setRedirect(true);
				
		return forward;
	}

}
