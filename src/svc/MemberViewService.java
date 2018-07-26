package svc;

import java.sql.Connection;

import static db.JdbcUtil.*;
import dao.LoginDAO;
import vo.Member;

public class MemberViewService {

	public Member getMember(String viewId) {
		
		Connection conn=getConnection();
		LoginDAO loginDAO=LoginDAO.getInstance();		
		loginDAO.setConnection(conn);
		
		Member member=loginDAO.selectMember(viewId);
		
		close(conn);
		
		return member;
	}
}
