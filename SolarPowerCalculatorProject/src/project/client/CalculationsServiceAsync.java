package project.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface CalculationsServiceAsync {
	void CalculationsServer(HashMap<String, String> stuffToServer, AsyncCallback<ArrayList<ArrayList<String>>> callback)
			throws IllegalArgumentException;
}
