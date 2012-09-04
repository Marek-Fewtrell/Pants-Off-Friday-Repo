package project.client;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface SolarPowerServiceAsync {
	void SolarPowerServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
