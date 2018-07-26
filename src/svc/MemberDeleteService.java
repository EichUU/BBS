package svc;

import static db.JdbcUtil.getConnection;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.LoginDAO;

public class MemberDeleteService {

	public boolean deleteMember(String deleteId) {
		
		Connection conn=getConnection();
		LoginDAO loginDAO=LoginDAO.getInstance();		
		loginDAO.setConnection(conn);
		
		boolean deleteResult=false;
		
		int deleteCount=loginDAO.deleteMember(deleteId);
		if(deleteCount>0) {
			commit(conn);
			deleteResult=true;			
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return deleteResult;
	}
}
