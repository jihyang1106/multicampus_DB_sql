import java.util.Scanner;

public class BookTest {


	public static void main(String[] args) throws Exception {
		// 도서 정보를 입력 받아서 BookDAO 클래스의 insertBook()호출하고 전달
		// 입력 데이터: B004 자바스크립트 강길동 2020 28000 서울출판사
		// BookDAO 클래스의 selectBook() 호출해서 데이터 출력되도록 함
		Scanner scan = new Scanner(System.in);
		
		BookDTO dto = new BookDTO();
		BookDAO dao = new BookDAO();
		
		dao.selectBook();

		System.out.print("도서 번호 > ");
		dto.setBookNo(scan.nextLine());
		
		System.out.print("도서 제목 > ");
		dto.setBookTitle(scan.nextLine());
		
		System.out.print("저자 > ");
		dto.setBookAuthor(scan.nextLine());
		
		System.out.print("발행연도 > ");
		dto.setBookYear(scan.nextInt());
		
		System.out.print("가격 > ");
		dto.setBookPrice(scan.nextInt());
		
		scan.nextLine();
		
		System.out.print("출판사 > ");
		dto.setBookPublisher(scan.nextLine());
		
		int result = dao.insertBook(dto);
		
		if (result > 0) {
			System.out.println(dto.getBookTitle() + "의 정보가 등록되었습니다.");
		} else {
			System.out.println(dto.getBookTitle() + "의 정보가 실패하였습니다.");
		}
		
		dao.selectBook();
		scan.close();
	}
}
