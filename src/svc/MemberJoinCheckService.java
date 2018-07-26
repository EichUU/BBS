package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import static db.JdbcUtil.*;

import dao.LoginDAO;
import vo.Member;

public class MemberJoinCheckService {

	public String getCheckMember(String idCheck) {
			
		Connection conn=getConnection();
		LoginDAO loginDAO=LoginDAO.getInstance();		
		loginDAO.setConnection(conn);
	
		String checkmember=loginDAO.joinCheck(idCheck);				
		
		close(conn);
		
		return checkmember;
		
		
	
		
	
		
	
	}
}
