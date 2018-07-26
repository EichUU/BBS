package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.LoginDAO;
import vo.Member;

public class MemberJoinService {

	public boolean JoinMember(Member member)  {
		
		boolean isJoinSuccess=false;
		
		Connection conn=getConnection();
		LoginDAO loginDAO=LoginDAO.getInstance();		
		loginDAO.setConnection(conn);
		
		int insertCount=loginDAO.joinMember(member); //주소값을 저장 , 주소가 있으면 1, 없으면 0
		
		if(insertCount>0) {
			commit(conn);
			isJoinSuccess=true;
		}else {
			rollback(conn);
		}
		close(conn);
		
		return isJoinSuccess;
		
		
	}
}
