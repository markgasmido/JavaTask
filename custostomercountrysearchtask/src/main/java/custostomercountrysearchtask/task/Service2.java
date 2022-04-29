package custostomercountrysearchtask.task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Service2 {
	private String dbURL = "jdbc:mysql://localhost/customers";
	private String dbUser = "root";
	private String dbPass = "root";

	public Model modelFromResultSet(ResultSet resultSet) throws SQLException {
		String country = resultSet.getString("country");
		String state = resultSet.getString("state");
		String city = resultSet.getString("city");
		return new Model(country, state, city);
	}

	public List<Model> searchUsingCountry(String input) {
		try (Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE country = ?");) {
			statement.setString(1, input);
			List<Model> listCountry = new ArrayList<>();
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					listCountry.add(modelFromResultSet(resultSet));
				}
				return listCountry;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return Collections.emptyList();
	}
}
