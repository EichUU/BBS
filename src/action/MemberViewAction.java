package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberListService;
import svc.MemberLoginService;
import svc.MemberViewService;
import vo.ActionForward;
import vo.Member;

public class MemberViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		/*
		HttpSession session=request.getSession(); //현재 로그인한 사용자가 admin 이면 관리자
			
		ActionForward forward=null;
		
		String viewId=request.getParameter("id");
		
		MemberViewService memberViewService=new MemberViewService();
		Member member=memberViewService.getMember(viewId);
		
		request.setAttribute("member", member);
		
		forward.setRedirect(true);
		forward.setPath("./member_info.jsp");
		
		return forward;
		*/
		
		
		/*
		HttpSession session=request.getSession();
		Member member=new Member();
		
		boolean isLoginSuccess=false;
		
		member.setId(request.getParameter("id")); //loginForm에서 id 파라미터를 얻어온다
		member.setPasswd(request.getParameter("passwd")); //loginForm에서 passwd 파라미터를 얻어온다
		
		MemberLoginService loginService=new MemberLoginService(); //로그인 비즈니스 로직을 처리하는 LoginService클래스 객체를 생성
		isLoginSuccess=loginService.login(member); //DB에서 true로 반환되는 member값을 가져온다
		
		ActionForward forward=null;
		
		if(isLoginSuccess) {
			if(member.getId().equals("java")) {
				forward=new ActionForward();
				String viewId=request.getParameter("java");
				MemberViewService memberViewService=new MemberViewService();
				Member mb=memberViewService.getMember(viewId);
				request.setAttribute("mb", mb);
				forward.setPath("./member_info.jsp");
								
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
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		ActionForward forward=null;
				
		if(id==null) {
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./memberLogin.bo");
		}
		else if(!id.equals("java")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('관리자가 아닙니다.');");
			out.println("history.back()");
			out.println("</script>");
		}
		else {
			forward=new ActionForward();
			String viewId=request.getParameter("id");
			MemberViewService memberViewService=new MemberViewService();
			Member member=memberViewService.getMember(viewId);
			request.setAttribute("member", member);
			forward.setPath("./member_info.jsp");
		}		
		
		return forward;
		
	}

}
