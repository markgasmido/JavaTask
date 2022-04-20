package custostomercountrysearchtask;

import java.util.List;

import custostomercountrysearchtask.task.Customer;
import custostomercountrysearchtask.task.CustomerSearch;
import custostomercountrysearchtask.task.Service;

public class Main {

	public static void main(String[] args) {
		Service service = new Service();

		// select all fields from customer table
		System.out.println();
		System.out.println("~~~~~~~~~~Select All~~~~~~~~~~~~");
		List<Customer> customers = service.getAll();
		for (Customer customer : customers) {
			try {
				System.out.println(customer.toString());
			} catch (Exception e) {
				System.out.print(e);
			}
		}

		// group by country + count
		System.out.println();
		System.out.println("~~~~~~~~~~Count Country~~~~~~~~~~~~");
		List<CustomerSearch> results = service.sortByCountry();
		for (CustomerSearch result : results) {
			try {
				System.out.println(result.toString());
			} catch (Exception e) {
				System.out.print(e);
			}
		}

		// Select using id from customer table
		System.out.println();
		System.out.println("~~~~~~~~~~Select using id = 2 ~~~~~~~~~~~~");
		Customer customer;
		try {
			customer = service.searchId(2);
			System.out.println(customer.toString());
		} catch (Exception e) {
			System.out.println("Please Try Again");
			
		}
		
		// Select using address
		System.out.println();
		System.out.println("~~~~~~~~~~Select using address = Obere Str. 57 ~~~~~~~~~~~~");
		Customer result = service.searchByAddress("Obere Str. 57");
		try {
			System.out.println(result.toString());
		} catch (Exception e) {
			System.out.println("Please try again");
		}
		
		// task
		System.out.println();
		System.out.println("~~~~~~~~~~Task~~~~~~~~~~~~");
		CustomerSearch result2 = service.sortByCustomer("UK");
		try {
			System.out.println(result2.toString());
		} catch (Exception e) {
			System.out.print(e);
		}

	}

}
