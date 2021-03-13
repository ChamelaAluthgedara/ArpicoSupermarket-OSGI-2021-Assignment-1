package arpicosupermarketserviceproducer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import arpicosupermarketserviceproducer.cashierService.ArpicoCashierService;
import arpicosupermarketserviceproducer.cashierServiceImplement.ArpicoCashierCunsumerServiceImplementation;
import arpicosupermarketserviceproducer.managerService.ArpicoManagerConsumerService;
import arpicosupermarketserviceproducer.managerServiceImplementation.ArpicoManagerConsumerServiceImplementation;
 

public class Activator implements BundleActivator {
	ServiceRegistration serviceRegisterer;

	@Override
	public void start(BundleContext context) throws Exception {// Life cycle method-start
		System.out.println("\n\n ************ Starting .... Arpico Manager Producer Service ************ ");
		System.out.println(" ************        Arpico Manager Producer Service Started ************ "); 
		ArpicoCashierService arpicoServiceCashier = new ArpicoCashierCunsumerServiceImplementation();
		serviceRegisterer = context.registerService(ArpicoCashierService.class.getName().toString(), arpicoServiceCashier, null); 
		ArpicoManagerConsumerService arpicoServiceManager = new ArpicoManagerConsumerServiceImplementation();
		serviceRegisterer = context.registerService(ArpicoManagerConsumerService.class.getName(), arpicoServiceManager, null);  
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// Life cycle method-stop
		System.out.println("\n\n ************ Terminating .... Arpico Manager Producer Service ************ ");
		System.out.println(" ************ Arpico Manager Producer Service Terminated ************ "); 
		serviceRegisterer.unregister();
	}

}
