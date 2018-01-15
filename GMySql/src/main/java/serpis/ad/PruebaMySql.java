package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaMySql {

	public static void main(String[] args) throws SQLException  {
		//TODO conectar...
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba", "root", "sistemas"
		);
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from categoria order by id");
		while (resultSet.next())
			System.out.printf("%5s %s\n", resultSet.getObject(1), resultSet.getObject(2));
		resultSet.close();
		statement.close();
		
		connection.close();
	}

}
