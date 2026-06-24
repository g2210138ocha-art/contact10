package bean;

public class Form {
	//フィールド変数の定義
	private int no; //問い合わせ番号を格納する変数
	private String name; //名前を格納する変数
	private String mail; //メールアドレスを格納する変数
	private String age; //年齢を格納する変数
	private String date; //日付を格納する変数
	private int sex; //性別を格納する変数
	private String address; //住所を格納する変数
	private int kind; //問い合わせ項目を格納する変数
	private String report; //問い合わせ内容を格納する変数
	private int status; //状態(0:未返信、1:返信済み)を格納する変数
	private String updated_at; //返信した日時
	private String responsed_by; //返信者のユーザーidを格納する変数
	private String response; //返信内容

	//コンストラクタ定義
	public Form() {
		this.no = 0;
		this.name = null;
		this.mail = null;
		this.age = null;
		this.date = null;
		this.sex = 0;
		this.address = null;
		this.kind = 0;
		this.report = null;
		this.status = 0;
		this.updated_at = null;
		this.responsed_by = null;
		this.response = null;
	}

	//各フィールド変数のGetメソッド定義
	public int getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public String getMail() {
		return mail;
	}

	public String getAge() {
		return age;
	}

	public String getDate() {
		return date;
	}

	public int getSex() {
		return sex;
	}

	public String getAddress() {
		return address;
	}

	public int getKind() {
		return kind;
	}

	public String getReport() {
		return report;
	}

	public int getStatus() {
		return status;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public String getResponsed_by() {
		return responsed_by;
	}

	public String getResponse() {
		return response;
	}

	//各フィールド変数のSetメソッド定義
	public void setNo(int no) {
		this.no = no;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public void setResponsed_by(String responsed_by) {
		this.responsed_by = responsed_by;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
