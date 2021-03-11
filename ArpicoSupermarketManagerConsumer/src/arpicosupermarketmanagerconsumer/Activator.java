package arpicosupermarketmanagerconsumer;

import arpicosupermarketserviceproducer.items.Item;
import arpicosupermarketserviceproducer.managerService.ManagerService;
import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference; 

public class Activator implements BundleActivator {
	ServiceReference ManagerServiceReference;
	Scanner input = new Scanner(System.in);

	boolean exit = false;
	String naviagteMessage = "\nPRESS 0 TO BACK TO MAIN-MENU OR PRESS ANY KEY TO CONTINUE ...";
	String errorMessage = "Something went wrong !!! Re-enter a Name.";

	@Override
	public void start(BundleContext context) throws Exception {// Start life cycle method
		System.out.println(" ************ Starting .... Arpico Supermarket Manager Consumer ************ ");
		System.out.println(" ************    Arpico Supermarket Manager Consumer Started ************ ");
		ManagerServiceReference = context.getServiceReference(ManagerService.class.getName());
		@SuppressWarnings("unchecked")
		ManagerService managerService = (ManagerService) context.getService(ManagerServiceReference); // Instance of
																										// managerService

		do {
			int selection = 7;
			do {
				System.out.println(
						" \n\n```````````````````` Welcome to Arpico Items Management System ```````````````````` \n");

				System.out.println("What do you want to do ? Please Select an option to continue ...\n");
				System.out.println("Options,\n");
				System.out.println("1.Add a new Item. ");
				System.out.println("2.Update An Items's Details.");
				System.out.println("3.Delete An Item. ");
				System.out.println("4.List Items. ");
				System.out.println("5.Search Product by Name. ");
				System.out.println("6.Exit.\n");

				System.out.print("PLEASE ENTER YOUR SELECTION: ");
				selection = input.nextInt();

				input.nextLine();
				if (selection == 6) {
					exit = true;
				}

				if (selection != 1 && selection != 2 && selection != 3 && selection != 4 && selection != 5 && selection != 6) {
					System.out.println("PLEASE ENTER A VALID SELECTION ...");
				}
			} while (selection != 1 && selection != 2 && selection != 3 && selection != 4 && selection != 5 && selection != 6);

			String backToHome = null;
			// Handles the adding process of new items to the list
			if (selection == 1) {
				do {
					System.out.print("Item Name: ");
					String itemName = input.nextLine();

					System.out.print("Item Price: ");
					double itemPrice = input.nextDouble();

					System.out.print("Discount(%): ");
					double itemDiscount = input.nextDouble();
					input.nextLine();
					int result = managerService.addItems(itemName, itemPrice, itemDiscount);// Consumes the
																							// ManagerService addItems()
					String msg = (result == 1) ? "\nItem Successfully Added !!!" : errorMessage;
					System.out.println(msg);
					System.out.println(naviagteMessage);

					backToHome = input.nextLine();

				}

				while (!(backToHome.equals("0")));

			} else if (selection == 2) {
				// Handles the updating process of an item in the list
				do {
					System.out.println("Item Name");
					String updatedItemName = input.nextLine();

					System.out.println("Item Price");
					double updatedItemPrice = input.nextDouble();

					System.out.println("Discount");
					double updatedItemDiscount = input.nextDouble();
					input.nextLine();

					int result = managerService.updateItems(updatedItemName, updatedItemPrice, updatedItemDiscount);
					// Consumes the ManagerService updateItems()

					String msg = (result == 1) ? "Item Successfully Updated !!!" : errorMessage;
					System.out.println(msg);
					System.out.println(naviagteMessage);

					backToHome = input.nextLine();

				} while (!(backToHome.equals("0")));

			} else if (selection == 3) {
				// Handles the removing process of an existing item in the list
				do {
					System.out.println("Enter the item name:");

					String itemName = input.nextLine();
					int result = managerService.removeItems(itemName);
					// Consumes the ManagerService removeItems()
					String msg = (result == 1) ? "Item Successfully Removed !!!" : errorMessage;
					System.out.println(msg);
					System.out.println(naviagteMessage);

					backToHome = input.nextLine();

				}

				while (!(backToHome.equals("0")));

			} else if (selection == 4) {
				// Handles displaying all items in the list
				do {
					List<Item> itemsList = managerService.listItems();// Consumes the ManagerService listItems()
					System.out.println( "-----------------------------------------------------------------------------------------");
					System.out.println( " \n********************* Store Items List ********************* \n");
					System.out.println("Item ID" + "\t" + "Item Name" + "\t" + "Item Price" + "\t" + "Discount Percentage(%)" + "\t" + "Item Final Price" + "\t");
					System.out.println( "-----------------------------------------------------------------------------------------\n\n");

					for (Item tempItem : itemsList) {
						System.out.println(
										tempItem.getItemId() + "\t         " + 
										tempItem.getItemName() + "\t         " + 
										tempItem.getItemPrice() + "\t         " + 
										tempItem.getDiscount() + "\t                  " + 
										tempItem.getFinalPrice() + "\t" + "\n");

					}
					System.out.println(
							"-----------------------------------------------------------------------------------------");
					System.out.println(naviagteMessage);

					backToHome = input.nextLine();

				}

				while (!(backToHome.equals("0")));

			} else if (selection == 5) {
				// Handles the searching process of an existing item in the list
				do {

					System.out.println("Enter the name of the item for find: ");

					String itemName = input.nextLine();
					int result = managerService.searchitems(itemName);
					// Consumes the ManagerService searchItems()
					String msg = (result == 1) ? "ITEM FOUND !!!" : "ITEM NOT FOUND !!!";
					System.out.println(msg);

					System.out.println(naviagteMessage);

					backToHome = input.nextLine();

				}

				while (!(backToHome.equals("0")));
			} else if (selection == 6) {
				// Exits form the Manager consumer program
				return;
			}
		} while (!exit);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// Stop Manager Consumer life cycle method
		System.out.println(" ************ Terminating .... Arpico Supermarket Manager Consumer ************ ");
		System.out.println(" ************ Arpico Supermarket Manager Consumer Terminated ************ ");
		context.ungetService(ManagerServiceReference);
	}

}
