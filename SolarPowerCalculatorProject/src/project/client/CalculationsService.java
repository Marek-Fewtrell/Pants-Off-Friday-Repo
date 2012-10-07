package project.client;

import java.util.ArrayList;
import java.util.HashMap;

import project.shared.CalcException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("calcServ")
public interface CalculationsService extends RemoteService {
	ArrayList<ArrayList<String>> CalculationsServer(HashMap<String, String> asd) throws IllegalArgumentException, CalcException;
}

