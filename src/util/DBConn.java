package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DBConn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getConn();
	}
	
	public static Connection getConn() {
//		String url = "jdbc:mysql://localhost:3306/gjun";
//		String id = "root";
//		String password = "12341234";
//		String driver = "com.mysql.cj.jdbc.Driver";
		
		//JNDI
		Connection cn = null;
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/AI_Bartender");
			cn = ds.getConnection();
			System.out.println("connection:"+cn.getCatalog()+"<br>");
		} catch (NamingException|SQLException e) {
			System.out.println("Error: "+e.getMessage());
		}
		return cn;
	}
}
