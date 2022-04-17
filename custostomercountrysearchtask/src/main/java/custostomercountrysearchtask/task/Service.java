package custostomercountrysearchtask.task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Service {

	private String dbURL = "jdbc:mysql://localhost/customers";
	private String dbUser = "root";
	private String dbPass = "root";

	public Customer modelFromResultSet(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("customer_id");
		String customerName = resultSet.getString("customer_name");
		String contactName = resultSet.getString("contact_name");
		String address = resultSet.getString("address");
		String city = resultSet.getString("city");
		String postalCode = resultSet.getString("postal_code");
		String country = resultSet.getString("country");
		return new Customer(id, customerName, contactName, address, city, postalCode, country);
	}

	public CustomerSearch countrySearchFromResultSet(ResultSet resultSet,String search) throws SQLException {
		String country = resultSet.getString("country");
		int count = resultSet.getInt("count");
		if(count == 0) {
			return new CustomerSearch(search,count);
		}
		return new CustomerSearch(country, count);
	}
	
	public CustomerSearch allCountryFromResultSet(ResultSet resultSet) throws SQLException{
		String country = resultSet.getString("country");
		int count = resultSet.getInt("count");
		
		return new CustomerSearch(country, count);
	}

	public List<Customer> readAll() {
		try (Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");) {
			List<Customer> customers = new ArrayList<>();
			while (resultSet.next()) {
				customers.add(modelFromResultSet(resultSet));
			}
			return customers;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ArrayList<>();
	}

	public Customer read(int id) {
		try (Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM customers WHERE customer_id=?");) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public List<CustomerSearch> sortByCountry() {
		try (Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT country, count(*) AS count FROM customers GROUP BY country");) {
			List<CustomerSearch> result = new ArrayList<>();
			while (resultSet.next()) {
				result.add(allCountryFromResultSet(resultSet));
			}
			return result;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ArrayList<>();
	}

	public CustomerSearch sortByCustomer(String country) {
		try (Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
				PreparedStatement statement = connection
						.prepareStatement("SELECT country, count(*) AS count FROM customers WHERE country=?");)
				 {
			statement.setString(1, country);
			try (ResultSet resultSet = statement.executeQuery();){
				resultSet.next();
				return countrySearchFromResultSet(resultSet,country);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}
}
