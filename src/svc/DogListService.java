package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.DogDAO;
import vo.Dog;

public class DogListService { //개 상품 목록보기 요청을 처리하는 비즈니스 로직을 구현
	
	public ArrayList<Dog> getDogList() {
		
		DogDAO dogDAO=DogDAO.getInstance(); 
		Connection conn=getConnection();
		dogDAO.setConnection(conn);
		
		ArrayList<Dog> dogList=dogDAO.selectDogList();
		
		close(conn);
		
		return dogList;
	}
}
