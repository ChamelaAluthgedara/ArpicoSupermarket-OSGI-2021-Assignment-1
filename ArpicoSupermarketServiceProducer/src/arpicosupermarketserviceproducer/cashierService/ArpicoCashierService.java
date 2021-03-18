package arpicosupermarketserviceproducer.cashierService;

import java.util.List;

import arpicosupermarketserviceproducer.items.Item;

//Service methods which will provide to the cashier consumers
public interface ArpicoCashierService {
	public List<Item> displayExistingItems();

	public int generateCustomerBill(int id, int qty);

	public double displayCutomerCalculatedBillAmount();

	public String[][] dispalyGeneratedCustomerBillDetails();
}
