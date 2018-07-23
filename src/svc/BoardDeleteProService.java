package svc;

import java.sql.Connection;

import dao.BoardDAO;

import static db.JdbcUtil.*;

public class BoardDeleteProService {

	public boolean isArticleWriter(int board_num, String pass) throws Exception{
		
		boolean isArticleWriter=false;
		
		Connection conn=getConnection();
		BoardDAO boardDAO=BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		
		isArticleWriter=boardDAO.isArticleBoardWriter(board_num, pass);
		
		conn.close();
		
		return isArticleWriter;
	}
	
	public boolean removeArticle(int board_num) throws Exception{
		
		boolean isRemoveSuccess=false;
		
		Connection conn=getConnection();
		BoardDAO boardDAO=BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		
		int deleteCount=boardDAO.deleteArticle(board_num);
		if(deleteCount>0) {
			conn.commit();
			isRemoveSuccess=true;
		}else {
			conn.rollback();
		}
		
		conn.close();
		
		return isRemoveSuccess;
	}
}
