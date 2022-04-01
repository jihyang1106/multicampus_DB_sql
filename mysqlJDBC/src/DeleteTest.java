import java.sql.*;
import java.util.Scanner;

public class DeleteTest {
	
	Scanner scan = new Scanner(System.in);
	Connection con = null;
	PreparedStatement pstmt = null;
	
	public DeleteTest() {

	}
	
	public void start() {
		try {
			// 사원번호를 입력받아 입력받은 사원을 삭제하라.
			System.out.print("삭제할 사원 번호 -> ");
			int no = Integer.parseInt(scan.nextLine());

			// 1.
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2.
			con = DriverManager.getConnection("jdbc:mysql://localhost/demo", "root", "root1106");
			// 3,4.
			String sql = "delete from emp where empno = ?";
			pstmt = con.prepareStatement(sql);
			// ?가 있으면 셋팅해야함
			pstmt.setInt(1, no);
			
			// 5. 
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(cnt + "개의 레코드가 삭제되었습니다.");
			} else {
				System.out.println("레코드 삭제 실패하였습니다."); // 해당 레코드가 없을 수도 있기 때문에 오류 아님
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {   // 6. dbclose
				if(pstmt !=null) pstmt.close();
				if(con != null)  con.close();
			} catch (Exception e) { }
		}
	}
	public static void main(String[] args) {
		new DeleteTest().start();

	}
}
