package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BoardDeleteProAction;
import action.BoardDetailAction;
import action.BoardListAction;
import action.BoardModifyFormAction;
import action.BoardModifyProAction;
import action.BoardReplyFormAction;
import action.BoardReplyProAction;
import action.BoardWriteProAction;
import vo.ActionForward;

@WebServlet("*.bo") //마지막url이 *.bo로 끝나는 요청을 매핑하는 서블릿으로 지정
public class BoardFrontcontroller extends HttpServlet{
	
	//게시판 프로젝트의 모든 웹 요청은 이 부분에서 제어된다

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//전송된 요청을 파악하는 부분
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		
		ActionForward forward=null; //각 Action클래스 객체의 execute메소드를 실행한 후 반환되는 ActionForward 객체를 저장할 변수를 정의		
		Action action=null; //각 요청을 처리하는 Action클래스 객체를 다형성을 사용해서 참조하는 변수를 정의
		
		
		//각 요청에 해당하는 ACtion클래스 객체를 실행하는 부분
		//각 Action객체를 실행하면 forward변수에 각 액션 객체에서 반환된 ActionForward객체가 참조된다
		if(command.equals("/boardWriteForm.bo")) {
			forward=new ActionForward();
			//글쓰기 페이지를 열어주는 요청은 특별한 비즈니스 로직을 실행할 필요 없이 포워딩 될 페이지만 지정해 준다
			forward.setPath("/board/qna_board_write.jsp");			
			
		}else if(command.equals("/boardWritePro.bo")) {
			action=new BoardWriteProAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}else if(command.equals("/boardList.bo")) {
			action=new BoardListAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}else if(command.equals("/boardDetail.bo")) {
			action=new BoardDetailAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}else if(command.equals("/boardReplyForm.bo")) {
			action=new BoardReplyFormAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
				
		}else if(command.equals("/boardReplyPro.bo")) {
			action=new BoardReplyProAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}else if(command.equals("/boardModifyForm.bo")) {
			action=new BoardModifyFormAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}else if(command.equals("/boardModifyPro.bo")){
			action=new BoardModifyProAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
			//게시판 글 하나를 삭제한 후 원래 보던 목록 보기 페이지로 되돌아 가야 하기 때문에
			//게시판 삭제를 위해서 비밀번호를 입력하는 페이지로 페이지 번호를 공유한다
		}else if(command.equals("/boardDeleteForm.bo")) {
			String nowPage=request.getParameter("page");
			request.setAttribute("page", nowPage); //이 페이지 번호는 글 상세 내용을 본 후 삭제 버튼을 눌렀을 때 파라미터로 전송된다
				int board_num=Integer.parseInt(request.getParameter("board_num"));
			request.setAttribute("board_num", board_num);
			forward=new ActionForward();
			forward.setPath("/board/qna_board_delete.jsp");	//비밀번호를 입력하는 페이지		
			
		}else if(command.equals("/boardDeletePro.bo")) {
			action=new BoardDeleteProAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		//각 Action클래스 객체에서 반환된 ActionForward객체 정보를 사용하여 포워딩 처리
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());				
			}else {
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}		
		
	}

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
}
