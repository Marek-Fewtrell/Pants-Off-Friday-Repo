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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
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
	 * Create a remote service proxy to talk to the server-side services.
	 */
	@SuppressWarnings("unused")
	private final SolarPowerServiceAsync solarPowerService = GWT
			.create(SolarPowerService.class);
	private final CalculationsServiceAsync calcService = GWT
			.create(CalculationsService.class);
	private final SetupServiceAsync setupService = GWT
			.create(SetupService.class);

	final Button sendButton = new Button("Send");
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
	final TextBox tiltAngle = new TextBox();
	final TextBox panDirection = new TextBox();
	final TextBox latitude = new TextBox();

	final Label errorLabel = new Label();
	final VerticalPanel vertPan = new VerticalPanel();
	final VerticalPanel vertPan2 = new VerticalPanel();

	final FlowPanel flowPan = new FlowPanel();
	final FlowPanel flowPan2 = new FlowPanel();
	
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
	final Label lbltiltAngle = new Label();
	final Label lblpanDirection = new Label();
	final Label lbllatitude = new Label();

	final ScrollPanel outputstuf = new ScrollPanel();
	final FlexTable generatedTable = new FlexTable();

	final Button autoFill = new Button("AutoFill");

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// We can add style names to widgets
		//sendButton.addStyleName("sendButton");

		class UpdatingHandler implements ChangeHandler {
			@SuppressWarnings("unused")
			private void asyncOutput() {
				// Window.alert("Holy crap it works!");
			}

			@Override
			public void onChange(ChangeEvent event) {
				// asyncOutput();
			}
		}

		UpdatingHandler hadle = new UpdatingHandler();

		// Number of Panel Selection
		numPanels.addChangeHandler(hadle);
		vertPan.add(lblNumPanels);
		vertPan.add(numPanels);
		lblNumPanels.setText("Number of Panel: ");

		// Panel Selection
		panelSelect.addChangeHandler(hadle);
		lblPanelSelect.setText("Select Panel: ");
		vertPan.add(lblPanelSelect);
		vertPan.add(panelSelect);

		// Inverter Selection
		inverterSelect.addChangeHandler(hadle);
		vertPan.add(lblInverterSelect);
		vertPan.add(inverterSelect);
		lblInverterSelect.setText("Select Inverter: ");

		// Energy Provider
		energyProvider.addChangeHandler(hadle);
		vertPan.add(lblEnergyProvider);
		vertPan.add(energyProvider);
		lblEnergyProvider.setText("Energy Provider: ");
				
		// Day Time Usage
		daytimeUsage.addChangeHandler(hadle);
		vertPan.add(lblDayTimeUsage);
		vertPan.add(daytimeUsage);
		lblDayTimeUsage.setText("Day time Electricity usage (kW): ");

		// Tilt Angle
		tiltAngle.addChangeHandler(hadle);
		vertPan2.add(lbltiltAngle);
		vertPan2.add(tiltAngle);
		lbltiltAngle.setText("Tilt Angle: ");

		// Direction
		panDirection.addChangeHandler(hadle);
		vertPan2.add(lblpanDirection);
		vertPan2.add(panDirection);
		lblpanDirection.setText("Facing Panel Direction (degrees): ");

		// Latitude
		latitude.addChangeHandler(hadle);
		vertPan2.add(lbllatitude);
		vertPan2.add(latitude);
		lbllatitude.setText("Latitude (degrees): ");

		// Postcode
		postcode.addChangeHandler(hadle);
		vertPan2.add(lblPostcode);
		vertPan2.add(postcode);
		lblPostcode.setText("Postcode: ");

		// Suburb
		suburb.addChangeHandler(hadle);
		vertPan2.add(lblSuburb);
		vertPan2.add(suburb);
		lblSuburb.setText("Suburb: ");
		
		// Initial Installation Cost
		initInstalCost.addChangeHandler(hadle);
		vertPan2.add(lblInitInstalCost);
		vertPan2.add(initInstalCost);
		lblInitInstalCost.setText("Initial Install Cost: ");

		// Interest Rate
		interestRate.addChangeHandler(hadle);
		vertPan2.add(lblInterestRate);
		vertPan2.add(interestRate);
		lblInterestRate.setText("Interest Rate: ");
		

		vertPan.add(sendButton);
		vertPan.add(errorLabel);
		vertPan.add(autoFill);
		autoFill.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("AutoFilled");
				numPanels.setText("5");
				daytimeUsage.setText("123");
				efficiencyLoss.setText("23");
				postcode.setText("4053");
				suburb.setText("STAFFORD");
				initInstalCost.setText("300");
				interestRate.setText("2.4");
				tiltAngle.setText("30");
				panDirection.setText("13");
				latitude.setText("24");
			}
		});
		//vertPan.add(errorLabel);

		inputArea.add(vertPan);
		inputArea.add(vertPan2);
		
		calcMainInputArea.add(inputArea);
		flowPan.setWidth("2em");
		flowPan.add(calcMainInputArea);
		flowPan.add(calcOutputArea);
		
		mainArea.add(flowPan);

		// **Start output table**
		outputstuf.add(generatedTable);
		outputstuf.setAlwaysShowScrollBars(true);
		outputstuf.setWidth("100%");
		calcOutputArea.add(outputstuf);

		generatedTable.setCellPadding(6);
		generatedTable.getColumnFormatter().addStyleName(0, "generListHeader");
		generatedTable.addStyleName("generList");
		generatedTable.getCellFormatter().addStyleName(0, 1, "generListNumericColumn");

		// Create table row names for data
		generatedTable.setText(0, 0, "Year");
		generatedTable.setText(1, 0, "Daily Generation");
		generatedTable.setText(2, 0, "Yearly Generation");
		generatedTable.setText(3, 0, "Yearly Savings");
		generatedTable.setText(4, 0, "Investment Return");
		// **End output table**

		// Add the Panel to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("CalInputs").add(mainArea);

		calcOutputArea.setVisible(false);

		// Focus the cursor on the first field when the app loads
		numPanels.setFocus(true);
		numPanels.selectAll();

		createLists();

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
				
				
//				 if (!FieldVerifier.isValidName(textToServer)) {
//				 errorLabel.setText("Please enter at least four characters");
//				 return; }
				 
				 // panelSelect 
				 //if (FieldVerifier.isNull())
				 //numPanels is not null or not letters
				
				//suburb is not null or numbers
				
				//inverter select is not null
				
				//energy provider is not null
				
				//daytimeUsage is not null or letters
				
				//tiltAngle is not null or letters
				
				//panDirection is not null or letters
				
				//initInstalCost is not null or letters
				
				//interestRate is not null or letters
				
				errorLabel.setText("");
				ArrayList<String> stuffToServer = new ArrayList<String>();
				stuffToServer.add(panelSelect.getItemText(panelSelect.getSelectedIndex()));
				stuffToServer.add(numPanels.getText());
				stuffToServer.add(suburb.getText());
				// stuffToServer.add(postcode.getText());
				stuffToServer.add(inverterSelect.getItemText(inverterSelect.getSelectedIndex()));
				stuffToServer.add(energyProvider.getItemText(energyProvider.getSelectedIndex()));
				stuffToServer.add(daytimeUsage.getText());
				// stuffToServer.add(latitude.getText());
				stuffToServer.add(tiltAngle.getText());
				stuffToServer.add(panDirection.getText());
				stuffToServer.add(initInstalCost.getText());
				stuffToServer.add(interestRate.getText());

				/*Iterator<String> iterate = stuffToServer.iterator();
				while (iterate.hasNext()){
					FieldVerifier.isNull(iterate.toString());
				}*/
				
				boolean toSend = true;
				
				
				for (String s : stuffToServer) {
					if (FieldVerifier.isNull(s) || FieldVerifier.isEmpty(s.toString())) {
						errorLabel.setText("Error, something is wrong");
						errorLabel.setVisible(true);
						toSend = false;
						return;
					}
				}
				
				if (!FieldVerifier.containsNum(numPanels.getText())) {
					errorLabel.setText("fuck7");
					errorLabel.setVisible(true);
					Window.alert(numPanels.getText().toString());
					toSend = false;
				}

				if (!FieldVerifier.containsLetters(suburb.getText())) {
					errorLabel.setText("fuck6");
					errorLabel.setVisible(true);
					toSend = false;
				}

				if (!FieldVerifier.containsNum(daytimeUsage.getText())) {
					errorLabel.setText("fuck5");
					errorLabel.setVisible(true);
					toSend = false;
				}

				if (!FieldVerifier.containsNum(tiltAngle.getText())) {
					errorLabel.setText("fuck4");
					errorLabel.setVisible(true);
					toSend = false;
				}

				if (!FieldVerifier.containsNum(panDirection.getText())) {
					errorLabel.setText("fuck3");
					errorLabel.setVisible(true);
					toSend = false;
				}

				if (!FieldVerifier.containsNum(initInstalCost.getText())) {
					errorLabel.setText("fuck2");
					errorLabel.setVisible(true);
					toSend = false;
				}

				if (!FieldVerifier.containsNum(interestRate.getText())) {
					errorLabel.setText("fuck1");
					errorLabel.setVisible(true);
					toSend = false;
				}
				
				
				if (toSend == true){
				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				serverResponseLabel.setText("");

				/*
				 * solarPowerService.SolarPowerServer(stuffToServer, new
				 * AsyncCallback<String>() { public void onFailure(Throwable
				 * caught) { // Show the RPC error message to the user dialogBox
				 * .setText("Connection with Server - Failure");
				 * serverResponseLabel
				 * .addStyleName("serverResponseLabelError");
				 * serverResponseLabel.setHTML(SERVER_ERROR);
				 * dialogBox.center(); closeButton.setFocus(true); }
				 * 
				 * public void onSuccess(String result) { dialogBox
				 * .setText("Connection with Server - Success");
				 * serverResponseLabel
				 * .removeStyleName("serverResponseLabelError");
				 * serverResponseLabel.setHTML(result); dialogBox.center();
				 * closeButton.setFocus(true); //generatingOutput(); } });
				 */

				calcService.CalculationsServer(stuffToServer,
						new AsyncCallback<ArrayList<ArrayList<String>>>() {
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

							public void onSuccess(ArrayList<ArrayList<String>> result) {
								dialogBox
										.setText("Connection with Server - Success");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								// serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
								calcOutputArea.setVisible(true);
								generatingOutput(result);
								
							}

						});

			}}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		// nameField.addKeyUpHandler(handler);
	}

	private void createLists() {
		setupService
				.SetupServer(new AsyncCallback<ArrayList<ArrayList<String>>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Failed");
					}

					@Override
					public void onSuccess(ArrayList<ArrayList<String>> result) {
						ArrayList<String> array0 = result.get(0);
						ArrayList<String> array1 = result.get(1);
						ArrayList<String> array2 = result.get(2);

						for (int i = 0; i < array0.size(); i++) {
							panelSelect.addItem(array0.get(i));
						}

						for (int i = 0; i < array1.size(); i++) {
							inverterSelect.addItem(array1.get(i));
						}

						for (int i = 0; i < array2.size(); i++) {
							energyProvider.addItem(array2.get(i));
						}

					}

				});
	}

	private void generatingOutput(ArrayList<ArrayList<String>> result) {

		ArrayList<String> array0 = result.get(0);
		ArrayList<String> array1 = result.get(1);
		ArrayList<String> array2 = result.get(2);
		ArrayList<String> array3 = result.get(3);
		ArrayList<String> array4 = result.get(4);
		
		// Year
		for (int i = 0; i < array0.size(); i++) {
			generatedTable.setText(0, i + 1, array0.get(i).toString());
		}

		// Daily
		for (int i = 0; i < array1.size(); i++) {
			generatedTable.setText(1, i + 1, array1.get(i).toString());
		}

		// Yearly
		for (int i = 0; i < array2.size(); i++) {
			generatedTable.setText(2, i + 1, array2.get(i).toString());
		}

		// Yearly
		for (int i = 0; i < array3.size(); i++) {
			generatedTable.setText(3, i + 1, array3.get(i).toString());
		}

		// Invest
		for (int i = 0; i < array4.size(); i++) {
			generatedTable.setText(4, i + 1, array4.get(i).toString());
		}

	}

}
