package dto;

public class MemberDTO {

	// �������� DB������ �� ������� DB ��������(�ʵ���)�̶� �����ϰ� �ϴ� ���� ����
	// �ܺο��� ������ �� ������ ����(���������ڸ� private����) DTO�� ������ ��κ� private
	// �ʿ� ���� �����ϰ� getter�� setter�� ����
	// ȸ�� ������ ������ ������ 
	
	private int num;
	private String username;
	private String tel;
	private String email;
	private String birth;
	private String gender;
	private String registerdate;
	
	
	public MemberDTO() {}

	public int getNum() {
		return num;
	}


	public String getUsername() {
		return username;
	}


	public String getTel() {
		return tel;
	}


	public String getEmail() {
		return email;
	}


	public String getBirth() {
		return birth;
	}


	public String getGender() {
		return gender;
	}


	public String getRegisterdate() {
		return registerdate;
	}

	public void setNum(int num) {
		this.num = num;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}
	
	
	
}

