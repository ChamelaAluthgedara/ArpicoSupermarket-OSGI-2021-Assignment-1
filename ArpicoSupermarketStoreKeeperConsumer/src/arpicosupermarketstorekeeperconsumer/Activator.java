package arpicosupermarketstorekeeperconsumer;
import arpicosupermarketserviceproducer.items.Item;
import arpicosupermarketserviceproducer.storekeeperService.ArpicoSupermarkertStoreKeeperConsumer;
import java.util.List;
import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference; 

public class Activator implements BundleActivator {
	@SuppressWarnings("rawtypes")
	ServiceReference StoreKeeperServiceReference;
	Scanner input = new Scanner(System.in);

	String keyPress = null; 
	boolean exit = false;
	String naviagteMessage = "\nPRESS 0 TO BACK TO MAIN-MENU OR PRESS ANY KEY TO CONTINUE ...";
	String errorMessage = "Something went wrong !!! Re-enter a Name.";

	@Override
	public void start(BundleContext context) throws Exception {
		// Start life cycle method
		System.out.println("\n\n************ Starting .... Arpico Store Keeper Consumer ************ ");
		System.out.println(" ************    Arpico Supermarket Store Keeper Started ************ ");
		StoreKeeperServiceReference = context.getServiceReference(ArpicoSupermarkertStoreKeeperConsumer.class.getName());
		@SuppressWarnings("unchecked")
		ArpicoSupermarkertStoreKeeperConsumer managerService = (ArpicoSupermarkertStoreKeeperConsumer) context.getService(StoreKeeperServiceReference);

		do {
			int userOption = 7;
			do {
				System.out.println(
						" \n\n```````````````````` Welcome to Arpico Items Management System ```````````````````` \n");

				System.out.print(
						"What do you want to do ? Please Select an option to continue ...\n\n"
						+ "Options,\n\n"
						+ "1.Update An Items's Details.\n"
						+ "2.List Items. \n"
						+ "3.Search Product by Name. \n"
						+ "4.Exit.\n"
						+ "\nPLEASE ENTER YOUR SELECTION: "
						); 
				
				userOption = input.nextInt();

				input.nextLine();
				if (userOption == 6) {
					exit = true;
				}

				if (userOption != 1 && userOption != 2 && userOption != 3 && userOption != 4) {
					System.out.print("PLEASE ENTER A VALID SELECTION ...");
				}
			} while (userOption != 1 && userOption != 2 && userOption != 3 && userOption != 4);

			if (userOption == 1) {
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


			} else if (userOption == 2) {
				
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

			} else if (userOption == 3) {
				// Handles the searching process of an existing item in the list
				do {

					System.out.println("Enter the name of the item for find: ");

					String itemName = input.nextLine();
					int itemResult = managerService.searchExistingItem(itemName);  
					System.out.println((itemResult == 1 ? "ITEM FOUND !!!" : "ITEM NOT FOUND !!!") + "\n" + naviagteMessage); 

					keyPress = input.nextLine();

				}

				while (!(keyPress.equals("0")));

			} else if (userOption == 4) { 
				return;
				
			}
			
		} while (!exit);
	}

	@Override
	public void stop(BundleContext context) throws Exception { 
		System.out.println(" ************ Terminating .... Arpico Supermarket Store Keeper Consumer ************ ");
		System.out.println(" ************ Arpico Supermarket Manager Store Keeper Terminated ************ ");
		context.ungetService(StoreKeeperServiceReference);
	}

}
