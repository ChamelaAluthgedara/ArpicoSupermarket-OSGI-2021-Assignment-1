package arpicosupermerketcashierconsumer;

import arpicosupermarketserviceproducer.CashierService;
import arpicosupermarketserviceproducer.Item;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	ServiceReference cashierServiceReference;
	private boolean exit = false;
	Scanner input = new Scanner(System.in);

	String naviagteMessage = "PRESS 0 TO BACK TO MAIN-MENU OR PRESS ANY KEY TO CONTINUE ...";
	String errorMessage = "Something went wrong !!! Re-enter a Name.";
	
	@Override
	public void start(BundleContext context) throws Exception {
		// Life cycle method-start
		System.out.println("\n\n ************ Starting .... Arpico Supermarket Cashier Consumer ************ ");
		System.out.println(" ************    Arpico Supermarket Cashier Consumer Started ************ ");
		cashierServiceReference = context.getServiceReference(CashierService.class.getName());
		@SuppressWarnings("unchecked")
		CashierService cashierService = (CashierService) context.getService(cashierServiceReference); // Instance of
																										// CashierService
		do {
			int selection = 4;
			do {
				System.out.println("\n\n ```````````````````` Welcome to Arpico Supermarket Billing System ```````````````````` \n");

				System.out.println("What do you want to do ? Please Select an option to continue ...\n");
				System.out.println("Options,\n");
				System.out.println("1.View Items List. ");
				System.out.println("2.Generate Customer Bills. ");
				System.out.println("3.Exit.\n");

				System.out.print("PLEASE ENTER YOUR SELECTION: ");
				selection = input.nextInt();

				input.nextLine();
				if (selection == 3) {// Exits from the cashier consumer program
					exit = true;
				}

				if (selection != 1 && selection != 2 && selection != 3) {
					System.out.println("PLEASE ENTER A VALID SELECTION ...");
				}
			} while (selection != 1 && selection != 2 && selection != 3);

			String backToHome = null;
			String calculateFinalBill = null;
			int itemCount = -1;
			if (selection == 1) {// Handles the viewing process of item list
				do {

					List<Item> itemsList = cashierService.displayItems();// Consumes the CashierService displayItems()
					System.out.println(
							" \n********************* Current Item list ********************* \n");
					System.out.println( "-----------------------------------------------------------------------------------------");
					System.out.println("Item ID" + "\t\\t" + "Item Name" + "\t" + "Item Price" + "\t" + "Discount Percentage(%)" + "\t" + "Item Final Price" + "\t");
					System.out.println( "-----------------------------------------------------------------------------------------\n\n");

					for (Item tempItem : itemsList) {
						System.out.println(
										tempItem.getItemId() + "\t         " + 
										tempItem.getItemName() + "\t         " + 
										tempItem.getItemPrice() + "\t         " + 
										tempItem.getDiscount() + "\t                  " + 
										tempItem.getFinalPrice() + "\t" + "\n");

						System.out.println( "-----------------------------------------------------------------------------------------");

					}

					System.out.println(naviagteMessage);

					backToHome = input.nextLine();
				}

				while (!(backToHome.equals("0")));

			}

			else if (selection == 2) {
				// Handles the billing process
				do {
					do {

						System.out.println( " \n\n *******************************` Customer's Bill `******************************* " + "\n");
						System.out.print("Enter the Item Id: ");
						int id = input.nextInt();

						System.out.print("Enter the Quantity: ");
						int qty = input.nextInt();

						int result = cashierService.generateBill(id, qty);
						// Consumes the CashierService geenrateBill()
						if (result == -1) {
							System.out.println("Error, Please Enter a valid item Number !!!");
						} else {
							itemCount++;
						}
						input.nextLine();
						System.out.println("Press 'y' to Calclate the total Bill or any Key to continue the billing....");
						calculateFinalBill = input.nextLine();

					} while (!(calculateFinalBill.equals("y") || calculateFinalBill.equals("Y")));

					System.out.println("\n\n************************************* Receipt Generated ************************************* \n");
					String[][] billDetails = cashierService.dispalybillDetails();// Consumes the cashierService and displaybillDetails()
					System.out.println( "-----------------------------------------------------------------------------------------");
					String format = "%-20s";
					System.out.printf(format, "Item ID ");
					System.out.printf(format, "Item Name ");
					System.out.printf(format, "Item Price ");
					System.out.printf(format, "Total ");
					System.out.println("");
					System.out.println( "-----------------------------------------------------------------------------------------\n");
					
					for (int i = 0; i <= itemCount; i++) {
						for (int j = 0; j < billDetails[i].length; j++) {

							System.out.printf(format, billDetails[i][j]);

						}
						System.out.println("");
					}
					System.out.println("                                                          ----------");
					System.out.println("Subtotal:                                                   " + cashierService.displayFinalBillAmount());// Consumes the cashierService and displayFinalBillAmount()
					System.out.println("                                                          ----------");
					System.out.println("                                                          ----------");
					System.out.println( "-------------------------------------------------------------------------------------------");
					 
					itemCount = -1;

					System.out.println(naviagteMessage);

					backToHome = input.nextLine();

				}

				while (!(backToHome.equals("0")));
			} else if (selection == 3) {
				stop(context);
				return;
			}
		} while (!exit);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// Life cycle method -stop
		System.out.println(" ************ Terminating .... Arpico Supermarket Manager Consumer ************ ");
		System.out.println(" ************ Arpico Supermarket Cashier Consumer Terminated ************ "); 
		context.ungetService(cashierServiceReference);
	}

}
