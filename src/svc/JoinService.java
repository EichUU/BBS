package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LoginDAO;
import vo.Member;

public class JoinService {

	public boolean JoinMember(Member member) throws Exception {
		
		boolean isJoinSuccess=false;
		
		Connection conn=getConnection();
		LoginDAO loginDAO=LoginDAO.getInstance();		
		loginDAO.setConnection(conn);
		
		int insertCount=loginDAO.joinMember(member); //주소값을 저장 , 주소가 있으면 1, 없으면 0
		
		if(insertCount>0) {
			conn.commit();
			isJoinSuccess=true;
		}else {
			conn.rollback();
		}
		conn.close();
		
		return isJoinSuccess;
		
		
	}
}
