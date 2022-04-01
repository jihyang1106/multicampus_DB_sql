import java.sql.*;

public class MysqlConnection {
	// 멤버변수 선언, 객체 생성 / 원래 실행문은 멤버영역에 기술할 수 없음

	// 1. 드라이브로딩
	static { // static{}은 실행문을 기술할 수 있는 영역이 된다.(멤버영역에 있는 실행문)
		// 실행문, 멤버영역
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("드라이브로딩 예외 발생!");
		}
	}

	// 멤버영역에 변수를 선언한다.
	// 상속받은 다른 패키지에서도 사용할 수 있게 접근제어자를 protected로
	protected Connection conn = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;

	// 2. 데이터베이스 연결메서드 (상속시켜줄 것이기 때문에 반환형 x)
	public void getConn() {
		String url = "jdbc:mysql://localhost/multicampus";
		String id = "root";
		String password = "root1106";
		try {
			conn = DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			System.out.println("데이터베이스 연결 예외 발생!");
		}
	}

	// 3. 데이터베이스 종료메서드
	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("데이터베이스 닫기 예외 발생!");
		}
	}

}
