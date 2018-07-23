package svc;


import java.sql.Connection;

import static db.JdbcUtil.*;
import dao.LoginDAO;
import vo.Member;

public class LoginService  {

	public Member getLoginMember(String id, String passwd)  {
		
		Connection conn=getConnection();
		LoginDAO loginDAO=LoginDAO.getInstance();		
		loginDAO.setConnection(conn);
		
		Member loginMember=loginDAO.selectLoginMember(id, passwd);		
		
		close(conn); //Connection 객체를 닫아주는 부분인데.. 왜 이렇게 표현할 수 있지?
		
		return loginMember;
	}
}
