package arpicosupermarketserviceproducer.managerServiceImplementation;

import java.util.List;
import java.util.Scanner;

import arpicosupermarketserviceproducer.dataStore.ArpicoDataStore;
import arpicosupermarketserviceproducer.items.Item;
import arpicosupermarketserviceproducer.managerService.ArpicoManagerConsumerService; 

public class ArpicoManagerConsumerServiceImplementation implements ArpicoManagerConsumerService {

	@Override
	public synchronized int addNewItems(String itemName, double itemPrice, double itemDiscount) {
		Item newItem = new Item(ArpicoDataStore.storeItemsList.size() + 1, itemName, itemPrice, itemDiscount);
		ArpicoDataStore.storeItemsList.add(newItem);
		return 1;
	}

	@Override
	public synchronized int updateExistingItems(String updatedItemName, double updatedItemPrice, double updatedItemDiscount) {
		Item itemToBeUpdated = null;
		boolean invalid = true;
		int count = -1;
		for (Item tempItem : ArpicoDataStore.storeItemsList) {
			count++;
			if (tempItem.getItemName().equalsIgnoreCase(updatedItemName)) {

				itemToBeUpdated = tempItem;
				invalid = false;
				break;
			}

		}
		if (!invalid) {

			itemToBeUpdated.setItemName(updatedItemName);
			itemToBeUpdated.setItemPrice(updatedItemPrice);
			itemToBeUpdated.setDiscount(updatedItemDiscount);
			itemToBeUpdated.calculateFinalPrice();

			ArpicoDataStore.storeItemsList.set(count, itemToBeUpdated);
			return 1;

		} else {
			return -1;
		}

	}

	@Override
	public synchronized int removeExistingItems(String itemName) {

		boolean invalid = true;
		int count = -1;
		for (Item tempItem : ArpicoDataStore.storeItemsList) {
			count++;
			if (tempItem.getItemName().equalsIgnoreCase(itemName)) {

				invalid = false;
				break;
			}
		}
		if (!invalid) {

			ArpicoDataStore.storeItemsList.remove(count);
			return 1;

		} else {
			return -1;
		}
	}

	@Override
	public int searchExistingItem(String itemName) {
		boolean valid = false;

		for (Item tempItem : ArpicoDataStore.storeItemsList) {

			if (tempItem.getItemName().equalsIgnoreCase(itemName)) {

				valid = true;
				break;

			}

		}
		if (valid) {
			return 1;

		} else {
			return -1;
		}

	}

	@Override
	public List<Item> storeItemList() {

		return ArpicoDataStore.storeItemsList;
	}
}
