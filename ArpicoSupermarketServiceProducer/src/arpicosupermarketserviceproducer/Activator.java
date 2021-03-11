package arpicosupermarketserviceproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
 

public class Activator implements BundleActivator {
	ServiceRegistration serviceRegisterer;

	@Override
	public void start(BundleContext context) throws Exception {// Life cycle method-start
		System.out.println("\n\n ************ Starting .... Arpico Manager Producer Service ************ ");
		System.out.println(" ************        Arpico Manager Producer Service Started ************ "); 
		CashierService cashierSer = new CashierServiceImpl();
		serviceRegisterer = context.registerService(CashierService.class.getName().toString(), cashierSer, null); 
		ManagerService managerSer = new ManagerServiceImpl();
		serviceRegisterer = context.registerService(ManagerService.class.getName(), managerSer, null);  
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// Life cycle method-stop
		System.out.println("\n\n ************ Terminating .... Arpico Manager Producer Service ************ ");
		System.out.println(" ************ Arpico Manager Producer Service Terminated ************ "); 
		serviceRegisterer.unregister();
	}

}
