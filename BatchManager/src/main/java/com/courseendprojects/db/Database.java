package com.courseendprojects.db;

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
	
	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error explicitly loading driver class!");
			e.printStackTrace();
		}
	}
	
	public int executeUpdate(PreparedStatement prst) {
		
		/**
		 * Executes SQL statements that alter the database state.
		 * Suitable for SQL commands like INSERT, UPDATE, and DELETE.
		 * Assumes that the SQL statement is well-formed.
		 * @param prst to be executed
		 * @return Number of rows affected by the operation.
		 * @throws SQLException if the query fails to execute.
		 */
		
		int rows = 0;
		
		try{
			rows = prst.executeUpdate();
			
		} catch(SQLException e){
			System.out.println("Error Executing Update: " + e);
		}
		
		return rows;
	}
	
	public ResultSet executeQuery(PreparedStatement prst) {
		
		/**
		 * Executes SQL SELECT statements against the database.
		 * Assumes that the SQL query is well-formed and that the database connection is valid.
		 * @param prst to be executed
		 * @return ResultSet containing the results of the query.
		 * @throws SQLException if the query fails to execute.
		 */
		
        ResultSet resultSet = null;
        
        try {
            resultSet = prst.executeQuery();
            
        } catch (Exception e) {
            System.out.println("Error Executing Query: " + e);
        }
        
        return resultSet;
    }
}