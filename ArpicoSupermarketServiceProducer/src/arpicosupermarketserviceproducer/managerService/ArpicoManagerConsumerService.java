package arpicosupermarketserviceproducer.managerService;

import java.util.List;

import arpicosupermarketserviceproducer.items.Item;

public interface ArpicoManagerConsumerService {

	public int addNewItems(String itemName, double itemPrice, double itemDiscount);

	public int updateExistingItems(String updatedItemName, double updatedItemPrice, double updatedItemDiscount);

	public int removeExistingItems(String itemName);

	public int searchExistingItem(String itemName);

	public List<Item> storeItemList();

}
