package svc;


import java.sql.Connection;

import static db.JdbcUtil.*;
import dao.LoginDAO;
import vo.Member;

public class MemberLoginService  {

	public boolean login(Member member) {
		
		Connection conn=getConnection();
		LoginDAO loginDAO=LoginDAO.getInstance();		
		loginDAO.setConnection(conn);
		
		boolean isLoginSuccess=false;
		String loginId=loginDAO.selectLoginId(member);
		
		if(loginId!=null) {
			isLoginSuccess=true;
		}
		
		close(conn); //Connection 객체를 닫아주는 부분인데.. 왜 이렇게 표현할 수 있지?
		
		return isLoginSuccess;
	}
}
