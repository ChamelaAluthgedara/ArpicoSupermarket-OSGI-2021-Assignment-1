package arpicosupermarketserviceproducer.cashierServiceImplement;

import java.util.List;

import arpicosupermarketserviceproducer.cashierService.ArpicoCashierService;
import arpicosupermarketserviceproducer.dataStore.ArpicoDataStore;
import arpicosupermarketserviceproducer.items.Item;
 

public class ArpicoCashierCunsumerServiceImplementation implements ArpicoCashierService {
	private List<Item> itemList = ArpicoDataStore.storeItemsList;// Item list details in the supermarket
	private double bill;// to store the bill value
	private String[][] generatedBilldetails = new String[1000][4]; 
	// To store the purchased item's details ,Assumption:only 1000
	// different items are allowed for an order
	private int items = -1; // to store the item count [starts from 0]

	@Override
	public List<Item> displayExistingItems() {
		return ArpicoDataStore.storeItemsList;

	}

	public int generateCustomerBill(int id, int qty) {

		boolean valid = false;
		Item selectedItem = null;
		for (Item tempItem : itemList) {
			if (id == tempItem.getItemId()) {
				selectedItem = tempItem;
				valid = true;
				items++;
				break;
			}
		}
		if (valid) {

			this.bill = this.bill + (selectedItem.getFinalPrice() * qty);

			generatedBilldetails[items][0] = Integer.toString(id);
			generatedBilldetails[items][1] = selectedItem.getItemName();

			generatedBilldetails[items][2] = Integer.toString(qty);
			generatedBilldetails[items][3] = Double.toString((selectedItem.getFinalPrice() * qty));

			return 1;
		} else {
			return -1;
		}
	}

	public double displayCutomerCalculatedBillAmount() {
		double generatedBill = this.bill;
		generateNewBill();

		return generatedBill;

	}

	public String[][] dispalyGeneratedCustomerBillDetails() {

		return generatedBilldetails;
	}

	public void generateNewBill() {// To reset all relevant fields to default values
		this.bill = 0;
		this.items = -1;
	}
}
