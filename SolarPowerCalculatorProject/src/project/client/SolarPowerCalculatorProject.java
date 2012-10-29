package project.client;

import java.util.ArrayList;
import java.util.HashMap;

import project.shared.CalcException;
import project.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.cellview.client.CellBrowser;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.TreeViewModel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.corechart.LineChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;

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

	//private static final String SERVER_SUCCESS = "Request processed succesfully. Results are now displayed.";

	/**
	 * Create a remote service proxy to talk to the server-side services.
	 */

	
	
	// This creates the remote async service proxy for communication with the
	// server-side calculations service.
	private final CalculationsServiceAsync calcService = GWT
			.create(CalculationsService.class);
	// This creates the remote async service proxy for the server-side page
	// set-up service.
	private final SetupServiceAsync setupService = GWT
			.create(SetupService.class);

	// Inputs for the form.
	final Button sendButton = new Button("Send");
	final TextBox numPanels = new TextBox();
	final ListBox panelSelect = new ListBox();
	final ListBox inverterSelect = new ListBox();
	final TextBox daytimeUsage = new TextBox();
	
	final TextBox tarrif11Usage = new TextBox();
	final TextBox billingPeriod = new TextBox();

	final Label choice = new Label("Click to specify");
	final RadioButton radioDayTimeUsage = new RadioButton("myRadioGroup", "Day time usage.");
	final RadioButton radioTarrifBilling = new RadioButton("myRadioGroup", "Tarrif 11 usage and billing period.");
	
	final TextBox postcode = new TextBox();
	final ListBox energyProvider = new ListBox();
	final TextBox initInstalCost = new TextBox();
	final TextBox interestRate = new TextBox();
	final TextBox tiltAngle = new TextBox();
	final TextBox panDirection = new TextBox();

	final Label errorLabel = new Label();

	// Contains Inverter selection cell browser
	final VerticalPanel inverterBrowserPan = new VerticalPanel();
	// Contains Panel selection cell browser
	final VerticalPanel panelBrowserPan = new VerticalPanel();

	// Contains left inputs
	final VerticalPanel vertPan = new VerticalPanel();
	// Contains right inputs
	final VerticalPanel vertPan2 = new VerticalPanel();
	// Contains vertPan and vertPan2.
	final HorizontalPanel inputArea = new HorizontalPanel();
	// Contains input area.
	final HorizontalPanel calcMainInputArea = new HorizontalPanel();
	// Contains the outputs including generatedTable.
	final VerticalPanel calcOutputArea = new VerticalPanel();
	// Contains calcMainInputArea and calcOutputArea
	final VerticalPanel mainArea = new VerticalPanel();

	CellBrowser panelBrowser;
	CellBrowser inverterBrowser;
	
	// Labels for the inputs
	final Label lblNumPanels = new Label();
	final Label lblPanelSelect = new Label();
	final Label lblInverterSelect = new Label();
	final Label lblDayTimeUsage = new Label();
	
	final Label lblTarrif11Usage = new Label();
	final Label lblbillingPeriod = new Label();
	
	final Label lblPostcode = new Label();
	final Label lblEnergyProvider = new Label();
	final Label lblInitInstalCost = new Label();
	final Label lblInterestRate = new Label();
	final Label lbltiltAngle = new Label();
	final Label lblpanDirection = new Label();
	final Label lblbreakeven = new Label();
	
	final Label lblsendButtonClicked = new Label("Change value to resend");

	// Scroll Panels and the flextable which is incased and holds the generated
	// output info.
	final ScrollPanel outputstuf = new ScrollPanel();
	final FlexTable generatedTable = new FlexTable();
	static LineChart lineChart = null;
	final VerticalPanel outputGraph = new VerticalPanel();

	final Button autoFill = new Button("Sample Data");
	final Label lblAutoFill = new Label();
	
	final static HTML helpText = new HTML(
			"Number of Panels: " + "Enter the total number of Solar Panels that are"
	                + " going to be installed on the roof of your home. </br></br>"+ 
			"Selecting a panel: " +"Select the Brand of your Panels and Select the corresponding Model of your Panels. </br></br>" +
			"Selecting an inverter: " + "Select the Brand of your Inverter and Select the corresponding Model of your Inverter. </br></br>" +
			"Selecting an energy provider: "+" Select your current Energy Provider from the"
            + " list of available Companies. </br></br>" +
			"selecting either day time usage or tarrif 11 usage and billing period: </br>" + "Enter your current average daily energy consumption"
            + " in whole kiloWatts. OR Enter your tarrif 11 usage and the period of usage as found on your recent energy bill.</br></br>" +
			"tilt angle: " + "Enter the known tilt angle of the panels"
            + " on the roof, in whole degrees.</br></br>" +
			"Roof direction: " + "Enter the known orientation of your roof"
            + " in whole degrees deviation from Solar North. This value can"
            + " be positive or negative. </br></br>" +
			"postcode: " + "Enter the postcode you live in.</br>" +
			"initial install cost: " + "Enter the initial cost of the Solar Panel"
            + " installation in whole dollars.</br></br>"+
			"interest rate: </br>");
	
//	DisclosurePanel disclaimerPan = new DisclosurePanel("Click for !!DISCLAIMER!!");
//	Label lblDisclosure = new Label("This calculator is not 100% accurate all the time. But it is damn near close.");

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {// TODO split up into methods and what not
		
		final Image helpImage = new Image("help_question_mark.gif");
		helpImage.setAltText("Help Image");
		
		final DialogBox dialogBoxHelp = new DialogBox();
		dialogBoxHelp.setWidth("500px");
		dialogBoxHelp.setText("Help Request");
		dialogBoxHelp.setAnimationEnabled(true);
		final Button closeButtonHelp = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButtonHelp.getElement().setId("closeButtonHelp");
		final Label textHelpLabel = new Label("");
		//final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanelHelp = new VerticalPanel();
		dialogVPanelHelp.addStyleName("dialogVPanel");
		dialogVPanelHelp.add(new HTML("<b>Help Dialog</b>"));
		dialogVPanelHelp.add(helpText);
		//dialogVPanelHelp.add(new HTML("<br>Request Status</b>"));
		dialogVPanelHelp.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanelHelp.add(closeButtonHelp);
		dialogBoxHelp.setWidget(dialogVPanelHelp);

		// Add a handler to close the DialogBox
		closeButtonHelp.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBoxHelp.hide();
//				sendButton.setEnabled(true);
//				sendButton.setFocus(true);
			}
		});
		
		// Create a handler for the help image
		
		helpImage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBoxHelp.center();
				//dialogBoxHelp.show();
			}
		});
		
		class UpdatingHandler implements ChangeHandler {
			@Override
			public void onChange(ChangeEvent event) {
				sendButton.setEnabled(true);
			}
		}

		UpdatingHandler hadle = new UpdatingHandler();
		
		
		// Number of Panel Selection
		numPanels.addChangeHandler(hadle);
		vertPan.add(lblNumPanels);
		vertPan.add(numPanels);
		lblNumPanels.setText("Number of Panels: ");

		// Panel Selection
		panelSelect.addChangeHandler(hadle);
		lblPanelSelect.setText("Select Panel: ");
		vertPan.add(lblPanelSelect);
		// vertPan.add(panelSelect);
		vertPan.add(panelBrowserPan);

		// Inverter Selection
		inverterSelect.addChangeHandler(hadle);
		lblInverterSelect.setText("Select Inverter: ");
		vertPan.add(lblInverterSelect);
		// vertPan.add(inverterSelect);
		vertPan.add(inverterBrowserPan);

		// Energy Provider
		energyProvider.addChangeHandler(hadle);
		vertPan.add(lblEnergyProvider);
		vertPan.add(energyProvider);
		lblEnergyProvider.setText("Select Energy Provider: ");

		radioDayTimeUsage.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (radioDayTimeUsage.getValue() == true) {
					lblDayTimeUsage.setVisible(true);
					daytimeUsage.setVisible(true);
					tarrif11Usage.setVisible(false);
					lblbillingPeriod.setVisible(false);
					billingPeriod.setVisible(false);
					lblTarrif11Usage.setVisible(false);
				}
			}
		});
		vertPan.add(radioDayTimeUsage);
		// Day Time Usage
		vertPan.add(lblDayTimeUsage);
		vertPan.add(daytimeUsage);
		lblDayTimeUsage.setText("Day time Electricity usage (kW): ");
		lblDayTimeUsage.setVisible(false);
		daytimeUsage.setVisible(false);
		daytimeUsage.addChangeHandler(hadle);
		
		radioTarrifBilling.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (radioTarrifBilling.getValue() == true) {
					lblTarrif11Usage.setVisible(true);
					tarrif11Usage.setVisible(true);
					lblbillingPeriod.setVisible(true);
					billingPeriod.setVisible(true);
					daytimeUsage.setVisible(false);
					lblDayTimeUsage.setVisible(false);
				}
			}
		});
		vertPan.add(radioTarrifBilling);
		//Tarrif 11 usage
		vertPan.add(lblTarrif11Usage);
		vertPan.add(tarrif11Usage);
		lblTarrif11Usage.setText("Tarrif 11 usage (kW/h): ");
		lblTarrif11Usage.setVisible(false);
		tarrif11Usage.setVisible(false);
		tarrif11Usage.addChangeHandler(hadle);
		
		//Billing Period
		vertPan.add(lblbillingPeriod);
		vertPan.add(billingPeriod);
		lblbillingPeriod.setText("Billing period (days): ");
		lblbillingPeriod.setVisible(false);
		billingPeriod.setVisible(false);
		billingPeriod.addChangeHandler(hadle);
		
		// Tilt Angle
		tiltAngle.addChangeHandler(hadle);
		vertPan2.add(lbltiltAngle);
		vertPan2.add(tiltAngle);
		lbltiltAngle.setText("Tilt Angle: ");

		// Direction
		panDirection.addChangeHandler(hadle);
		vertPan2.add(lblpanDirection);
		vertPan2.add(panDirection);
		lblpanDirection
				.setText("Roof direction (deviation from Solar North)(degrees): ");

		// Postcode
		postcode.addChangeHandler(hadle);
		vertPan2.add(lblPostcode);
		vertPan2.add(postcode);
		lblPostcode.setText("Postcode: ");

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

		// Sample data stuff
		lblAutoFill.setText("Insert a selection of sample data");
		vertPan2.add(lblAutoFill);
		vertPan2.add(autoFill);

		// Send button
		vertPan2.add(errorLabel);
		vertPan2.add(helpImage);
		vertPan2.add(sendButton);
		
		vertPan2.add(lblsendButtonClicked);
		lblsendButtonClicked.setVisible(false);

		autoFill.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				numPanels.setText("8");
				daytimeUsage.setText("10");
				radioDayTimeUsage.setValue(true, true);
				postcode.setText("4053");
				initInstalCost.setText("3200");
				interestRate.setText("4.6");
				tiltAngle.setText("20");
				panDirection.setText("6");
				CellBrowserCreationPanel.CustomTreeModel.setSelectedItem(
						"Rec250peBLK", true);
				CellBrowserCreationInverter.CustomTreeModel.setSelectedItem(
						"SB3000", true);
			}
		});

		inputArea.add(vertPan);
		inputArea.add(vertPan2);

		calcMainInputArea.add(inputArea);
		
//		disclaimerPan.add(lblDisclosure);
//		mainArea.add(disclaimerPan);
		mainArea.add(calcMainInputArea);
		mainArea.add(calcOutputArea);

		calcOutputArea.add(lblbreakeven);
//		calcOutputArea.add(lineChart);

		// **Start output table**
		outputstuf.add(generatedTable);
		outputstuf.setAlwaysShowScrollBars(true);
		outputstuf.setWidth("45%");
		calcOutputArea.add(outputstuf);

		generatedTable.setCellPadding(6);
		generatedTable.getColumnFormatter().addStyleName(0, "generListHeader");
		generatedTable.addStyleName("generList");
		generatedTable.getRowFormatter().addStyleName(0,
				"generListNumericColumn");
		generatedTable.getRowFormatter().addStyleName(1,
				"generListNumericColumn");
		generatedTable.getRowFormatter().addStyleName(2,
				"generListNumericColumn");
		generatedTable.getRowFormatter().addStyleName(3,
				"generListNumericColumn");
		generatedTable.getRowFormatter().addStyleName(4,
				"generListNumericColumn");

		// Create table row names for data
		generatedTable.setText(0, 0, "Year");
		generatedTable.setText(1, 0, "Daily Generation");
		generatedTable.setText(2, 0, "Yearly Generation");
		generatedTable.setText(3, 0, "Yearly Savings");
		generatedTable.setText(4, 0, "Investment Return");
		// **End output table**
		
		calcOutputArea.add(outputGraph);
		outputGraph.setHeight("500");
		

		// Add the Panel to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("CalInputs").add(mainArea);

		// Hides the output area until needed.
		calcOutputArea.setVisible(false);

		// Focus the cursor on the first field when the app loads
		numPanels.setFocus(true);
		numPanels.selectAll();

		createLists();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Processing Request");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending request to server....</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Request Status</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
//				sendButton.setEnabled(true);
//				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton
		class MyHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				try {
					sendNameToServer();
				} catch (CalcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 * 
			 * @throws CalcException
			 */
			private void sendNameToServer() throws CalcException {

				errorLabel.setText("");

				boolean toSend = true;

				/*
				 * if (FieldVerifier.isNull(s) ||
				 * FieldVerifier.isEmpty(s.toString())) {
				 * errorLabel.setText("Error, a field is empty"); toSend =
				 * false; return; }
				 */

				// TODO improve validation and add exceptions and exception
				// handling.

				if (!FieldVerifier.containsNum(interestRate.getText())
						|| FieldVerifier.isEmpty(interestRate.getText())) {
					errorLabel.setText("No letter in the interest rate field.");
					toSend = false;
					errorLabel.setVisible(true);
				}

				if (!FieldVerifier.containsNum(initInstalCost.getText())
						|| FieldVerifier.isEmpty(initInstalCost.getText())) {
					errorLabel
							.setText("No letter in the initial install cost field.");
					toSend = false;
					errorLabel.setVisible(true);
				}

				if (!FieldVerifier.containsNum(postcode.getText())
						|| FieldVerifier.isEmpty(postcode.getText())) {
					errorLabel.setText("No letters in the postcode field.");
					toSend = false;
					errorLabel.setVisible(true);
				}

				if (!FieldVerifier.containsNum(panDirection.getText())
						|| FieldVerifier.isEmpty(panDirection.getText())) {
					errorLabel.setText("No letter in the Roof Dirction field.");
					toSend = false;
					errorLabel.setVisible(true);
				}

				if (!FieldVerifier.containsNum(tiltAngle.getText())
						|| FieldVerifier.isEmpty(tiltAngle.getText())) {
					errorLabel
							.setText("No letter in the roof tilt angle field.");
					toSend = false;
					errorLabel.setVisible(true);
				}

				/*if (!FieldVerifier.containsNum(daytimeUsage.getText())
						|| FieldVerifier.isEmpty(daytimeUsage.getText())) {
					errorLabel
							.setText("No letter in the Day time usage field.");
					toSend = false;
					errorLabel.setVisible(true);
				}*/

				if (!FieldVerifier.containsNum(numPanels.getText())
						|| FieldVerifier.isEmpty(numPanels.getText())) {
					errorLabel
							.setText("No letters in the the Number of Panels field.");
					errorLabel.setVisible(true);
					toSend = false;
				}

				
				
				if (toSend == true) {
					boolean use1Calc = true;
					if (daytimeUsage.getText().isEmpty()) {
						daytimeUsage.setText("-1");
						use1Calc = false;
					} else if (daytimeUsage.getText() == "-1" || daytimeUsage.getText().isEmpty()) {
						use1Calc = false;
					} else if (!daytimeUsage.getText().isEmpty()) {
						tarrif11Usage.setText("-1");
						billingPeriod.setText("-1");
						
					}
					
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("panelSelect",
							CellBrowserCreationPanel.CustomTreeModel
									.getSelectedItem().toString());
					map.put("panelNumber", numPanels.getText());
					map.put("postcode", postcode.getText());
					map.put("inverterSelect",
							CellBrowserCreationInverter.CustomTreeModel
									.getSelectedItem().toString());
					map.put("energyProvider", energyProvider
							.getItemText(energyProvider.getSelectedIndex()));
					map.put("dayTimeUsage", daytimeUsage.getText());
					map.put("tiltAngle", tiltAngle.getText());
					map.put("panelDirection", panDirection.getText());
					map.put("initialInstallCost", initInstalCost.getText());
					map.put("interestRate", interestRate.getText());
					map.put("tarrif11usage", tarrif11Usage.getText());
					map.put("days", billingPeriod.getText());
					
					// Then, we send the input to the server.
					sendButton.setEnabled(false);
					lblsendButtonClicked.setVisible(true);
					serverResponseLabel.setText("");

					
					calcService.CalculationsServer(map, use1Calc,
							new AsyncCallback<ArrayList<ArrayList<String>>>() {
								// TODO improve descriptions
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

								public void onSuccess(
										ArrayList<ArrayList<String>> result) {
									calcOutputArea.setVisible(true);
									generatingOutput(result);

								}

							});

				}
			}
		}

		// Add a handler to send the options to the server
		sendButton.addClickHandler(new MyHandler());


	} // End onModuleLoad()

	/**
	 * This creates the lists for Panel, Inverter and Energy Providers
	 * selection.
	 */
	private void createLists() {
		setupService
				.SetupServer(new AsyncCallback<ArrayList<ArrayList<String>>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Sorry, an error occured while loading. Please trying refreashing.");
					}

					@Override
					public void onSuccess(ArrayList<ArrayList<String>> result) {
						ArrayList<String> array0 = result.get(0);// Panel
						ArrayList<String> array1 = result.get(1);// Inverter
						ArrayList<String> array2 = result.get(2);// Energy Provider

						/*
						 * Panel creation
						 */
						// Create a model for the browser.
						TreeViewModel panelModel = new CellBrowserCreationPanel.CustomTreeModel(
								array0, "Select Panel");

						/*
						 * Create the browser using the model. We use
						 * <code>null</code> as the default value of the root
						 * node. The default value will be passed to
						 * CustomTreeModel#getNodeInfo();
						 */
						panelBrowser = new CellBrowser(panelModel,
								null);
						panelBrowser
								.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
						// RootLayoutPanel.get().add(panelBrowser);
						panelBrowserPan.add(panelBrowser);
						panelBrowser.setSize("450px", "200px");
						panelBrowser.setMinimumColumnWidth(150);
						panelBrowser.setDefaultColumnWidth(150);
						
						//panelBrowser.getRootTreeNode().setChildOpen(0, true);

						/*
						 * Inverter creation
						 */
						// Create a model for the browser.
						TreeViewModel inverterModel = new CellBrowserCreationInverter.CustomTreeModel(
								array1, "Select Inverter");

						/*
						 * Create the browser using the model. We use
						 * <code>null</code> as the default value of the root
						 * node. The default value will be passed to
						 * CustomTreeModel#getNodeInfo();
						 */

						inverterBrowser = new CellBrowser(
								inverterModel, null);
						inverterBrowser
								.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

						// Add the browser to the root layout panel.
						inverterBrowserPan.add(inverterBrowser);
						inverterBrowser.setSize("450px", "200px");
						inverterBrowser.setMinimumColumnWidth(150);
						inverterBrowser.setDefaultColumnWidth(150);

						for (int i = 0; i < array2.size(); i++) {
							energyProvider.addItem(array2.get(i));
						}

					}

				});
	}

	/**
	 * Generates the table filled with the output.
	 * 
	 * @param result
	 *            the output to be inserted into the table.
	 */
	private void generatingOutput(ArrayList<ArrayList<String>> result) {

		ArrayList<String> yearlyArray = result.get(0);
		ArrayList<String> dailyGenResultArray = result.get(1);
		ArrayList<String> yearlyGenResultArray = result.get(2);
		ArrayList<String> yearlySavingResultArray = result.get(3);
		ArrayList<String> investReturnResultArray = result.get(4);
		ArrayList<String> breakEvenArray = result.get(5);
		
		
		data = null;
		data = com.google.gwt.visualization.client.DataTable.create();

		data.addColumn(ColumnType.STRING, "Solar Panel Return");
		data.addColumn(ColumnType.NUMBER, "Investment Return");
		data.addColumn(ColumnType.NUMBER, "Yearly Savings");
		data.addRows(yearlyArray.size());
		
		// Year
		for (int i = 0; i < yearlyArray.size(); i++) {
			generatedTable.setText(0, i + 1, yearlyArray.get(i).toString());
			
			data.setValue(i, 0, Integer.toString(i+1));
			data.setValue(i, 1, Double.parseDouble(yearlySavingResultArray.get(i)));
			data.setValue(i, 2, Double.parseDouble(investReturnResultArray.get(i)));
		}

		// Daily
		for (int i = 0; i < dailyGenResultArray.size(); i++) {
			generatedTable.setText(1, i + 1, dailyGenResultArray.get(i).toString());
		}

		// Yearly
		for (int i = 0; i < yearlyGenResultArray.size(); i++) {
			generatedTable.setText(2, i + 1, yearlyGenResultArray.get(i).toString());
		}

		// Yearly
		for (int i = 0; i < yearlySavingResultArray.size(); i++) {
			generatedTable.setText(3, i + 1, yearlySavingResultArray.get(i).toString());
		}

		// Invest
		for (int i = 0; i < investReturnResultArray.size(); i++) {
			generatedTable.setText(4, i + 1, investReturnResultArray.get(i).toString());
		}

		if (Double.parseDouble(breakEvenArray.get(0)) <= 0) {
			lblbreakeven.setText("Break Even: Does not break even");
		} else {
			lblbreakeven.setText("Break Even: " + breakEvenArray.get(0).toString()
					+ " yrs");
		}
		createGraph();

	}

	public void createGraph() {
		// Create a callback to be called when the visualization API
		// has been loaded.
		Runnable onLoadCallback = new Runnable() {
			public void run() {

				// Create a pie chart visualization.
				lineChart = new LineChart(createTable(), createOptions());
				outputGraph.clear();
				outputGraph.add(lineChart);
			}
		};

		// Load the visualization api, passing the onLoadCallback to be called
		// when loading is done.
		VisualizationUtils.loadVisualizationApi(onLoadCallback,
				LineChart.PACKAGE);
	}

	private com.google.gwt.visualization.client.visualizations.corechart.Options createOptions() {
		Options options = Options.create();
		options.setWidth(700);
		options.setHeight(440);
		options.setTitle("Predicted investment returns");
		return options;
	}

	private SelectHandler createSelectHandler(final LineChart chart) {
		return new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				String message = "";

				// May be multiple selections.
				JsArray<Selection> selections = chart.getSelections();

				for (int i = 0; i < selections.length(); i++) {
					// add a new line for each selection
					message += i == 0 ? "" : "\n";

					Selection selection = selections.get(i);

					if (selection.isCell()) {
						// isCell() returns true if a cell has been selected.

						// getRow() returns the row number of the selected cell.
						int row = selection.getRow();
						// getColumn() returns the column number of the selected
						// cell.
						int column = selection.getColumn();
						message += "cell " + row + ":" + column + " selected";
					} else if (selection.isRow()) {
						// isRow() returns true if an entire row has been
						// selected.

						// getRow() returns the row number of the selected row.
						int row = selection.getRow();
						message += "row " + row + " selected";
					} else {
						// unreachable
						message += "Pie chart selections should be either row selections or cell selections.";
						message += "  Other visualizations support column selections as well.";
					}
				}

				Window.alert(message);
			}
		};
	}

	public static com.google.gwt.visualization.client.DataTable data;
	
	private AbstractDataTable createTable() {
		return data;
	}

}
