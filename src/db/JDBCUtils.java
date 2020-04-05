/**
 * 
 */
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import exception.DBException;

/**
 * 
 * JDBC 的工具类
 *
 */
public class JDBCUtils {

	private static DataSource dataSource = null;
	
	static{
		//数据源只能被创建一次，多次的话成本太高
		dataSource = new ComboPooledDataSource("javawebapp");
	}
	
	//获取数据库连接
	public static Connection getConnection(){  
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("数据库连接错误!");
		}
	}
 
	//关闭数据库连接
	public static void release(Connection connection) {
		try {
			if(connection != null){
				connection.close();//并没有真正的关闭数据库的物理连接，只是将连接归还到了连接池
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("数据库连接错误!");
		}
	}
	
	//关闭数据库连接
	public static void release(ResultSet rs, Statement statement) {
		try {
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("数据库连接错误!");
		}
		
		try {
			if(statement != null){
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("数据库连接错误!");
		}
	}
	
}
