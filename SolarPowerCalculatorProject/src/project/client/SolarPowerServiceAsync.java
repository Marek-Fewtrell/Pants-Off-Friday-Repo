package project.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface SolarPowerServiceAsync {
	void SolarPowerServer(ArrayList<String> calcumalations, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
