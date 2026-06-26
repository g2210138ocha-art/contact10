package bean;

public class User {

	//フィールド変数の定義
	private String userid; //管理者のユーザー名を格納する変数
	private String password; //管理者のパスワードを格納する変数
	private String usermail; //管理者のメールアドレスを格納する変数
	private String username; //管理者の名前を格納する変数
	private int authority; //権限(0:管理者、1:ユーザー)を格納する変数
	private String created_at; //管理者のidを作成した日時を格納する変数

	//コンストラクタ定義
	public User() {
		this.userid = null;
		this.password = null;
		this.usermail = null;
		this.username = null;
		this.authority = 0;
		this.created_at = null;
	}

	//各フィールド変数のGetメソッド定義
	public String getUserid() {
		return userid;
	}

	public String getPassword() {
		return password;
	}

	public String getUsermail() {
		return usermail;
	}

	public String getUsername() {
		return username;
	}

	public int getAuthority() {
		return authority;
	}

	public String getCreated_at() {
		return created_at;
	}

	//各フィールド変数のGetメソッド定義
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

}
