package project.client;

import project.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SolarPowerCalculatorProject implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final SolarPowerServiceAsync solarPowerService = GWT
			.create(SolarPowerService.class);

	final Button sendButton = new Button("Send");
	final TextBox nameField = new TextBox();
	final TextBox numPanels = new TextBox();
	final ListBox panelSelect = new ListBox();
	final ListBox inverter = new ListBox();
	final TextBox daytimeUsage = new TextBox();
	final TextBox efficiencyLoss = new TextBox();
	final TextBox postcode = new TextBox();
	final TextBox suburb = new TextBox();
	final ListBox energyProvider = new ListBox();
	final Label errorLabel = new Label();
	final VerticalPanel vertPan = new VerticalPanel();
	final HorizontalPanel horizPan = new HorizontalPanel();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		vertPan.add(numPanels);
		vertPan.add(panelSelect);
		vertPan.add(inverter);
		vertPan.add(daytimeUsage);
		vertPan.add(efficiencyLoss);
		vertPan.add(postcode);
		vertPan.add(suburb);
		vertPan.add(energyProvider);
		horizPan.add(sendButton);
		horizPan.add(errorLabel);

		numPanels.setText("Insert Number Panels");
		daytimeUsage.setText("Day Time Usage");
		efficiencyLoss.setText("Efficiency Loss");
		postcode.setText("PostCode");
		suburb.setText("Suburb");
		panelSelect.addItem("REC230PE");
		panelSelect.addItem("Second Pan");
		panelSelect.setVisibleItemCount(2);

		inverter.addItem("SB1600TL");
		inverter.addItem("second Invert");

		energyProvider.addItem("Origin");
		energyProvider.addItem("Second Prov");

		// Add the Panel to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("CalInputs").add(vertPan);
		RootPanel.get("CalOther").add(horizPan);

		// Focus the cursor on the first field when the app loads
		numPanels.setFocus(true);
		numPanels.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			/*
			 * public void onKeyUp(KeyUpEvent event) { if
			 * (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			 * sendNameToServer(); } }
			 */

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = numPanels.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				solarPowerService.SolarPowerServer(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Connection with Server - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox
										.setText("Connection with Server - Success");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		// nameField.addKeyUpHandler(handler);
	}
}
