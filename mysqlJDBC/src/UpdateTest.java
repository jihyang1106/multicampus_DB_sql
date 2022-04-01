import java.sql.*;
import java.util.Scanner;

public class UpdateTest {
	Scanner sc = new Scanner(System.in);
	
	public UpdateTest() {
		
	}
	
	public void start() {
		// 사원번호와 급여를 입력받아 해당사원의 급여를 수정하는 프로그램작성

		System.out.print("수정할 사원 번호 = ");
		int empno = Integer.parseInt(sc.nextLine());
		System.out.print("수정할 급여 = ");
		int sal = Integer.parseInt(sc.nextLine());
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 1. 드라이브로딩 
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. db연결 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/demo","root","root1106");
			// 3,4. 쿼리문을 가지고 Preparedstatement 생성
			String sql = "update emp set sal=? where empno = ?";
			pstmt = con.prepareStatement(sql);
			// 3-1. : 필요데이터 셋팅
			pstmt.setInt(1, sal);
			pstmt.setInt(2, empno);
			
			// 5. 실행 : 수정한 레코드의 수를 리턴해준다.
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				System.out.println(result + "개의 레코드가 수정되었습니다.");
			} else {
				System.out.println("수정한 레코드가 없습니다.");  // 해당 레코드가 없을수도 있기 때문에 오류가 아님
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 7. dbClose
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e1) { 
			}
		}
	}

	public static void main(String[] args) {
		new UpdateTest().start();

	}
}
