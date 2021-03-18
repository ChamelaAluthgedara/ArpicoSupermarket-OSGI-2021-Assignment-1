package arpicosupermarketserviceproducer.storekeeperService;

import java.util.List;
import arpicosupermarketserviceproducer.items.Item;

public interface ArpicoSupermarkertStoreKeeperConsumer {

	public int updateExistingItems(String updatedItemName, double updatedItemPrice, double updatedItemDiscount);

	public int searchExistingItem(String itemName);

	public List<Item> storeItemList();

}
