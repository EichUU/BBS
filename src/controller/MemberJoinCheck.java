package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberJoinCheckService;
import vo.Member;

@WebServlet("/Check")

public class MemberJoinCheck extends HttpServlet {

	private static final long serialVersionUID=1L;

	public MemberJoinCheck() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		String idCheck=request.getParameter("idCheck");
		
		MemberJoinCheckService joinCheckService=new MemberJoinCheckService();		
		
		try {
			String checkmember=joinCheckService.getCheckMember(idCheck);
			
			if(idCheck.equals(checkmember)) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('이미 사용되고 있는 아이디입니다')"); 
				out.println("history.back();"); 
				out.println("</script>");
			}else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('사용가능한 아이디 입니다.')"); 
				out.println("history.back();"); 
				out.println("</script>");
			}
		}catch(Exception e) {
			
		}
		
			
		
		
		
		
		
	}
	
	
}
