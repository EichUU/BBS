package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberLoginService;
import vo.ActionForward;
import vo.Member;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession();
		Member member=new Member();
		
		boolean isLoginSuccess=false;
		
		member.setId(request.getParameter("id")); //loginForm에서 id 파라미터를 얻어온다
		member.setPasswd(request.getParameter("passwd")); //loginForm에서 passwd 파라미터를 얻어온다
		
		MemberLoginService loginService=new MemberLoginService(); //로그인 비즈니스 로직을 처리하는 LoginService클래스 객체를 생성
		isLoginSuccess=loginService.login(member); //DB에서 true로 반환되는 member값을 가져온다
		
		ActionForward forward=null;
		
		//Member loginMember=loginService.getLoginMember(id, passwd); //로그인에 성공한 사용자의 정보를 얻어오는 getLoginMember메소드 호출 
		//로그인이 성공되면 로그인에 성공한 사용자의 정보를 Member객체에 담아서 반환하고, 실패하면 null이 넘어옴
		
		if(isLoginSuccess) {
			forward=new ActionForward();
			session.setAttribute("id", member.getId());	//로그인에 성공했을 경우 세션 영역에 id라는 이름으로 로그인에 성공한 아이디 값을 속성으로 공유한다
			//response.setContentType("text/html;charset=UTF-8");
			//PrintWriter out=response.getWriter();
			//out.println("<script>");
			//out.println("alert(member.getId()+'님 환영합니다.')");			
			//out.println("</script>");
			
			forward.setRedirect(true);
			
			forward.setPath("./memberLogin.bo");
			//alert처리해서 id님 환영합니다 이런거 했었던것 같은데..			
			
			
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 혹은 비밀번호를 확인해주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}
				
		
		return forward;
	}

}
