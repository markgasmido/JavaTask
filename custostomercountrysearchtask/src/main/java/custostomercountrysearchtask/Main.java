package custostomercountrysearchtask;

import custostomercountrysearchtask.task.CustomerSearch;
import custostomercountrysearchtask.task.Model;
import custostomercountrysearchtask.task.Service;
import custostomercountrysearchtask.task.Service2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		Service service = new Service();
		Service2 service2 = new Service2();
		Scanner scanner = new Scanner(System.in);

		// task 1
		String flag  = "end";
		String response = "";
		CustomerSearch result;
		do{
			System.out.println("Number of customers from which country? - respond 'end' to close application");
			response = scanner.nextLine();
			result = service.sortByCustomer(response);
			if(response.toLowerCase().matches("end")) {
				System.out.println("Thank you - application closed");
				
			} else {
				try {
					System.out.println(result.toString());
				} catch (Exception e) {
					System.out.print(e);
				}
			}
			
		}while(!response.toLowerCase().matches(flag));

		// task 2
//		String flag = "end";
//		String response = "";
//		List<Model> result;
//		do {
//			System.out.println("Which country do you want to know about? - respond 'end' to close application");
//			response = scanner.nextLine();
//			if(response.toLowerCase().matches("end")) {
//				System.out.println("Thank you - application closed");
//				
//			} else {
//				try {
//					result = service2.searchUsingCountry(response);
//					Set<String> stateSet = new HashSet<>();
//					Set<String> citySet = new HashSet<>();
//					
//					for(Model model : result) {
//						stateSet.add(model.getState());
//						citySet.add(model.getCity());
//					}
//					System.out.println(response+" having " + stateSet.size() + " states and " + citySet.size() + " cities");
//					
//				}catch(Exception e) {
//					System.out.println(e);
//				}
//			}
//
//		} while (!response.toLowerCase().matches(flag));
	}

}
