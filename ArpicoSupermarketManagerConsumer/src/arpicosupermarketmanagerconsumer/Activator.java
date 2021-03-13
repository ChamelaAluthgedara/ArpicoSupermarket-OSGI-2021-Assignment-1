package arpicosupermarketmanagerconsumer;

import arpicosupermarketserviceproducer.items.Item;
import arpicosupermarketserviceproducer.managerService.ArpicoManagerConsumerService;
import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference; 

public class Activator implements BundleActivator {
	ServiceReference ManagerServiceReference;
	Scanner input = new Scanner(System.in);

	String keyPress = null; 
	boolean exit = false;
	String naviagteMessage = "\nPRESS 0 TO BACK TO MAIN-MENU OR PRESS ANY KEY TO CONTINUE ...";
	String errorMessage = "Something went wrong !!! Re-enter a Name.";

	@Override
	public void start(BundleContext context) throws Exception {// Start life cycle method
		System.out.println(" ************ Starting .... Arpico Supermarket Manager Consumer ************ ");
		System.out.println(" ************    Arpico Supermarket Manager Consumer Started ************ ");
		ManagerServiceReference = context.getServiceReference(ArpicoManagerConsumerService.class.getName());
		@SuppressWarnings("unchecked")
		ArpicoManagerConsumerService managerService = (ArpicoManagerConsumerService) context.getService(ManagerServiceReference);

		do {
			int userOption = 7;
			do {
				System.out.println(
						" \n\n```````````````````` Welcome to Arpico Items Management System ```````````````````` \n");

				System.out.print(
						"What do you want to do ? Please Select an option to continue ...\n\n"
						+ "Options,\n\n"
						+ "1.Add a new Item. \n"
						+ "2.Update An Items's Details.\n"
						+ "3.Delete An Item. \n"
						+ "4.List Items. \n"
						+ "5.Search Product by Name. \n"
						+ "6.Exit.\n"
						+ "\nPLEASE ENTER YOUR SELECTION: "
						); 
				
				userOption = input.nextInt();

				input.nextLine();
				if (userOption == 6) {
					exit = true;
				}

				if (userOption != 1 && userOption != 2 && userOption != 3 && userOption != 4 && userOption != 5 && userOption != 6) {
					System.out.print("PLEASE ENTER A VALID SELECTION ...");
				}
			} while (userOption != 1 && userOption != 2 && userOption != 3 && userOption != 4 && userOption != 5 && userOption != 6);

			if (userOption == 1) {
				do {
					System.out.print("Item Name: ");
					String storeItemName = input.nextLine();

					System.out.print("Item Price: ");
					double storeItemPrice = input.nextDouble();

					System.out.print("Discount(%): ");
					double storeItemDiscount = input.nextDouble();
					input.nextLine();
					int systemResult = managerService.addNewItems(storeItemName, storeItemPrice, storeItemDiscount);	 
					System.out.println((systemResult == 1 ? "\nItem Successfully Added !!!" : errorMessage) + "\n" + naviagteMessage); 

					keyPress = input.nextLine();

				}

				while (!(keyPress.equals("0")));

			} else if (userOption == 2) {
				// Handles the updating process of an item in the list
				do {
					System.out.print("Item Name: ");
					String updatedItemName = input.nextLine();

					System.out.print("Item Price: ");
					double updatedItemPrice = input.nextDouble();

					System.out.print("Discount(%):");
					double updatedItemDiscount = input.nextDouble();
					input.nextLine();

					int itemsResult = managerService.updateExistingItems(updatedItemName, updatedItemPrice, updatedItemDiscount);
					// Consumes the ManagerService updateItems()
					System.out.println((itemsResult == 1 ? "Item Successfully Updated !!!" : errorMessage) + "\n" + naviagteMessage); 

					keyPress = input.nextLine();

				} while (!(keyPress.equals("0")));

			} else if (userOption == 3) {
				// Handles the removing process of an existing item in the list
				do {
					System.out.println("Enter the item name:");

					String itemName = input.nextLine();
					int itemsResult = managerService.removeExistingItems(itemName); 
					System.out.println((itemsResult == 1 ? "Item Successfully Removed !!!" : errorMessage) + "\n" + naviagteMessage); 


					keyPress = input.nextLine();

				}

				while (!(keyPress.equals("0")));

			} else if (userOption == 4) { 
				do {
					List<Item> itemsList = managerService.storeItemList();
					System.out.println( "------------------------------------------------------------------------------------------");
					System.out.println( " \n********************* Store Items List ********************* \n");
					System.out.println("Item ID" + "\t" + "Item Name" + "\t" + "Item Price" + "\t" + "Discount Percentage(%)" + "\t" + "Item Final Price" + "\t");
					System.out.println( "------------------------------------------------------------------------------------------\n\n");

					for (Item tempItem : itemsList) {
						System.out.println(
										tempItem.getItemId() + "\t          " + 
										tempItem.getItemName() + "\t          " + 
										tempItem.getItemPrice() + "\t          " + 
										tempItem.getDiscount() + "\t                   " + 
										tempItem.getFinalPrice() + "\t" + "\n");

					}
					System.out.println(
							"------------------------------------------------------------------------------------------");
					System.out.println(naviagteMessage);

					keyPress = input.nextLine();

				}

				while (!(keyPress.equals("0")));

			} else if (userOption == 5) {
				// Handles the searching process of an existing item in the list
				do {

					System.out.println("Enter the name of the item for find: ");

					String itemName = input.nextLine();
					int itemResult = managerService.searchExistingItem(itemName);  
					System.out.println((itemResult == 1 ? "ITEM FOUND !!!" : "ITEM NOT FOUND !!!") + "\n" + naviagteMessage); 

					keyPress = input.nextLine();

				}

				while (!(keyPress.equals("0")));
			} else if (userOption == 6) { 
				return;
			}
		} while (!exit);
	}

	@Override
	public void stop(BundleContext context) throws Exception { 
		System.out.println(" ************ Terminating .... Arpico Supermarket Manager Consumer ************ ");
		System.out.println(" ************ Arpico Supermarket Manager Consumer Terminated ************ ");
		context.ungetService(ManagerServiceReference);
	}

}
