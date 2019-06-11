/**
 *
 *
 * author weixin
 * 2019年6月11日 上午12:45:42
 *
 */

package mysqltest;

public class Main {
	
	private static DBServer dbServer = null;
	
	public static void main(String[] args) {
		System.out.println("Mysql Test");
		
		dbServer = new DBServer();
		dbServer.connectDB();
		dbServer.doSQL("select * from info");
		dbServer.closeDB();

	}

}
