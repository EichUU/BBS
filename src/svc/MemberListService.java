package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.LoginDAO;
import vo.Member;

public class MemberListService {

	public ArrayList<Member> getMemberList() {
		
		Connection conn=getConnection();
		LoginDAO loginDAO=LoginDAO.getInstance();		
		loginDAO.setConnection(conn);
		
		ArrayList<Member> memberList=loginDAO.selectMemberList();
		
		close(conn);
		
		return memberList;
	}
}
