import java.util.List;  // util�� �ִ� List���� Ȯ���ؾ� ��
import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberStart {
	
	Scanner scan = new Scanner(System.in);
	
	public void start() {

		while (true) {
			menuOutput();
			int menu = Integer.parseInt(scan.nextLine());
			
			// �����ͺ��̽����� ȸ������ ��ü�� ������ �ֿܼ� ����ϴ� ��ɱ���
			// ��� ���
			switch(menu) {
				case 1: memberAllList();  // ��ü ȸ����� �����ϱ�
					break;
				case 2: memberInsert();  // ȸ�� ���
					break;
				case 3: memberEdit();  // ȸ�� ���� ����
					break;
				case 4: memberDel();  // ȸ�� ����
					break;
				case 5: System.exit(0); // ���α׷� ����
					break;
				default: System.out.println("�޴��� �߸��Է��Ͽ����ϴ�.");
					break;
			}
		}

	}
	// case1��ü ȸ����� �����ϱ�
	public void memberAllList() {
		// dao�� �����س��� memberList�� ȣ���ϸ� �ȴ�.
		MemberDAO dao = new MemberDAO();
		List<MemberDTO> list = dao.memberList(); // list�� ȸ�� �����ͺ��̽��� ����[0],[1],[2]...
		// list�ȿ� DTO��ü�� �ִ�. ȣ���ϴ� �� for(MemberDTO:list) -> �ε����� ���� �� ����
		for(int idx=0; idx<list.size(); idx++) { // ȸ���� �ݺ�
		// list���� index��ġ�� ��ü ���Ͽ�(dto) �ֿܼ� ����Ѵ�. 
			MemberDTO dto  = list.get(idx);  // idx�� 0 0��°, 1�̸� 1��° list�� memberDTO Ÿ��, 1���� ȸ��
			oneMemberOutput(dto); // �ѻ���� ������ ����ִ� dto�� �Ű������� �Ѱ���(����ϱ����ؼ�)
			
//			oneMemberOutput(list.get(idx)); ���� ���ٰ� ����
		}
	}
	// case2 ȸ�� ���
	public void memberInsert() {
		// ����� ȸ�������� �Է¹޾Ƽ� DTO ��ü���ٰ� setter�� �Ѵ�. 
		
		MemberDTO dto = new MemberDTO();
		
		System.out.print("�̸� -> ");
		dto.setUsername(scan.nextLine());
		System.out.print("����ó -> ");
		dto.setTel(scan.nextLine());
		System.out.print("�̸��� -> ");
		dto.setEmail(scan.nextLine());
		System.out.print("�������(ex:2000-10-10) -> ");
		dto.setBirth(scan.nextLine());
		System.out.print("����(M,F) -> ");
		dto.setGender(scan.nextLine());
		
		MemberDAO dao = new MemberDAO();
		int result = dao.memberInsert(dto);
		if(result>0) { // ȸ�����
			System.out.println(dto.getUsername() + "�� ������ ��ϵǾ����ϴ�.");
		} else { // ��� ����
			System.out.println(dto.getUsername() + "�� ������ �����Ͽ����ϴ�.!");
		}
	}
	
	// case3 ȸ�� ���� ����
	public void memberEdit() {
		MemberDTO dto = new MemberDTO();
		// ������ ȸ����ȣ�� �Է¹ް�
		System.out.print("������ ȸ����ȣ -> ");		
		dto.setNum(Integer.parseInt(scan.nextLine()));
		
		// ����ó(tel), �̸���(email)�� Ȯ���� �� �� 
		System.out.print("������ �׸��� �Է��ϼ���(1.����ó, 2.�̸���) -> ");
		String que = scan.nextLine();
		if(que.equals("1")) { // ����ó(tel)
			System.out.print("������ ����ó�� �Է��ϼ���. -> ");
			dto.setTel(scan.nextLine());
		}else if(que.equals("2")) { // �̸���(email)
			System.out.print("������ �̸����� �Է��ϼ���. -> ");
			dto.setEmail(scan.nextLine());
		}

		// �����ͺ��̽� ������Ʈ
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberUpdate(que, dto);
		// ���
		if(cnt>0) { // ������
			System.out.println("ȸ�������� �����Ǿ����ϴ�.");
		} else {
			System.out.println("ȸ������ ���������߽��ϴ�.");
		}
	}
	
	
	// case4 ȸ�� ���� 
	public void memberDel() {
		// ������ ȸ����ȣ
		System.out.println("������ ȸ����ȣ -> ");
		int num = Integer.parseInt(scan.nextLine());
		
		MemberDAO dao = new MemberDAO();
		int result = dao.memberDelete(num);
		
		if(result>0) {
			System.out.println(num + "�� ȸ���� �����Ǿ����ϴ�.");
		}else {
			System.out.println(num + "�� ȸ�� ���� �����߽��ϴ�.");
		}
		
	}

	// 1���� ȸ���� ����ϴ� �޼���
	public void oneMemberOutput(MemberDTO dto) {
		// ��ȣ, �̸�, ����ó, �̸���, ����, ����, �����
		System.out.printf("%5d, %10s, %20s, %20s, %10s, %5s, %20s\n", 
				dto.getNum(), dto.getUsername(), dto.getTel(), dto.getEmail(),
				dto.getBirth(), dto.getGender(), dto.getRegisterdate());
	}
	
	public void menuOutput() {
		System.out.print("[�޴�] 1.ȸ�����, 2.ȸ�����, 3.ȸ������, 4.ȸ������, 5.����?");
		
	}
	
	public static void main(String[] args) {
		new MemberStart().start();

	}
	
	public void memberList() {
		MemberDAO dao = new MemberDAO();
		dao.memberList2();  // ��� ȸ�� ���
	}
}
