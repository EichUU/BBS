package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.DogDAO;
import vo.Dog;

public class DogRegistService { //개 상품 등록 요청을 처리하는 비즈니스 로직을 구현
								//파일을 서버 상에 업로드 한 후 업로드 된 파일의 정보를 데이터베이스에 저장
	
	public boolean registDog(Dog dog) {
		
		DogDAO dogDAO=DogDAO.getInstance(); //dogDAO 의 인스턴스 객체를 호출하고
		Connection conn=getConnection();	//jdbcUtil 의 Connection 객체를 호출하여
		dogDAO.setConnection(conn);			//DogDAO와 Connection을 연결한다
		
		boolean isRegistSuccess=false;
		int insertCount=dogDAO.insertDog(dog);	//새로운 개 상품 정보를 데이터베이스에 추가하는 메소드
		
		if(insertCount>0) {
			commit(conn);
			isRegistSuccess=true;
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return isRegistSuccess;
		
	}
}
