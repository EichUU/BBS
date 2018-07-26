package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberDeleteService;
import vo.ActionForward;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
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
			String deleteId=request.getParameter("id");
			MemberDeleteService memberDeleteService=new MemberDeleteService();
			boolean isDeleteSuccess=memberDeleteService.deleteMember(deleteId);
			
			if(isDeleteSuccess) {
				forward=new ActionForward();
				forward.setRedirect(true);
				forward.setPath("./memberListAction.bo");
			}else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('회원정보 삭제 실패.');");
				out.println("history.back()");
				out.println("</script>");
			}
		}		
		
		return forward;
	}

}
