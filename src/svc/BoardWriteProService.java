package svc;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;
import static db.JdbcUtil.*;

public class BoardWriteProService {

	public boolean registArticle(BoardBean boardBean) throws Exception {
			
		boolean isWriteSuccess=false;
		
		Connection conn=getConnection();
		BoardDAO boardDAO=BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		
		int insertCount=boardDAO.insertArticle(boardBean); //주소값을 저장 , 주소가 있으면 1, 없으면 0
		
		if(insertCount>0) {
			commit(conn);
			isWriteSuccess=true;
		}else {
			rollback(conn);
		}
		close(conn);
		
		return isWriteSuccess;
	}
}
