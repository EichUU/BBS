package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberJoinService;
import vo.ActionForward;
import vo.Member;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		Member member=new Member();
		boolean isJoinSuccess=false;
		
		//새로 등록할 회원정보들을 Member객체의 속성 값으로 할당

		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setAddr(request.getParameter("addr"));
		member.setAge(Integer.parseInt(request.getParameter("age")));
		member.setEmail(request.getParameter("email"));
		member.setGender(request.getParameter("gender"));
		member.setName(request.getParameter("name"));
		member.setNation(request.getParameter("nation"));		
		
		MemberJoinService joinService=new MemberJoinService();		
		isJoinSuccess = joinService.JoinMember(member);	 //DB와 연결한 후 DB에 등록이 된다면 true 반환
		
		ActionForward forward=null;
					
			if(isJoinSuccess==false) { //회원 등록 작업이 실패했을 때 
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('회원등록에 실패했습니다. 다시 확인해 주세요');"); //자바 스크립트로 등록 실패 경고창을 출력
				out.println("history.back();"); //이전 페이지로 되돌아가도록 처리
				out.println("</script>");
			}
			else { //회원 등록 성공일 때
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('회원가입이 완료되었습니다. 로그인하세요.');");
				out.println("</script>");
				forward=new ActionForward();
				forward.setRedirect(true);
				forward.setPath("./memberLogin.bo");
			}
			
		
		return forward;
	}
	
}
