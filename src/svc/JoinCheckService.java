package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LoginDAO;
import vo.Member;

public class JoinCheckService {

	public String getCheckMember(String idCheck) throws Exception {
			
		Connection conn=getConnection();
		LoginDAO loginDAO=LoginDAO.getInstance();		
		loginDAO.setConnection(conn);
	
		String checkmember=loginDAO.joinCheck(idCheck);				
		
		conn.close();
		
		return checkmember;
		
		
	
		
	
		
	
	}
}
