import java.sql.*;

public class InsertTest {
    Connection con = null;           // ��������� ����
    PreparedStatement pstmt = null;  // ��������� ����
    public InsertTest() {
        
    }
    public void empInsert() {
        //�����ȣ, �̸�,�޿�, �Ի����� ���ڵ� �߰��ϱ�
        try {
            // 1. ����̺� �ε�
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. DB����                                server/dbname
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/demo","root","root1106");
            
            // 3. preparedStatemnet ��ü ���� : �������� �̿��Ͽ�
                                                                             /*1  2  3*/
            String sql = "insert into emp(empno, ename, sal, hiredate) values(?, ?, ?, now())";
            pstmt = con.prepareStatement(sql);
            
            // ?�� ���� �����Ѵ�. ��ȣ �������
            pstmt.setInt(1, 5555);
            pstmt.setString(2, "kim");
            pstmt.setDouble(3, 3500.0);  // Int�� �ص� ��� ���� (3, 3500���� �ص� ���xx)
            
            // 4. ����
            int cnt = pstmt.executeUpdate();
            
            if(cnt>0) {
            	System.out.println("���ڵ� �߰� �Ǿ����ϴ�.");
            } else {
            	System.out.println("���ڵ� �߰� �����Ͽ����ϴ�.");
            }
            
        } catch (Exception e) {   
            e.printStackTrace();
        } finally {  // �߰��� �Ǵ� �ȵǴ� �ݾ���� �Ѵ�.
        	try {
        		if(pstmt !=null) pstmt.close();
        		if(con != null) con.close();
        	} catch (Exception e2) {e2.printStackTrace();}
        }
    }
    public static void main(String[] args) {
        new InsertTest().empInsert();
    }
}