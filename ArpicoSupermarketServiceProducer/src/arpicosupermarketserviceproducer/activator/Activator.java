package arpicosupermarketserviceproducer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import arpicosupermarketconsumer.storekeeperService.ArpicoStorekeeperConsumerServiceImplementation;
import arpicosupermarketserviceproducer.cashierService.ArpicoCashierService;
import arpicosupermarketserviceproducer.cashierServiceImplement.ArpicoCashierCunsumerServiceImplementation;
import arpicosupermarketserviceproducer.managerService.ArpicoManagerConsumerService;
import arpicosupermarketserviceproducer.managerServiceImplementation.ArpicoManagerConsumerServiceImplementation;
import arpicosupermarketserviceproducer.storekeeperService.ArpicoSupermarkertStoreKeeperConsumer;
 

public class Activator implements BundleActivator {
	@SuppressWarnings("rawtypes")
	ServiceRegistration serviceRegisterer;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("\n\n ************ Starting .... Arpico Manager Producer Service ************ ");
		System.out.println(" ************        Arpico Manager Producer Service Started ************ "); 
		
		ArpicoCashierService arpicoServiceCashier = new ArpicoCashierCunsumerServiceImplementation();
		serviceRegisterer = context.registerService(ArpicoCashierService.class.getName().toString(), arpicoServiceCashier, null); 
		
		ArpicoManagerConsumerService arpicoServiceManager = new ArpicoManagerConsumerServiceImplementation();
		serviceRegisterer = context.registerService(ArpicoManagerConsumerService.class.getName(), arpicoServiceManager, null);  
		
		ArpicoSupermarkertStoreKeeperConsumer arpicoServiceStoreKeeper = new ArpicoStorekeeperConsumerServiceImplementation();
		serviceRegisterer = context.registerService(ArpicoSupermarkertStoreKeeperConsumer.class.getName(), arpicoServiceStoreKeeper, null); 
	}

	@Override
	public void stop(BundleContext context) throws Exception { 
		System.out.println("\n\n ************ Terminating .... Arpico Manager Producer Service ************ ");
		System.out.println(" ************ Arpico Manager Producer Service Terminated ************ "); 
		serviceRegisterer.unregister();
	}

}
