package project.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface CalculationsServiceAsync {
	void CalculationsServer(ArrayList<String> stuffToServer, AsyncCallback<ArrayList<ArrayList<String>>> callback)
			throws IllegalArgumentException;
}
