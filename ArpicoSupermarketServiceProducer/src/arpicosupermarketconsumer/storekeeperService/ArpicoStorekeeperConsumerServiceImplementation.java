package arpicosupermarketconsumer.storekeeperService;

import java.util.List;

import arpicosupermarketserviceproducer.dataStore.ArpicoDataStore;
import arpicosupermarketserviceproducer.items.Item;
import arpicosupermarketserviceproducer.storekeeperService.ArpicoSupermarkertStoreKeeperConsumer;

public class ArpicoStorekeeperConsumerServiceImplementation implements ArpicoSupermarkertStoreKeeperConsumer {

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
