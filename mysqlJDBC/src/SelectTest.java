import java.sql.*;

public class SelectTest {

	public SelectTest() { // ��ü ������ �� �� ���� ����
		
	}
	public void start() {	
		// �����ͺ��̽� �����ϴ� �۾�
		// 1. JDBC ����̹� load 
		try {   // (forName()�� ���� ����ó��)
			Class.forName("com.mysql.cj.jdbc.Driver");  
		} catch (ClassNotFoundException cnfe) {
			System.out.println("����̺� �ε� ����!!!"); }
		// ���� ����
		Connection conn = null;          // 2��
		PreparedStatement pstmt = null;  // 3,4�� 
		ResultSet rs = null;             // 5��
		
		try {
		// 2. DB����(Connection ��ü ����)
		String url = "jdbc:mysql://127.0.0.1/demo";
		conn = DriverManager.getConnection(url, "root", "root1106");
		
		// 3,4. ������ �ۼ�, PrepareStatement ��ü ����
		 String sql = "select empno, ename, hiredate, sal from emp order by ename asc";
		 pstmt = conn.prepareStatement(sql);
		 
		// 5. Result ��ü�κ��� ������ ����
		rs = pstmt.executeQuery();  // rs�� ǥ�������� �����Ͱ� �� �ִ�. 
		
		while(rs.next()) {
			int empno = rs.getInt(1);            
			String ename = rs.getString(2);  
			String hiredate = rs.getString(3);
			double sal =  rs.getDouble(4);
			System.out.printf("%6d %10s %22s %10.2f\n", empno, ename, hiredate, sal);
			}
		
		// executeQuery�� ���� ����ó��
		} catch (SQLException se) {
			System.out.println("DB���� ���� �߻�...");
			se.printStackTrace();
		} finally {
			// ��������(�������� ������ �ͺ��� ����)
			try {
				if(rs!=null) { rs.close(); }        // ������ ���� �����͸� ���ؼ�
				if(pstmt!=null) { pstmt.close(); }
				if(conn!=null) { conn.close(); }
			}catch(Exception e) {}
		} 
	}
 	public static void main(String[] args) {
 		new SelectTest().start();
	}
}
