package dao;

import java.util.ArrayList;
import java.util.List;

import dbCon.MysqlConnection; // 상속받고 패키지가 다르기 때문에 import 
import dto.MemberDTO;

public class MemberDAO extends MysqlConnection{
	// 회원선택
	
	public List<MemberDTO> memberList() {
		// dto객체를 담을 ArrayList객체를 생성
		
		// MemberDTO 객체가 들어올 수 있음
		 List<MemberDTO> list= new ArrayList<MemberDTO>();
		 
		 try {
			 getConn(); // DB연결
			 
			 //select
			 String sql = "SELECT * FROM member ORDER BY num ";
			 
			 pstmt = conn.prepareStatement(sql);
			 
			 //rs
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) { // sql로부터 받은 정보를 dto에 넣기
				 MemberDTO dto = new MemberDTO(); // 한사람의 정보를 저장할 수있는 변수 생성
				 // 데이터타입이 memberdto이고, 변수는 dto
				 dto.setNum(rs.getInt(1));  // 값을 읽어서 DTO에다가 넣는다. 
				 dto.setUsername(rs.getString(2));
				 dto.setTel(rs.getString(3));
				 dto.setEmail(rs.getString(4));
				 dto.setBirth(rs.getString(5));
				 dto.setGender(rs.getString(6));
				 dto.setRegisterdate(rs.getString(7));
				 
				 list.add(dto); // 컬렉션에다가 계속해서 추가한다.(레코드 수만큼) 하나의 컬렉션 dto라는 객체를 담는 작업을 한다. 
			 }
			 
			    // select
				// rs
				// dto, dto, dto, dto
				
		 } catch(Exception e) {
			 System.out.println("회원선택 예외발생!");
			 e.printStackTrace();
		 } finally {
			 // 데이터베이스 닫기
			 dbClose();
		 }
		 return list; // dto를 list에 담기 
	}
	
	// 회원등록
	public int memberInsert(MemberDTO dto) {  // int인 이유가 executeUpdate()는 int형 반환함
		int result = 0; // 결과를 리턴시킬 변수(insert가 되면 result에 값 추가)
		try {
			// DB연결
			getConn();
			
			String sql = "INSERT INTO member(username, tel, email, birth, gender) VALUES(?,?,?,?,?)";
					
			pstmt = conn.prepareStatement(sql);
			// 값을 세팅(물픔표 세팅)
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getTel());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getBirth());
			pstmt.setString(5, dto.getGender());
			
			// 추가된 레코드의 수를 반환(에러나면 0개 아니면 1개)
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("회원등록 예외발생!");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;  
	}
	
	// 회원수정(tel과 email)
	public int memberUpdate(String que, MemberDTO dto) {
		int result = 0;
		try {
			getConn();
			
			String sql = "UPDATE MEMBER SET ";
			if(que.equals("1")) {
				sql += " tel=? ";
			}else if(que.equals("2")) {
				sql += " email=? ";
			}
			sql += "WHERE num =?";
			
			pstmt = conn.prepareStatement(sql);
			
			// 연락처, 이메일 setting
			if(que.equals("1")) {
				pstmt.setString(1, dto.getTel());
			} else if(que.equals("2")) {
				pstmt.setString(1, dto.getEmail());
			}
			pstmt.setInt(2, dto.getNum());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("회원 수정 예외 발생! ");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	
	// 회원삭제
	public int memberDelete(int num) {
		int result = 0;
		try {
			getConn();
			
			String sql = "DELETE FROM member WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("회원삭제  예외발생!");
		}finally {
			dbClose();
		}
		return result;
	}
	
	// 회원전체 목록(리턴이 없는 메서드)
	public void memberList2() {
		try {
			getConn();
			String sql = "SELECT * FROM member";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.printf("%5d, %10s, %20s, %20s, %10s, %5s, %20s\n", 
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
			}
		}catch(Exception e) {
			
		}finally {
			dbClose();
		}
	}
	
}
