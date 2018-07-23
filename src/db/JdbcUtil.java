package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	// 데이터베이스 작업을 할 때 반복적으로 수행해야 하는 작업을 정의하는 클래스

	// Connection pool에서 Connection 객체를 얻어와서 반환하는 메소드
	public static Connection getConnection() { 
		Connection conn=null;
		try {
			//톰캣 자체의 Context를 얻어온다
			Context initCtx=new InitialContext();
			Context envCtx=(Context)initCtx.lookup("java:comp/env");
			
			//context.xml에서 정의한 DataSorce 객체를 얻어온다
			DataSource ds=(DataSource)envCtx.lookup("jdbc/MysqlDB");
			
			//Connection Pool 에서 Connection 객체를 얻어온다
			conn=ds.getConnection();
			//트랜잭션을 처리하기 위해 오토커밋을 false 상태로
			conn.setAutoCommit(false); 
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return conn;
	}
	
	//Connection 객체를 닫아주는 메소드
	public static void close(Connection conn) {
		try {
			conn.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Statement 객체를 닫아주는 메소드
	public static void close(Statement stmt) {
		try {
			stmt.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//ResultSet 객체를 닫아주는 메소드
	public static void close(ResultSet rs) {
		try {
			rs.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	//트랜잭션 중에 실행된 작업들을 완료시키는 기능을 하는 메소드
	public static void commit(Connection conn) {
		try {
			conn.commit();
			System.out.println("commit success");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//트랜잭션 중에 실행된 작업들을 취소시키는 기능을 하는 메소드
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
			System.out.println("rollback success");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
