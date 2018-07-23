package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;
import vo.PageInfo;

public class BoardDetailService {

	public BoardBean getArticle(int board_num) throws Exception {
		
		BoardBean article=null;
		Connection conn=getConnection();
		BoardDAO boardDAO=BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		
		int updateCount=boardDAO.updateReadCount(board_num);
		if(updateCount>0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		
		article=boardDAO.selectArticle(board_num);
		conn.close();
		
		return article;
	}
	
}
