import java.sql.*;

public class InsertTest {
    Connection con = null;           // 멤버변수로 선언
    PreparedStatement pstmt = null;  // 멤버변수로 선언
    public InsertTest() {
        
    }
    public void empInsert() {
        //사원번호, 이름,급여, 입사일을 레코드 추가하기
        try {
            // 1. 드라이브 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. DB연결                                server/dbname
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/demo","root","root1106");
            
            // 3. preparedStatemnet 객체 생성 : 쿼리문을 이용하여
                                                                             /*1  2  3*/
            String sql = "insert into emp(empno, ename, sal, hiredate) values(?, ?, ?, now())";
            pstmt = con.prepareStatement(sql);
            
            // ?에 값을 세팅한다. 번호 순서대로
            pstmt.setInt(1, 5555);
            pstmt.setString(2, "kim");
            pstmt.setDouble(3, 3500.0);  // Int로 해도 상관 없음 (3, 3500으로 해도 상관xx)
            
            // 4. 실행
            int cnt = pstmt.executeUpdate();
            
            if(cnt>0) {
            	System.out.println("레코드 추가 되었습니다.");
            } else {
            	System.out.println("레코드 추가 실패하였습니다.");
            }
            
        } catch (Exception e) {   
            e.printStackTrace();
        } finally {  // 추가가 되던 안되던 닫아줘야 한다.
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