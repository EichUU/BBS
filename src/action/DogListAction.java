package action;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogListService;
import vo.ActionForward;
import vo.Dog;

public class DogListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ArrayList<String> todayImageList=new ArrayList<String>();	//오늘 본 상품의 이미지를 저장할 ArrayList객체를 생성
		Cookie[] cookieArray=request.getCookies();	//클라리언트에서 넘어온 Cookie객체를 배열의 형태로 반환받는다
														//사이트에서 오늘 본 상품이 있다면 각 상품의 이미지가 쿠키로 저장되어 있다
		
		if(cookieArray!=null) {		//요청에 넘어온 쿠키 객체 중 오늘 본 상품 이미지 이름을 저장하고 있는 쿠키 객체를 찾아서
			for(int i=0; i<cookieArray.length; i++) {		//todayImageList ArrayList객체에 쿠키 객체의 값을 요소로 추가
				if(cookieArray[i].getName().startsWith("today")) {	//DogviewAction클래스에서 본 상품의 이미지를
					todayImageList.add(cookieArray[i].getValue());	//today문자열 뒤에 해당 상품의 아이디를 붙인 이름으로 쿠키 객체에 저장한다
				}
			}
		}
		
		DogListService dogListService=new DogListService();		//개 상품 목록보기 요청을 처리하는 서비스 객체를 생성
		ArrayList<Dog> dogList=dogListService.getDogList();		//등록되어 있는 개 상품 정보를 ArrayList타입으로 얻어온다
		request.setAttribute("dogList", dogList);	//개 상품 목록 정보
		request.setAttribute("todayImageList", todayImageList);	//오늘 본 개 상품 이미지 목록 정보
		
		ActionForward forward=new ActionForward(); 
		forward.setPath("dogList.jsp");	//포워딩 페이지
		forward.setRedirect(false);	//포워딩 방식은 디스패치 방식으로
		
		return forward;
	}
	
}
