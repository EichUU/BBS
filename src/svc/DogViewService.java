package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.DogDAO;
import vo.Dog;

public class DogViewService {	// 개 상품 상세 정보보기 요청을 처리하는 비즈니스 로직을 구현
	
	public Dog getDogView(int id) {
		
		DogDAO dogDAO=DogDAO.getInstance();
		Connection conn=getConnection();
		dogDAO.setConnection(conn);
		
		int updateCount=dogDAO.updateReadCount(id);
		
		if(updateCount>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		Dog dog=dogDAO.selectDog(id); //파라미터로 전송된 id 값을 가지고 있는 개 상품 정보 하나를 얻어오는 부분
		close(conn);
				
		return dog;
	}

}
