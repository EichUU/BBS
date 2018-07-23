package vo;

public class ActionForward {
	
	//컨트롤러 역할을 하는 서블릿에서 클라리언트의 각 요청을 받아서 처리한 후 
	//최종적으로 뷰 페이지로 포워딩 처리시 이동할 뷰 페이지의 url과 포워딩 방식을 처리
	
	private String path; 
	//서블릿에서 요청 처리 후 포워딩 될 최종 뷰 페이지 url이 저장되는 변수 정의
	
	private boolean redirect; 
	//포워딩 방식이 저장되는 변수, 값이 false면 디스패치 방식으로, true면 리다이렉트 방식으로 포워딩한다
	
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path=path;
	}
	
	public boolean isRedirect() {
		return redirect;
	}
	
	public void setRedirect(boolean redirect) {
		this.redirect=redirect;
	}
	
	
	
}
