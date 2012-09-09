package project.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface CalculationsServiceAsync {
	void CalculationsServer(ArrayList<String> asd, AsyncCallback<ArrayList<String>> callback)
			throws IllegalArgumentException;
}
