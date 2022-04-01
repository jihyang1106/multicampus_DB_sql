import java.sql.*;

public class MysqlConnection {
	// ������� ����, ��ü ���� / ���� ���๮�� ��������� ����� �� ����

	// 1. ����̺�ε�
	static { // static{}�� ���๮�� ����� �� �ִ� ������ �ȴ�.(��������� �ִ� ���๮)
		// ���๮, �������
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("����̺�ε� ���� �߻�!");
		}
	}

	// ��������� ������ �����Ѵ�.
	// ��ӹ��� �ٸ� ��Ű�������� ����� �� �ְ� ���������ڸ� protected��
	protected Connection conn = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;

	// 2. �����ͺ��̽� ����޼��� (��ӽ����� ���̱� ������ ��ȯ�� x)
	public void getConn() {
		String url = "jdbc:mysql://localhost/multicampus";
		String id = "root";
		String password = "root1106";
		try {
			conn = DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� ���� ���� �߻�!");
		}
	}

	// 3. �����ͺ��̽� ����޼���
	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �ݱ� ���� �߻�!");
		}
	}

}
