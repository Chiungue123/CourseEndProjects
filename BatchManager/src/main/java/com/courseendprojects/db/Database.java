package com.courseendprojects.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	// TODO: FUTURE ENHANCEMENT - Connection Pooling
    // 1. Import HikariCP package
    // 2. Initialize HikariConfig and set JDBC URL, username, and password
    // 3. Create a HikariDataSource with the config
    // 4. Use dataSource.getConnection() instead of DriverManager.getConnection()

    // TODO: FUTURE ENHANCEMENT - Logging Framework
    // 1. Import Log4J or SLF4J package
    // 2. Initialize a Logger object
    // 3. Replace System.out.println with logger.info, logger.error, etc.
	
	private String url = System.getenv("DB_URL");
	private String user = System.getenv("DB_USER");
	private String pass = System.getenv("DB_PASS");
	
	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error explicitly loading driver class!");
			e.printStackTrace();
		}
	}
	
	public int executeUpdate(String sql) {
		
		/**
		 * Executes SQL statements that alter the database state.
		 * Suitable for SQL commands like INSERT, UPDATE, and DELETE.
		 * Assumes that the SQL statement is well-formed.
		 * @param sql The SQL statement to execute.
		 * @return Number of rows affected by the operation.
		 * @throws SQLException if the query fails to execute.
		 */
		
		int rows = 0;
		
		try(Connection con = DriverManager.getConnection(url, user, pass);
			PreparedStatement prst = con.prepareStatement(sql)){
			
			System.out.println("Executing SQL " + sql + " ...");
			rows = prst.executeUpdate();
			System.out.println("Query Successfully Executed");
			
		} catch(SQLException ex){
			System.out.println("Error Executing Update: " + sql);
			System.out.println("Error: " + ex);
		}
		
		return rows;
	}
	
	public ResultSet executeQuery(String sql) {
		
		/**
		 * Executes SQL SELECT statements against the database.
		 * Assumes that the SQL query is well-formed and that the database connection is valid.
		 * @param sql The SQL SELECT statement to execute.
		 * @return ResultSet containing the results of the query.
		 * @throws SQLException if the query fails to execute.
		 */
		
        ResultSet resultSet = null;
        
        try(Connection con = DriverManager.getConnection(url, user, pass);
			PreparedStatement prst = con.prepareStatement(sql)) {
        	
        	System.out.println("Executing SQL " + sql + " ...");
            resultSet = prst.executeQuery(sql);
            System.out.println("Statement Executed Successfully");
            
        } catch (Exception e) {
            System.out.println("Error Executing Query: " + e);
        }
        
        return resultSet;
    }	
}