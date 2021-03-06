package svc;

import java.sql.Connection;

import dao.BoardDAO;

import static db.JdbcUtil.*;

public class BoardDeleteProService {

	public boolean isArticleWriter(int board_num, String pass) {
		
		boolean isArticleWriter=false;
		
		Connection conn=getConnection();
		BoardDAO boardDAO=BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		
		isArticleWriter=boardDAO.isArticleBoardWriter(board_num, pass);
		
		close(conn);
		
		return isArticleWriter;
	}
	
	public boolean removeArticle(int board_num) {
		
		boolean isRemoveSuccess=false;
		
		Connection conn=getConnection();
		BoardDAO boardDAO=BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		
		int deleteCount=boardDAO.deleteArticle(board_num);
		if(deleteCount>0) {
			commit(conn);
			isRemoveSuccess=true;
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return isRemoveSuccess;
	}
}
