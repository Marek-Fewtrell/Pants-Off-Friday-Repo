package project.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface SetupServiceAsync {
	void SetupServer(AsyncCallback<ArrayList<ArrayList<String>>> callback)
			throws IllegalArgumentException;
}
