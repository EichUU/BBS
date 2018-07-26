package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardWriteProService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardWriteProAction implements Action {
	//새로운 글을 등록하는 액션클래스
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Frontcontroller에서 전송된 요청을 파악하여 각 요청을 처리하는  Action클래스 객체의 execute 메소드를 호출
		//추상메소드를 상속받았기 때문에 execute를 재정의 
		
		ActionForward forward=null;
		BoardBean boardBean=null;
		String realFolder=""; //서버상의 파일 경로를 저장할 실제 경로를 저장할 변수
		String saveFolder="/boardUpload"; //파일을 업로드할 디렉토리명을 지정
		int fileSize=5*1024*1024; //한번에 업로드할 파일 사이즈를 정의
		ServletContext context=request.getServletContext();
		realFolder=context.getRealPath(saveFolder); //파라미터로 지정된 디렉토리의 서버 상의 실제 경로를 얻어옴
		MultipartRequest multi=new MultipartRequest(request,
													realFolder,
													fileSize,
													"UTF-8",
													new DefaultFileRenamePolicy());
		
		//새로 등록할 글 정보들을 BoardBean객체의 속성 값으로 할당
		boardBean=new BoardBean();
		boardBean.setBoard_name(multi.getParameter("board_name"));
		boardBean.setBoard_pass(multi.getParameter("board_pass"));
		boardBean.setBoard_subject(multi.getParameter("board_subject"));
		boardBean.setBoard_content(multi.getParameter("board_content"));
		boardBean.setBoard_file(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		
		BoardWriteProService boardWriteProService=new BoardWriteProService();
		
		boolean isWriteSuccess=boardWriteProService.registArticle(boardBean); //DB와 연결한 후 DB에 등록이 된다면 true 반환	
		
		
		//글 등록 작업이 실패했을 때 
		if(!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패');"); //자바 스크립트로 등록 실패 경고창을 출력
			out.println("history.back();"); //이전 페이지로 되돌아가도록 처리
			out.println("</script>");
		}
		else { //글 등록 성공일 때
			forward=new ActionForward();
			forward.setRedirect(true); 
			forward.setPath("boardList.bo"); //목록보기 요청
		}
				
		return forward;
		
	}
}
