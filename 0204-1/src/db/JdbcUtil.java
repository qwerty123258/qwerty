package db;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class JdbcUtil {
	public static Connection getConnection(){
		Connection con = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = 
					(Context)initCtx.lookup("java:comp/env");
			DataSource ds = 
					(DataSource)envCtx.lookup("jdbc/OracleDB");
			con = ds.getConnection();
			con.setAutoCommit(false);
			System.out.println("DB접속성공");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void close(Connection con){
		try { 
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
		}
	public static void close(Statement stmt){
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		}
	public static void close(ResultSet rs){
		try { rs.close();
		}
		catch (Exception e) {	
			e.printStackTrace();
		}	
		}
	public static void commit(Connection con){
		try {
			con.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		}
	public static void rollback(Connection con){
		try {
			con.rollback();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
