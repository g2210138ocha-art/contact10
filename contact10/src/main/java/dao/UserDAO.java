package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.User;

public class UserDAO {

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

	//DBのuserinfoテーブルから指定ユーザーとパスワードの条件に合致する情報を取得するメソッド
	public User selectByUserid(String userid, String password) {

		//変数宣言
		Connection con = null;
		Statement smt = null;

		//リターン用オブジェクト宣言
		User user = new User();

		//SQL文発行
		String sql = "SELECT * FROM logininfo WHERE userid ='" + userid + "' AND password='" + password + "'";
		try {
			//データベース接続
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をDTOオブジェクトへ格納
			if (rs.next()) {
				user.setUserid(rs.getString("userid"));
				user.setPassword(rs.getString("password"));
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

		return user;
	}

	//DBのlogininfoテーブルから指定ユーザーのパスワード情報のみを更新するメソッド
	public int updateForPassword(String userid, String password) {

		//変数宣言
		Connection con = null;
		Statement smt = null;

		//return用変数
		int count = 0;

		//SQL文
		String sql = "UPDATE logininfo SET password = '" + password + "' WHERE userid = '" + userid + "'";

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
}