package arpicosupermarketserviceproducer.cashierService;

import java.util.List;

import arpicosupermarketserviceproducer.items.Item;
 
//Service methods which will provide to the cashier consumers
public interface CashierService {
	public List<Item> displayItems();//return the item list with item details
	public int generateBill(int id,int qty);//calculates the on going bill continuously
	public double displayFinalBillAmount();//displays the final bill amount
	public String[][] dispalybillDetails();//returns the purchased item list wit details
}
