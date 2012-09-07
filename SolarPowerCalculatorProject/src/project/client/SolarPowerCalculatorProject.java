package project.client;

import java.util.ArrayList;

import project.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
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
	//final TextBox nameField = new TextBox();
	final TextBox numPanels = new TextBox();
	final ListBox panelSelect = new ListBox();
	final ListBox inverterSelect = new ListBox();
	final TextBox daytimeUsage = new TextBox();
	final TextBox efficiencyLoss = new TextBox();
	final TextBox postcode = new TextBox();
	final TextBox suburb = new TextBox();
	final ListBox energyProvider = new ListBox();
	final TextBox initInstalCost = new TextBox();
	final TextBox interestRate = new TextBox();
	
	final Label errorLabel = new Label();
	final VerticalPanel vertPan = new VerticalPanel();
	//final HorizontalPanel horizPan = new HorizontalPanel();
	
	final HorizontalPanel inputArea = new HorizontalPanel();
	
	final HorizontalPanel mainArea = new HorizontalPanel();
	final HorizontalPanel calcMainInputArea = new HorizontalPanel();
	final HorizontalPanel calcOutputArea = new HorizontalPanel();
	
	final Label lblNumPanels = new Label();
	final Label lblPanelSelect = new Label(); 
	final Label lblInverterSelect = new Label(); 
	final Label lblDayTimeUsage = new Label(); 
	final Label lblEfficiencyLoss = new Label();
	final Label lblPostcode = new Label();
	final Label lblSuburb = new Label();
	final Label lblEnergyProvider = new Label();
	final Label lblInitInstalCost = new Label();
	final Label lblInterestRate = new Label();
	
	final FlexTable generatedTable = new FlexTable();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		/*numPanels.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Window.alert("Something");
			}
		});*/

		class UpdatingHandler implements ChangeHandler {
			private void asyncOutput() {
				Window.alert("Holy crap it works!");
			}
			@Override
			public void onChange(ChangeEvent event) {
				asyncOutput();
			}
		}

		UpdatingHandler hadle = new UpdatingHandler();

		// Number of Panel Selection
		numPanels.addChangeHandler(hadle);
		vertPan.add(lblNumPanels);
		vertPan.add(numPanels);
		lblNumPanels.setText("Insert Number Panels");
		
		//Panel Selection
		panelSelect.addChangeHandler(hadle);
		panelSelect.addItem("REC230PE");
		panelSelect.addItem("Second Pan");
		panelSelect.setVisibleItemCount(2);
		lblPanelSelect.setText("Panel Select");
		vertPan.add(lblPanelSelect);
		vertPan.add(panelSelect);
		
		//Inverter Selection
		inverterSelect.addChangeHandler(hadle);
		vertPan.add(lblInverterSelect);
		vertPan.add(inverterSelect);
		inverterSelect.addItem("SB1600TL");
		inverterSelect.addItem("second Invert");
		lblInverterSelect.setText("Inverter Select");
		
		//Day Time Usage
		daytimeUsage.addChangeHandler(hadle);
		vertPan.add(lblDayTimeUsage);
		vertPan.add(daytimeUsage);
		lblDayTimeUsage.setText("Day time Electricity usage: (kW)");
		
		//Efficiency Loss
		efficiencyLoss.addChangeHandler(hadle);
		vertPan.add(lblEfficiencyLoss);
		vertPan.add(efficiencyLoss);
		lblEfficiencyLoss.setText("Efficiency Loss");
		
		//Postcode
		postcode.addChangeHandler(hadle);
		vertPan.add(lblPostcode);
		vertPan.add(postcode);
		lblPostcode.setText("Postcode");
		
		//Suburb
		suburb.addChangeHandler(hadle);
		vertPan.add(lblSuburb);
		vertPan.add(suburb);
		lblSuburb.setText("Suburb");
		
		//Energy Provider
		energyProvider.addChangeHandler(hadle);
		vertPan.add(lblEnergyProvider);
		vertPan.add(energyProvider);
		energyProvider.addItem("Origin");
		energyProvider.addItem("Second Prov");
		lblEnergyProvider.setText("Energy Provider");
		
		//Initial Installation Cost
		initInstalCost.addChangeHandler(hadle);
		vertPan.add(lblInitInstalCost);
		vertPan.add(initInstalCost);
		lblInitInstalCost.setText("Initial Install Cost");
		
		//Interest Rate 
		interestRate.addChangeHandler(hadle);
		vertPan.add(lblInterestRate);
		vertPan.add(interestRate);
		lblInterestRate.setText("Interest Rate");
		
		vertPan.add(sendButton);
		vertPan.add(errorLabel);

		inputArea.add(vertPan);
		//inputArea.add(horizPan);
		
		calcMainInputArea.add(inputArea);
		
		mainArea.add(calcMainInputArea);
		mainArea.add(calcOutputArea);
		
		// Add the Panel to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("CalInputs").add(mainArea);

		calcOutputArea.setVisible(false);
		
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
				calcOutputArea.setVisible(true);
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
				ArrayList <String> stuffToServer = new ArrayList<String>(); 
				stuffToServer.add(numPanels.getText());
				stuffToServer.add(panelSelect.getItemText(panelSelect.getSelectedIndex()));
				stuffToServer.add(inverterSelect.getItemText(inverterSelect.getSelectedIndex()));
				stuffToServer.add(daytimeUsage.getText());
				stuffToServer.add(efficiencyLoss.getText());
				stuffToServer.add(postcode.getText());
				stuffToServer.add(suburb.getText());
				stuffToServer.add(energyProvider.getItemText(energyProvider.getSelectedIndex()));
				stuffToServer.add(initInstalCost.getText());
				stuffToServer.add(interestRate.getText());
				
				String asdf = new String();
				/*if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}*/

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				serverResponseLabel.setText("");
				solarPowerService.SolarPowerServer(stuffToServer,
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
								generatingOutput();
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		// nameField.addKeyUpHandler(handler);
	}
	
	private void generatingOutput() {
		calcOutputArea.add(generatedTable);
		
		generatedTable.setCellPadding(6);
		generatedTable.getColumnFormatter().addStyleName(0, "generListHeader");
		generatedTable.addStyleName("generList");
		generatedTable.getCellFormatter().addStyleName(0, 1, "generListNumericColumn");
		//generatedTable.getCellFormatter().addStyleName(0, 2, "generListNumericColumn");
		
		//Create table row names for data
		generatedTable.setText(0, 0, "Year");
		generatedTable.setText(1, 0, "Daily Generation");
		generatedTable.setText(2, 0, "Yearly Generation");
		generatedTable.setText(3, 0, "Yearly Savings");
		generatedTable.setText(4, 0, "Investment Return");
		
		generatedTable.setText(0, 1, "1");
		generatedTable.setText(1, 1, "12.1");
		generatedTable.setText(2, 1, "123");
		generatedTable.setText(3, 1, "321");
		generatedTable.setText(4, 1, "6");
		
		generatedTable.setText(0, 2, "1");
		generatedTable.setText(1, 2, "12.1");
		generatedTable.setText(2, 2, "123");
		generatedTable.setText(3, 2, "321");
		generatedTable.setText(4, 2, "6");
		
		generatedTable.getCellFormatter().addStyleName(0, 1, "generListNumericColumn");
		generatedTable.getCellFormatter().addStyleName(1, 2, "generListNumericColumn");
		    
	}
	
}
