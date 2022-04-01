import java.util.Scanner;

public class BookTest {


	public static void main(String[] args) throws Exception {
		// ���� ������ �Է� �޾Ƽ� BookDAO Ŭ������ insertBook()ȣ���ϰ� ����
		// �Է� ������: B004 �ڹٽ�ũ��Ʈ ���浿 2020 28000 �������ǻ�
		// BookDAO Ŭ������ selectBook() ȣ���ؼ� ������ ��µǵ��� ��
		Scanner scan = new Scanner(System.in);
		
		BookDTO dto = new BookDTO();
		BookDAO dao = new BookDAO();
		
		dao.selectBook();

		System.out.print("���� ��ȣ > ");
		dto.setBookNo(scan.nextLine());
		
		System.out.print("���� ���� > ");
		dto.setBookTitle(scan.nextLine());
		
		System.out.print("���� > ");
		dto.setBookAuthor(scan.nextLine());
		
		System.out.print("���࿬�� > ");
		dto.setBookYear(scan.nextInt());
		
		System.out.print("���� > ");
		dto.setBookPrice(scan.nextInt());
		
		scan.nextLine();
		
		System.out.print("���ǻ� > ");
		dto.setBookPublisher(scan.nextLine());
		
		int result = dao.insertBook(dto);
		
		if (result > 0) {
			System.out.println(dto.getBookTitle() + "�� ������ ��ϵǾ����ϴ�.");
		} else {
			System.out.println(dto.getBookTitle() + "�� ������ �����Ͽ����ϴ�.");
		}
		
		dao.selectBook();
		scan.close();
	}
}
