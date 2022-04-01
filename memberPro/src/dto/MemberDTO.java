package dto;

public class MemberDTO {

	// 변수명은 DB설계할 때 만들었던 DB 데이터형(필드형)이랑 동일하게 하는 것이 좋음
	// 외부에서 접근할 수 없도록 막기(접근제한자를 private으로) DTO의 변수는 대부분 private
	// 필요 변수 생성하고 getter와 setter를 생성
	// 회원 정보를 저장할 수있음 
	
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

