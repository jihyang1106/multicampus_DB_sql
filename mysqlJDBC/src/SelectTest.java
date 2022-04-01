import java.sql.*;

public class SelectTest {

	public SelectTest() { // 객체 생성할 때 한 번만 실행
		
	}
	public void start() {	
		// 데이터베이스 연동하는 작업
		// 1. JDBC 드라이버 load 
		try {   // (forName()를 위한 예외처리)
			Class.forName("com.mysql.cj.jdbc.Driver");  
		} catch (ClassNotFoundException cnfe) {
			System.out.println("드라이브 로딩 실패!!!"); }
		// 변수 생성
		Connection conn = null;          // 2번
		PreparedStatement pstmt = null;  // 3,4번 
		ResultSet rs = null;             // 5번
		
		try {
		// 2. DB연결(Connection 객체 생성)
		String url = "jdbc:mysql://127.0.0.1/demo";
		conn = DriverManager.getConnection(url, "root", "root1106");
		
		// 3,4. 쿼리문 작성, PrepareStatement 객체 생성
		 String sql = "select empno, ename, hiredate, sal from emp order by ename asc";
		 pstmt = conn.prepareStatement(sql);
		 
		// 5. Result 객체로부터 데이터 추출
		rs = pstmt.executeQuery();  // rs에 표형식으로 데이터가 들어가 있다. 
		
		while(rs.next()) {
			int empno = rs.getInt(1);            
			String ename = rs.getString(2);  
			String hiredate = rs.getString(3);
			double sal =  rs.getDouble(4);
			System.out.printf("%6d %10s %22s %10.2f\n", empno, ename, hiredate, sal);
			}
		
		// executeQuery를 위한 예외처리
		} catch (SQLException se) {
			System.out.println("DB연결 에러 발생...");
			se.printStackTrace();
		} finally {
			// 연결종료(마지막에 생성된 것부터 종료)
			try {
				if(rs!=null) { rs.close(); }        // 다음에 들어올 데이터를 위해서
				if(pstmt!=null) { pstmt.close(); }
				if(conn!=null) { conn.close(); }
			}catch(Exception e) {}
		} 
	}
 	public static void main(String[] args) {
 		new SelectTest().start();
	}
}
