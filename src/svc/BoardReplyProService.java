package svc;

import static db.JdbcUtil.getConnection;

import java.io.PrintWriter;
import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

public class BoardReplyProService {

	public boolean replyArticle(BoardBean article) throws Exception{
		
		boolean isReplySuccess=false;
		int insertCount=0;
		
		Connection conn=getConnection();
		BoardDAO boardDAO=BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		
		insertCount=boardDAO.insertReplyArticle(article);
				
		if(insertCount>0) {
			conn.commit();
			isReplySuccess=true;
		}else {
			conn.rollback();
		}
		
		conn.close();		
		
		return isReplySuccess;
	}
}
