import java.util.List;  // util에 있는 List인지 확인해야 함
import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberStart {
	
	Scanner scan = new Scanner(System.in);
	
	public void start() {

		while (true) {
			menuOutput();
			int menu = Integer.parseInt(scan.nextLine());
			
			// 데이터베이스에서 회원정보 전체를 선택해 콘솔에 출력하는 기능구현
			// 목록 출력
			switch(menu) {
				case 1: memberAllList();  // 전체 회원목록 선택하기
					break;
				case 2: memberInsert();  // 회원 등록
					break;
				case 3: memberEdit();  // 회원 정보 수정
					break;
				case 4: memberDel();  // 회원 삭제
					break;
				case 5: System.exit(0); // 프로그램 종료
					break;
				default: System.out.println("메뉴를 잘못입력하였습니다.");
					break;
			}
		}

	}
	// case1전체 회원목록 선택하기
	public void memberAllList() {
		// dao에 구현해놓은 memberList를 호출하면 된다.
		MemberDAO dao = new MemberDAO();
		List<MemberDTO> list = dao.memberList(); // list에 회원 데이터베이스가 있음[0],[1],[2]...
		// list안에 DTO객체가 있다. 호출하는 법 for(MemberDTO:list) -> 인덱스가 없을 때 편리함
		for(int idx=0; idx<list.size(); idx++) { // 회원수 반복
		// list에서 index위치의 객체 구하여(dto) 콘솔에 출력한다. 
			MemberDTO dto  = list.get(idx);  // idx가 0 0번째, 1이면 1번째 list가 memberDTO 타입, 1명의 회원
			oneMemberOutput(dto); // 한사람의 정보가 들어있는 dto를 매개변수로 넘겨줌(출력하기위해서)
			
//			oneMemberOutput(list.get(idx)); 위의 두줄과 동일
		}
	}
	// case2 회원 등록
	public void memberInsert() {
		// 등록할 회원정보를 입력받아서 DTO 객체에다가 setter를 한다. 
		
		MemberDTO dto = new MemberDTO();
		
		System.out.print("이름 -> ");
		dto.setUsername(scan.nextLine());
		System.out.print("연락처 -> ");
		dto.setTel(scan.nextLine());
		System.out.print("이메일 -> ");
		dto.setEmail(scan.nextLine());
		System.out.print("생년월일(ex:2000-10-10) -> ");
		dto.setBirth(scan.nextLine());
		System.out.print("성별(M,F) -> ");
		dto.setGender(scan.nextLine());
		
		MemberDAO dao = new MemberDAO();
		int result = dao.memberInsert(dto);
		if(result>0) { // 회원등록
			System.out.println(dto.getUsername() + "의 정보가 등록되었습니다.");
		} else { // 등록 실패
			System.out.println(dto.getUsername() + "의 정보가 실패하였습니다.!");
		}
	}
	
	// case3 회원 정보 수정
	public void memberEdit() {
		MemberDTO dto = new MemberDTO();
		// 수정할 회원번호를 입력받고
		System.out.print("수정할 회원번호 -> ");		
		dto.setNum(Integer.parseInt(scan.nextLine()));
		
		// 연락처(tel), 이메일(email)을 확인을 한 후 
		System.out.print("수정할 항목을 입력하세요(1.연락처, 2.이메일) -> ");
		String que = scan.nextLine();
		if(que.equals("1")) { // 연락처(tel)
			System.out.print("수정할 연락처를 입력하세요. -> ");
			dto.setTel(scan.nextLine());
		}else if(que.equals("2")) { // 이메일(email)
			System.out.print("수정할 이메일을 입력하세요. -> ");
			dto.setEmail(scan.nextLine());
		}

		// 데이터베이스 업데이트
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberUpdate(que, dto);
		// 결과
		if(cnt>0) { // 수정됨
			System.out.println("회원정보가 수정되었습니다.");
		} else {
			System.out.println("회원정보 수정실패했습니다.");
		}
	}
	
	
	// case4 회원 삭제 
	public void memberDel() {
		// 삭제할 회원번호
		System.out.println("삭제할 회원번호 -> ");
		int num = Integer.parseInt(scan.nextLine());
		
		MemberDAO dao = new MemberDAO();
		int result = dao.memberDelete(num);
		
		if(result>0) {
			System.out.println(num + "번 회원이 삭제되었습니다.");
		}else {
			System.out.println(num + "번 회원 삭제 실패했습니다.");
		}
		
	}

	// 1명의 회원을 출력하는 메서드
	public void oneMemberOutput(MemberDTO dto) {
		// 번호, 이름, 연락처, 이메일, 생년, 성별, 등록일
		System.out.printf("%5d, %10s, %20s, %20s, %10s, %5s, %20s\n", 
				dto.getNum(), dto.getUsername(), dto.getTel(), dto.getEmail(),
				dto.getBirth(), dto.getGender(), dto.getRegisterdate());
	}
	
	public void menuOutput() {
		System.out.print("[메뉴] 1.회원목록, 2.회원등록, 3.회원수정, 4.회원삭제, 5.종료?");
		
	}
	
	public static void main(String[] args) {
		new MemberStart().start();

	}
	
	public void memberList() {
		MemberDAO dao = new MemberDAO();
		dao.memberList2();  // 모든 회원 출력
	}
}
