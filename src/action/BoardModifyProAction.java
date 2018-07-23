package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardModifyProService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class BoardModifyProAction implements Action{

public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
	
		//2차수정
		int page=1;
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}		
		
		/* 1차 수정
		PageInfo pageInfo=new PageInfo(); //수정1
		int page=1;
		pageInfo.setPage(page);
		page=pageInfo.getPage(); //수정2
		*/
	
		ActionForward forward=null;
		boolean isModifySuccess=false;
		
		int board_num=Integer.parseInt(request.getParameter("board_num"));
		
		BoardBean article=new BoardBean();
		BoardModifyProService boardModifyProService=new BoardModifyProService();
		boolean isRightUser=boardModifyProService.isArticleWriter(board_num, request.getParameter("board_pass"));
		
		if(!isRightUser) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			article.setBoard_num(board_num);
			article.setBoard_subject(request.getParameter("board_subject"));
			article.setBoard_content(request.getParameter("board_content"));
			isModifySuccess=boardModifyProService.modifyArticle(article);
			
			if(!isModifySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back()");
				out.println("</script>");
			}else {
					
				/* 1차수정
				if(board_num>10) {
					page++;
				}else if(board_num>20){
					page++;
				}else if(board_num>30){
					page++;
				}else if(board_num>40){
					page++;
				}else if(board_num>50){
					page++;
				}
				*/
				forward=new ActionForward();
				forward.setRedirect(true);
				forward.setPath("boardDetail.bo?board_num="+article.getBoard_num()+"&page="+page);
			}
		}
	
		return forward;
		
	}
}
