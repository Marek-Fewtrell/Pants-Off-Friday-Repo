package project.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("calc")
public interface SolarPowerService extends RemoteService {
	String SolarPowerServer(ArrayList<String> calcumalations) throws IllegalArgumentException;
}

