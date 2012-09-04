package project.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("calc")
public interface SolarPowerService extends RemoteService {
	String SolarPowerServer(String name) throws IllegalArgumentException;
}

