package arpicosupermarketserviceproducer.dataStore;

import java.util.ArrayList;
import java.util.List;

import arpicosupermarketserviceproducer.items.Item;
 

//Store the shared data among the producer and the consumers
public class DataStore {
	public static List<Item> itemsList = new ArrayList<Item>();
}
