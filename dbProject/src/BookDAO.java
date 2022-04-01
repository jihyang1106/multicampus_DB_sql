
public class BookDAO extends MysqlConnection {

	// book ���̺� �ִ� ��� ������ ��� 
	public void selectBook() {
		try {
			getConn();
			String sql= "SELECT * FROM book";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.printf("%s\t %s\t %s\t %d\t %d\t %s\t \n", 
						rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getInt(5), rs.getString(6));
			}
		}catch(Exception e) {
			System.out.println("ȸ����� �ҷ����� �����Ͽ����ϴ�.");
			e.printStackTrace();
		}finally {
			dbClose();
		}
	}
	
	// book ���̺� ������ ����
	public int insertBook(BookDTO dto) {
		int result = 0;
		try {
			
		getConn();
		String sql = "INSERT INTO Book(bookNo, bookTitle, bookAuthor, bookYear, bookPrice, bookPublisher) VALUES(?,?,?,?,?,?)";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getBookNo());
		pstmt.setString(2, dto.getBookTitle());
		pstmt.setString(3, dto.getBookAuthor());
		pstmt.setInt(4, dto.getBookYear());
		pstmt.setInt(5, dto.getBookPrice());
		pstmt.setString(6, dto.getBookPublisher());

		result = pstmt.executeUpdate();
		
		}catch(Exception e) {
			System.out.println("�������� ���� �����߽��ϴ�.");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	} 
}













