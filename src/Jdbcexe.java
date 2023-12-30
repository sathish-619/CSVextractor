package dhoni;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbcexe {

 
	    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/nobita";
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "12345";

	    public static Connection getConnection() throws SQLException {
	    	
	        
			return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			
	    }
	    public static List<String> getDataFromTable(Connection connection, String tableName) {
	        List<String> result = new ArrayList<>();

	        
	        String selectQuery = "SELECT * FROM " + tableName;

	        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            
	            int columnCount = resultSet.getMetaData().getColumnCount();
	            List<String> header = new ArrayList<>();
	            for (int i = 1; i <= columnCount; i++) {
	                header.add(resultSet.getMetaData().getColumnName(i));
	            }
	            result.add(String.join(",", header));

	           
	            while (resultSet.next()) {
	                List<String> rowData = new ArrayList<>();
	                for (int i = 1; i <= columnCount; i++) {
	                    rowData.add(resultSet.getString(i));
	                }
	                result.add(String.join(",", rowData));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return result;
	    }
	    public static List<String> getHeader(Connection connection, String tableName) {
	        List<String> result = new ArrayList<>();

	       
	        String selectQuery = "SELECT * FROM " + tableName;

	        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	        	
	            int columnCount = resultSet.getMetaData().getColumnCount();
	            List<String> header = new ArrayList<>();
	            for (int i = 1; i <= columnCount; i++) {
	                header.add(resultSet.getMetaData().getColumnName(i));
	            }
	            result.add(String.join(",", header));

	            

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return result;
	    }

	}


