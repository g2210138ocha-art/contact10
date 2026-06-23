package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Form;

public class FormDAO {

	//データベース接続情報
	private static String RDB_DRIVE = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/mycontactdb";
	private static String USER = "root";
	private static String PASS = "root123";

	//データベース接続を行うメソッド
	public static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	//問い合わせ入力データを元にDBのforminfoテーブルに新規登録処理を行うメソッド
	public int insert(Form form) {
		//変数宣言
		Connection con = null;
		Statement smt = null;

		//return用変数
		int count = 0;

		//SQL文
		String sql = "INSERT INTO forminfo (no,name,age,mail,date,sex,address,kind,report,status)"
				+ "VALUES (null,'" + form.getName() + "', " + form.getAge() + ", '" + form.getMail() + "', NOW(), "
				+ form.getSex() + ", '" + form.getAddress() + "', " + form.getKind() + ", '"
				+ form.getReport() + "', 1)";

		try {
			//データベース接続
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			count = smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//リソースの開放
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}

		}
		return count;
	}

	//forminfoから問い合わせ一覧を取得するメソッド
	public ArrayList<Form> selectAll() {
		//変数宣言
		Connection con = null;
		Statement smt = null;

		//return用オブジェクトの生成
		ArrayList<Form> formList = new ArrayList<Form>();

		//SQL文発行
		String sql = "SELECT * FROM forminfo ORDER BY no DESC";

		try {
			//データベース接続
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//検索結果を配列に格納
			while (rs.next()) {
				Form form = new Form();
				form.setNo(rs.getInt("no"));
				form.setName(rs.getString("name"));
				form.setKind(rs.getInt("kind"));
				form.setDate(rs.getString("date"));
				form.setReport(rs.getString("report"));
				form.setStatus(rs.getInt("status"));
				formList.add(form);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//リーソスの開放
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return formList;
	}

	//Noを元にDBのforminfoテーブルから該当データの検索を行うメソッド
	public Form selectByNo(int no) {
		//変数宣言
		Connection con = null;
		Statement smt = null;

		//リターン用オブジェクト宣言
		Form form = new Form();

		//SQL文発行
		String sql = "SELECT * FROM forminfo WHERE no = '" + no + "'";

		try {
			//データベース接続
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をDTOオブジェクトへ格納
			if (rs.next()) {
				form.setNo(rs.getInt("no"));
				form.setName(rs.getString("name"));
				form.setMail(rs.getString("mail"));
				form.setAge(rs.getInt("age"));
				form.setSex(rs.getInt("sex"));
				form.setAddress(rs.getString("address"));
				form.setKind(rs.getInt("kind"));
				form.setDate(rs.getString("date"));
				form.setReport(rs.getString("report"));
				form.setStatus(rs.getInt("status"));
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//リソースの開放
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return form;
	}

	//データベースから指定された問い合わせデータを検索するメソッド
	public ArrayList<Form> search(int no, String name, int kind, String date, String report) {
		//変数宣言
		Connection con = null;
		Statement smt = null;

		//return用のオブジェクトの生成
		ArrayList<Form> formList = new ArrayList<Form>();

		//SQL文発行
		String sql = "SELECT no,name,kind,date,report FROM forminfo " +
				"WHERE no LIKE '%" + no + "%' AND name LIKE '%" + name + "%' AND kind LIKE '%" + kind
				+ "%' AND date LIKE '%" + date + "%' AND report LIKE '%" + report + "%'";

		try {
			//DBに接続
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//検索結果を配列に格納
			while (rs.next()) {
				Form form = new Form();
				form.setNo(rs.getInt("no"));
				form.setName(rs.getString("name"));
				form.setKind(rs.getInt("kind"));
				form.setDate(rs.getString("date"));
				form.setReport(rs.getString("report"));
				form.setStatus(rs.getInt("status"));
				formList.add(form);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//リソースの開放
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return formList;
	}

	//データベースから指定された問い合わせデータを検索するメソッド（未返信、返信済み)
	public ArrayList<Form> search(int status) {
		//変数宣言
		Connection con = null;
		Statement smt = null;

		//return用のオブジェクトの生成
		ArrayList<Form> formList = new ArrayList<Form>();

		//SQL文発行
		String sql = "SELECT * FROM forminfo WHERE status= '" + status + "'";

		try {
			//DBに接続
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//検索結果を配列に格納
			while (rs.next()) {
				Form form = new Form();
				form.setNo(rs.getInt("no"));
				form.setName(rs.getString("name"));
				form.setKind(rs.getInt("kind"));
				form.setDate(rs.getString("date"));
				form.setReport(rs.getString("report"));
				form.setStatus(rs.getInt("status"));
				formList.add(form);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//リソースの開放
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return formList;
	}

	public void updateStatus(Form form) {
		//変数宣言
		Connection con = null;
		Statement smt = null;

		//SQL文発行
		String sql = "UPDATE forminfo SET status=2, responsed_by='" + form.getResponsed_by() + "', updated_at='"
				+ form.getUpdated_at() + "',response='" + form.getResponse() + "' WHERE no="
				+ form.getNo();

		try {
			//変数宣言
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//リソースの開放
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

}
