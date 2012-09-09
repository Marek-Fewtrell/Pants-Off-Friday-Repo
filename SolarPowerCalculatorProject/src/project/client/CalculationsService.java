package project.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("calcServ")
public interface CalculationsService extends RemoteService {
	ArrayList<String> CalculationsServer(ArrayList<String> asd) throws IllegalArgumentException;
}

