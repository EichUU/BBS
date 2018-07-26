package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberListService;
import svc.MemberLoginService;
import vo.ActionForward;
import vo.Member;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		ActionForward forward=null;
		//System.out.print(id); id가 왜 null이지?
		
		if(id==null) {
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./memberlogin.bo");
		}
		else if(!id.equals("java")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('관리자가 아닙니다');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			forward=new ActionForward();
			MemberListService memberListService=new MemberListService();
			ArrayList<Member> memberList=memberListService.getMemberList();
			request.setAttribute("memberList", memberList);
			forward.setPath("./member_list.jsp");
		}
		/*
		
		HttpSession session=request.getSession();
		Member member=new Member();
		
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		
		MemberLoginService loginService=new MemberLoginService(); 
		boolean isLoginSuccess=loginService.login(member);
		
		ActionForward forward=null;							
			
		/*
		if(session.getAttribute("id")!=null) {
			MemberListService memberListService=new MemberListService();
			ArrayList<Member> memberList=memberListService.getMemberList();
			request.setAttribute("memberList", memberList);
			forward.setPath("./member_list.jsp");
		}
		
		else 
	
		if(isLoginSuccess || session!=null) {
			if(member.getId().equals("java")) {
				forward=new ActionForward();
				MemberListService memberListService=new MemberListService();
				ArrayList<Member> memberList=memberListService.getMemberList();
				request.setAttribute("memberList", memberList);
				forward.setPath("./member_list.jsp");
			}
		}else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('관리자가 아닙니다');");
			out.println("history.back();");
			out.println("</script>");
		}
				*/		
			
		return forward;
	}

	
}
