package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class BoardListAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ArrayList<BoardBean> articleList=new ArrayList<BoardBean>(); //각 페이지당 출력될 전체 글 목록을 저장할 ArrayList객체를 생성
		
		int page=1; //목록보기 요청에서 출력될 페이지의 기본값으로 1페이지를 설정
		int limit=10; //한페이지당 출력된 글의 개수를 10개로 설정
		
		//목록 보기에 출력될 페이지가 파라미터로 전송된 경우 page 변수의 값을 변경
		if(request.getParameter("page")!=null) {			
			//목록 보기 페이지에서 조회할 페이지 번호를 클릭하고 요청한 경우, 페이지 번호가 파라미터로 전송되어 온다
			page=Integer.parseInt(request.getParameter("page"));
		}	
		
		BoardListService boardListService=new BoardListService();
		int listCount=boardListService.getListCount();//총 리스트 수를 받아옴		
		articleList=boardListService.getArticleList(page, limit);//리스트를 받아옴		
		int maxPage=(int)((double)listCount/limit+0.95); 	//총 페이지 수, 0.95를 더해서 올림처리
		int startPage=(((int)((double)page/10+0.9))-1)*10+1; //현재페이지에 보여줄 시작 페이지 수
		int endPage=startPage+10-1; //현재페이지에서 보여줄 마지막 페이지 수
		
		if(endPage>maxPage) {
			endPage=maxPage;
		}
		
		PageInfo pageInfo=new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		ActionForward forward=new ActionForward();
		forward.setPath("/board/qna_board_list.jsp");
				
		return forward;
	}
	
}
