package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.DogRegistService;
import vo.ActionForward;
import vo.Dog;

public class DogRegistAction implements Action {	// 새로운 개 상품 정보를 등록

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
				
		String realFolder=""; //파일 업로드될 서버 상의 물리적인 경로
		String saveFolder="/images";
		String encType="UTF-8";
		int maxSize=5*1024*1024;
		
		ServletContext context=request.getServletContext();
		realFolder=context.getRealPath(saveFolder);
		MultipartRequest multi=new MultipartRequest(request,
													realFolder,
													maxSize,
													encType,
													new DefaultFileRenamePolicy());
			
		
		Dog dog=new Dog( //클라이언트에서 전송된 파라미터 데이터들을 사용해서 새로 등록될 개 정보를 저장하는 Dog객체
				0,
				multi.getParameter("kind"),
				Integer.parseInt(multi.getParameter("price")),
				multi.getFilesystemName("image"), //서버 상에 업로드된 파일 이름을 얻어오는 부분
				multi.getParameter("nation"),
				Integer.parseInt(multi.getParameter("height")),
				Integer.parseInt(multi.getParameter("weight")),
				multi.getParameter("content"),
				0);
		
		DogRegistService dogRegistService=new DogRegistService();
		boolean isRegistSuccess=dogRegistService.registDog(dog);
		
		ActionForward forward=null;
		
		if(isRegistSuccess) {
			forward=new ActionForward();
			forward.setRedirect(true); 
			forward.setPath("dogList.dog");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패');");
			out.println("history,back();");
			out.println("</script>");
		}
				
		return forward;
	}	

	
}
