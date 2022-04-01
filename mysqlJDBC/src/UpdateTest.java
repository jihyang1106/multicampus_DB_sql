import java.sql.*;
import java.util.Scanner;

public class UpdateTest {
	Scanner sc = new Scanner(System.in);
	
	public UpdateTest() {
		
	}
	
	public void start() {
		// �����ȣ�� �޿��� �Է¹޾� �ش����� �޿��� �����ϴ� ���α׷��ۼ�

		System.out.print("������ ��� ��ȣ = ");
		int empno = Integer.parseInt(sc.nextLine());
		System.out.print("������ �޿� = ");
		int sal = Integer.parseInt(sc.nextLine());
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 1. ����̺�ε� 
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. db���� 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/demo","root","root1106");
			// 3,4. �������� ������ Preparedstatement ����
			String sql = "update emp set sal=? where empno = ?";
			pstmt = con.prepareStatement(sql);
			// 3-1. : �ʿ䵥���� ����
			pstmt.setInt(1, sal);
			pstmt.setInt(2, empno);
			
			// 5. ���� : ������ ���ڵ��� ���� �������ش�.
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				System.out.println(result + "���� ���ڵ尡 �����Ǿ����ϴ�.");
			} else {
				System.out.println("������ ���ڵ尡 �����ϴ�.");  // �ش� ���ڵ尡 �������� �ֱ� ������ ������ �ƴ�
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
