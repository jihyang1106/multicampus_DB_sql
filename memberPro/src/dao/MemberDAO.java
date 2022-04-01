package dao;

import java.util.ArrayList;
import java.util.List;

import dbCon.MysqlConnection; // ��ӹް� ��Ű���� �ٸ��� ������ import 
import dto.MemberDTO;

public class MemberDAO extends MysqlConnection{
	// ȸ������
	
	public List<MemberDTO> memberList() {
		// dto��ü�� ���� ArrayList��ü�� ����
		
		// MemberDTO ��ü�� ���� �� ����
		 List<MemberDTO> list= new ArrayList<MemberDTO>();
		 
		 try {
			 getConn(); // DB����
			 
			 //select
			 String sql = "SELECT * FROM member ORDER BY num ";
			 
			 pstmt = conn.prepareStatement(sql);
			 
			 //rs
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) { // sql�κ��� ���� ������ dto�� �ֱ�
				 MemberDTO dto = new MemberDTO(); // �ѻ���� ������ ������ ���ִ� ���� ����
				 // ������Ÿ���� memberdto�̰�, ������ dto
				 dto.setNum(rs.getInt(1));  // ���� �о DTO���ٰ� �ִ´�. 
				 dto.setUsername(rs.getString(2));
				 dto.setTel(rs.getString(3));
				 dto.setEmail(rs.getString(4));
				 dto.setBirth(rs.getString(5));
				 dto.setGender(rs.getString(6));
				 dto.setRegisterdate(rs.getString(7));
				 
				 list.add(dto); // �÷��ǿ��ٰ� ����ؼ� �߰��Ѵ�.(���ڵ� ����ŭ) �ϳ��� �÷��� dto��� ��ü�� ��� �۾��� �Ѵ�. 
			 }
			 
			    // select
				// rs
				// dto, dto, dto, dto
				
		 } catch(Exception e) {
			 System.out.println("ȸ������ ���ܹ߻�!");
			 e.printStackTrace();
		 } finally {
			 // �����ͺ��̽� �ݱ�
			 dbClose();
		 }
		 return list; // dto�� list�� ��� 
	}
	
	// ȸ�����
	public int memberInsert(MemberDTO dto) {  // int�� ������ executeUpdate()�� int�� ��ȯ��
		int result = 0; // ����� ���Ͻ�ų ����(insert�� �Ǹ� result�� �� �߰�)
		try {
			// DB����
			getConn();
			
			String sql = "INSERT INTO member(username, tel, email, birth, gender) VALUES(?,?,?,?,?)";
					
			pstmt = conn.prepareStatement(sql);
			// ���� ����(����ǥ ����)
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getTel());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getBirth());
			pstmt.setString(5, dto.getGender());
			
			// �߰��� ���ڵ��� ���� ��ȯ(�������� 0�� �ƴϸ� 1��)
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("ȸ����� ���ܹ߻�!");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;  
	}
	
	// ȸ������(tel�� email)
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
			
			// ����ó, �̸��� setting
			if(que.equals("1")) {
				pstmt.setString(1, dto.getTel());
			} else if(que.equals("2")) {
				pstmt.setString(1, dto.getEmail());
			}
			pstmt.setInt(2, dto.getNum());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("ȸ�� ���� ���� �߻�! ");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	
	// ȸ������
	public int memberDelete(int num) {
		int result = 0;
		try {
			getConn();
			
			String sql = "DELETE FROM member WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("ȸ������  ���ܹ߻�!");
		}finally {
			dbClose();
		}
		return result;
	}
	
	// ȸ����ü ���(������ ���� �޼���)
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
