package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.JoinService;
import vo.Member;

@WebServlet("/Join")
public class JoinServlet extends HttpServlet {

	private static final long serialVersionUID=1L;
	private boolean isJoinSuccess;
	
	public JoinServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Member member=new Member();
		
		//새로 등록할 회원정보들을 Member객체의 속성 값으로 할당

		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setAddr(request.getParameter("addr"));
		member.setAge(Integer.parseInt(request.getParameter("age")));
		member.setEmail(request.getParameter("email"));
		member.setGender(request.getParameter("gender"));
		member.setName(request.getParameter("name"));
		member.setNation(request.getParameter("nation"));		
		
		JoinService joinService=new JoinService();
		
		
		try {
			boolean isJoinSuccess = joinService.JoinMember(member);
			
			if(!isJoinSuccess) { //회원 등록 작업이 실패했을 때 
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('회원등록에 실패했습니다. 다시 확인해 주세요')"); //자바 스크립트로 등록 실패 경고창을 출력
				out.println("history.back();"); //이전 페이지로 되돌아가도록 처리
				out.println("</script>");
			}
			else { //회원 등록 성공일 때
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('회원가입이 완료되었습니다. 로그인하세요.');");
				out.println("location.href='index.jsp'");
				out.println("</script>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //DB와 연결한 후 DB에 등록이 된다면 true 반환	
		
		
		
		
	}
	
}
