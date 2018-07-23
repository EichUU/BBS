package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.DogCartAddAction;
import action.DogCartListAction;
import action.DogCartQtyDownAction;
import action.DogCartQtyUpAction;
import action.DogCartRemoveAction;
import action.DogCartSearchAction;
import action.DogListAction;
import action.DogRegistAction;
import action.DogRegistFormAction;
import action.DogViewAction;
import vo.ActionForward;

@WebServlet("*.dog")	//마지막url이 *.bo로 끝나는 요청을 매핑하는 서블릿으로 지정
public class DogFrontController extends HttpServlet {	//DogShopping 프로젝트의 모든 웹 요청은 이 곳 서블릿으로 요청되고 
														//전체요청이 이 부분에서 제어된다

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//1.전송된 요청파악
		String requestURI=request.getRequestURI();	//requestURI : /BBS/*.dog 반환		
		String contextPath=request.getContextPath(); // /BBS 반환		
		String command=requestURI.substring(contextPath.length());
		System.out.println(command);
		
		ActionForward forward=null; //각 Action클래스 객체의 execute메소드를 실행한 후 반환되는 ActionForward 객체를 저장할 변수를 정의		
		Action action=null; //각 요청을 처리하는 Action클래스 객체를 다형성을 사용해서 참조하는 변수를 정의
		
		//2.각 요청별로 비즈니스 로직 호출
		//각 요청에 해당하는 ACtion클래스 객체를 실행하는 부분
				//각 Action객체를 실행하면 forward변수에 각 액션 객체에서 반환된 ActionForward객체가 참조된다
		if(command.equals("/dogList.dog")) {
			action=new DogListAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("dogList.dog 에러 : "+e.getMessage());
			}
		}
		else if(command.equals("/dogView.dog")) {
			action=new DogViewAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("dogView.dog 에러 : "+e.getMessage());
			}
		}
		else if(command.equals("/dogCartAdd.dog")) {
			action=new DogCartAddAction();
			try{
				forward=action.execute(request, response);						
			}catch(Exception e) {
				System.out.println("dogCartAdd.dog 에러 : "+e.getMessage());
			}
		}
		else if(command.equals("/dogCartList")) {
			action=new DogCartListAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("dogCartList.dog 에러 : "+e.getMessage());
			}
		}
		else if(command.equals("/dogCartSearch.dog")) {
			action=new DogCartSearchAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("dogCartSearch.dog 에러 : "+e.getMessage());
			}
		}
		else if(command.equals("/dogCartRemove.dog")) {
			action=new DogCartRemoveAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("dogCartRemove.dog 에러 : "+e.getMessage());
			}
		}
		else if(command.equals("/dogCartQtyUp.dog")) {
			action=new DogCartQtyUpAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("dogCartQtyUp.dog 에러 : "+e.getMessage());
			}
		}
		else if(command.equals("/dogCartQtyDown.dog")) {
			action=new DogCartQtyDownAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("dogCartQtyDown.dog 에러 : "+e.getMessage());
			}
		}
		else if(command.equals("/dogRegist.dog")) {
			action=new DogRegistAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("dogRegist.dog 에러 : "+e.getMessage());
			}
		}
		else if(command.equals("/dogRegistForm.dog")) {
			action=new DogRegistFormAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("dogRegistForm.dog 에러 : "+e.getMessage());
			}
		}
		
		//3.포워딩
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	
}
