package Pocketmon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PocketmonDBconnection {
	public static Connection POCKETMON;

	public static Connection getConnection() 
	{
	Connection conn=null;
	try {
		String user="kosea";
		String password="kosea2019a";
		String url ="jdbc:oracle:thin:@localhost:1521:orcl";
		Class.forName("oracle.jdbc.driver.OracleDriver");
	conn=DriverManager.getConnection(url,user,password);
	
	System.out.println("DB에 연결 되었다");
	}catch(ClassNotFoundException cnfe) {
		System.out.println("DB 드라이버 로딩 실패 : "+cnfe.toString());
	}catch(SQLException sqle) {
		System.out.println("DB에 접속실패 : "+sqle.toString());
	}catch(Exception e) {
		System.out.println("Unkown error");
		e.printStackTrace();
	}
	return conn;
	}

}