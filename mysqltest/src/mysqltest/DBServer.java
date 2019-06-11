/**
 * mysql db 处理服务
 *
 * author weixin
 * 2019年6月11日 下午11:43:36
 *
 */

package mysqltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBServer {
	static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	static final String db_url = "jdbc:mysql://localhost:3306/role?useSSL=false&serverTimezone=UTC";

	static final String user = "root";
	static final String psw = "wxin1986";

	private Connection conn = null;
	private Statement stmt = null;

	private Boolean _isConnect = false;

	public void connectDB() {
		try {
			Class.forName(jdbc_driver);

			// 连接数据库
			System.out.println("连接数据库");
			this.conn = DriverManager.getConnection(db_url, user, psw);
			this._isConnect = true;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	
	public void doSQL(String sql) {
		if (!this._isConnect) {
			//线连接数据库
			this.connectDB();
			return;
		}
		
		try {
			if (this.stmt == null) {
				this.stmt = this.conn.createStatement();
			}
			
			ResultSet rs = this.stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("uid");
				String name = rs.getString("name");
				int sex = rs.getInt("sex");
				
				System.out.print("ID: " + id);
				System.out.print("  Name: " + name);
				System.out.println("  Sex: " + (sex == 1 ? "男" : "女"));
			}
			
			rs.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeDB() {
		System.out.println("关闭数据库");
		this._isConnect = false;
		try {
			if (this.stmt != null) {
				this.stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (this.conn != null) {
				this.conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
