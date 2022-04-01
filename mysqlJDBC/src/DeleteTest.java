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
			// �����ȣ�� �Է¹޾� �Է¹��� ����� �����϶�.
			System.out.print("������ ��� ��ȣ -> ");
			int no = Integer.parseInt(scan.nextLine());

			// 1.
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2.
			con = DriverManager.getConnection("jdbc:mysql://localhost/demo", "root", "root1106");
			// 3,4.
			String sql = "delete from emp where empno = ?";
			pstmt = con.prepareStatement(sql);
			// ?�� ������ �����ؾ���
			pstmt.setInt(1, no);
			
			// 5. 
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(cnt + "���� ���ڵ尡 �����Ǿ����ϴ�.");
			} else {
				System.out.println("���ڵ� ���� �����Ͽ����ϴ�."); // �ش� ���ڵ尡 ���� ���� �ֱ� ������ ���� �ƴ�
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
