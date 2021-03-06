package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Member;

public class LoginDAO {

	private static LoginDAO loginDAO;
	private Connection conn;
	
	private LoginDAO() {
		
	}
	
	public static LoginDAO getInstance() {
		
		if(loginDAO==null) {
			loginDAO=new LoginDAO();
		}
		return loginDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn=conn;
	}
	
	//로그인관련 sql구문
	public String selectLoginId(Member member) {
		
		String loginId=null;
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			pstmt=conn.prepareStatement("select * from users where id=? and passwd=?");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {				
				loginId=rs.getString("id");					
			}
		}catch(Exception e) {
			System.out.println("아이디 혹은 비밀번호 오류1 : "+e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
			}catch(Exception e) {
				System.out.println("아이디 혹은 비밀번호 오류2 : "+e.getMessage());
			}
		}
		
		return loginId;
	}
	
	//회원가입 관련 sql 구문
	public int joinMember(Member join) {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;			
		
		int insertCount=0;
		
		try {
											
			pstmt=conn.prepareStatement("insert into users values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, join.getId());
			pstmt.setString(2, join.getPasswd());
			pstmt.setString(3, join.getAddr());
			pstmt.setInt(4, join.getAge());
			pstmt.setString(5, join.getEmail());
			pstmt.setString(6, join.getGender());
			pstmt.setString(7, join.getName());
			pstmt.setString(8, join.getNation());				
			
			insertCount=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("MemberJoin 에러1 : "+e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
			}catch(Exception e) {
				System.out.println("MemberJoin 에러2 : "+e.getMessage());
			}
		}
		
		return insertCount;
				
	}
	
	//아이디 중복확인
	public String joinCheck(String idCheck) {
		
		String checkMember=null;
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;			
		
		try{								
			pstmt=conn.prepareStatement("select * from users where id=?");
			pstmt.setString(1, idCheck);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				checkMember=idCheck;					
			}
			
		}catch(Exception e){
			System.out.println("JoinCheck 에러1: "+e.getMessage());
		}finally{
			try{
				rs.close();
				pstmt.close();
			}catch(Exception e){
				System.out.println("JoinCheck 에러2 : "+e.getMessage());
			}
		}
					
		return checkMember;
	}
	
	//회원정보보기
	public ArrayList<Member> selectMemberList() {

		ArrayList<Member> memberList=new ArrayList<Member>();
		Member mb=null;
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;	
		
		try {
			pstmt=conn.prepareStatement("select * from users");
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					mb=new Member();
					mb.setId(rs.getString("id"));
					mb.setPasswd(rs.getString("passwd"));
					mb.setAddr(rs.getString("addr"));
					mb.setAge(rs.getInt("age"));
					mb.setEmail(rs.getString("email"));
					mb.setGender(rs.getString("gender"));
					mb.setName(rs.getString("name"));
					mb.setNation(rs.getString("nation"));
					
					memberList.add(mb);
				}while(rs.next());
			}	
				
			
		}catch(Exception e) {
			System.out.println("getDetailMember 에러1 : "+e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
			}catch(Exception e) {
				System.out.println("getDetailMember 에러2 : "+e.getMessage());
			}
		}
		return memberList;
	}
	
	//회원 한명의 정보보기
	public Member selectMember(String id) {
		
		Member mb=null;
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			pstmt=conn.prepareStatement("select * from users where id=?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				mb=new Member();
				mb.setId(rs.getString("id"));
				mb.setPasswd(rs.getString("passwd"));
				mb.setAddr(rs.getString("addr"));
				mb.setAge(rs.getInt("age"));
				mb.setEmail(rs.getString("email"));
				mb.setGender(rs.getString("gender"));
				mb.setName(rs.getString("name"));
				mb.setNation(rs.getString("nation"));	
			}
			
		}catch(Exception e) {
			System.out.println("회원정보보기에러1 : "+e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
			}catch(Exception e) {
				System.out.println("회원정보보기에러2 : "+e.getMessage());
			}
		}
		
		return mb;
	}
	
	
	//회원정보 삭제
	public int deleteMember(String id) {
		
		int deleteCount=0;
		
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement("delete from users where id=?");
			pstmt.setString(1, id);
			
			deleteCount=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("deleteMember 에러1 : "+e.getMessage());
		}finally {
			try {
				pstmt.close();
			}catch(Exception e) {
				System.out.println("deleteMember 에러2: "+e.getMessage());
			}			
		}
		
		return deleteCount;
	}
	
}
