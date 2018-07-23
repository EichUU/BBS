package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Dog;

public class DogDAO {

	Connection conn;
	private static DogDAO dogDAO; //싱글톤의 멤버변수는 static 이어야 한다
	
	private DogDAO() {
		
	} // 싱글톤 패턴을 사용해서 처음 생성된 객체를 공유해서 사용
		//private이므로 DogDAO 객체의 레퍼런스 값을 얻어갈 때는 반드시 getInstance() 메소드를 호출해서 얻어가야 함
	
	public static DogDAO getInstance() {
		if(dogDAO==null) {
			dogDAO=new DogDAO();
		}
		return dogDAO;
	} // 외부 클래스에서 DogDAO클래스에 정의되어 있는 메소드를 사용하기 위해서 객체를 얻어갈 때
		// 첫번째 요청에서만 객체를 생성해 주고 다음에 객체를 사용할 때는 처음 생성된 객체의 레퍼런스 값을 공유하게 해 주는 메소드
			
	public void setConnection(Connection conn) {
		this.conn=conn;
	}
	
	//데이터베이스에 저장되어 있는 모든 개 상품 정보를 반환하는 메소드
	public ArrayList<Dog> selectDogList(){
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		ArrayList<Dog> dogList=null;
		
		try {
			pstmt=conn.prepareStatement("select * from dog");
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dogList=new ArrayList<Dog>();
				
				do {
					dogList.add(new Dog(
							rs.getInt("id"),
							rs.getString("kind"),
							rs.getInt("price"),
							rs.getString("image"),
							rs.getString("country"),
							rs.getInt("height"),
							rs.getInt("weight"),
							rs.getString("content"),
							rs.getInt("readcount")));
				}while(rs.next());
			}
			
		}catch(Exception e) {
			System.out.println("개 상품 정보 반환 오류1 : "+e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
			}catch(Exception e) {
				System.out.println("개 상품 정보 반환 오류2 : "+e.getMessage());
			}
		}
		
		return dogList;
		
	}
	
	//데이터베이스에 저장되어 있는 개 상품 정보 중 파라미터로 전송된 id 값을 가지고 있는 개 상품의 정보를 반환하는 메소드
	public Dog selectDog(int id) {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		Dog dog=null;
		
		try {
			pstmt=conn.prepareStatement("select * from dog where id=?");
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dog=new Dog(
						rs.getInt("id"),
						rs.getString("kind"),
						rs.getInt("price"),
						rs.getString("image"),
						rs.getString("country"),
						rs.getInt("height"),
						rs.getInt("weight"),
						rs.getString("content"),
						rs.getInt("readcount"));						
			}
		}catch(Exception e) {
			System.out.println("개정보 불러오기 오류1 : "+e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
			}catch(Exception e) {
				System.out.println("개정보 불러오기 오류2 : "+e.getMessage());
			}
		}
		
		return dog;
	}
	
	//상세 내용 보기 요청이 된 개 상품 정보의 조회수를 증가시키는 메소드
	public int updateReadCount(int id) {
		
		PreparedStatement pstmt=null;
		
		int updateCount=0;
		
		try {
			pstmt=conn.prepareStatement("update dog set readcount=readcount+1 where id=?");
			pstmt.setInt(1, id);
			updateCount=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("조회수증가오류1 : "+e.getMessage());
		}finally {
			try {
				pstmt.close();
			}catch(Exception e) {
				System.out.println("조회수증가오류2 : "+e.getMessage());
			}
		}
		
		return updateCount;
	}
	
	//새로운 개 상품 정보를 데이터베이스에 추가하는 메소드
	public int insertDog(Dog dog) {
		
		PreparedStatement pstmt=null;
		
		int insertCount=0;
		
		try {
			pstmt=conn.prepareStatement("insert into dog(kind, price, image, country, height, weight, content, readcount) values(?,?,?,?,?,?,?,?)");
										//auto_increment가 설정된 값은 0을 줌으로서 자동증가하게 하던지
										//auto_increment가 설정된 id를 제외한 나머지 칼럼명을 명시함으로 values 값이 들어갈 수 있도록 할 수도 있다
			pstmt.setString(1, dog.getKind());
			pstmt.setInt(2, dog.getPrice());
			pstmt.setString(3, dog.getImage());
			pstmt.setString(4, dog.getCountry());
			pstmt.setInt(5, dog.getHeight());
			pstmt.setInt(6, dog.getWeight());
			pstmt.setString(7, dog.getContent());
			pstmt.setInt(8, dog.getReadcount());
			insertCount=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("데이터 등록 오류1 : "+e.getMessage());
		}finally {
			try {
				pstmt.close();
			}catch(Exception e) {
				System.out.println("데이터 등록 오류2 : "+e.getMessage());
			}
		}
		
		return insertCount;
	}
}
