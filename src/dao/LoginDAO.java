package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.ws.Response;


import vo.BoardBean;
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
	public Member selectLoginMember(String id, String passwd) {
		
		Member loginMember=null;
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			pstmt=conn.prepareStatement("select * from users where id=? and passwd=?");
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember=new Member();
				loginMember.setId(rs.getString("id"));
				loginMember.setPasswd(rs.getString("passwd"));
				loginMember.setAddr(rs.getString("addr"));
				loginMember.setAge(rs.getInt("age"));
				loginMember.setEmail(rs.getString("email"));
				loginMember.setGender(rs.getString("gender"));					
				loginMember.setName(rs.getString("name"));
				loginMember.setNation(rs.getString("nation"));					
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
		
		return loginMember;
	}
	
	//회원가입 관련 sql 구문
	public int joinMember(Member join) {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;			
		String sql="";
		int insertCount=0;
		
		try {
											
			sql="insert into users values(?,?,?,?,?,?,?,?)";
			
			pstmt=conn.prepareStatement(sql);
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
	
}
