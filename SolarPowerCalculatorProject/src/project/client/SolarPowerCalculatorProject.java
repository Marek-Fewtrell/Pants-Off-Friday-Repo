package project.client;

import java.util.ArrayList;
import java.util.HashMap;

import project.shared.CalcException;
import project.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellBrowser;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.TreeViewModel;

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
	
	private static final String SERVER_SUCCESS = "Request processed succesfully. Results are now displayed.";

	/**
	 * Create a remote service proxy to talk to the server-side services.
	 */
	
	//private final SolarPowerServiceAsync solarPowerService = GWT.create(SolarPowerService.class);
	//This creates the remote async service proxy for communication with the server-side calculations service.
	private final CalculationsServiceAsync calcService = GWT
			.create(CalculationsService.class);
	//This creates the remote async service proxy for the server-side page set-up service.
	private final SetupServiceAsync setupService = GWT
			.create(SetupService.class);

	//Inputs for the form.
	final Button sendButton = new Button("Send");
	final TextBox numPanels = new TextBox();
	final ListBox panelSelect = new ListBox();
	final ListBox inverterSelect = new ListBox();
	final TextBox daytimeUsage = new TextBox();
	final TextBox postcode = new TextBox();
	final ListBox energyProvider = new ListBox();
	final TextBox initInstalCost = new TextBox();
	final TextBox interestRate = new TextBox();
	final TextBox tiltAngle = new TextBox();
	final TextBox panDirection = new TextBox();

	final Label errorLabel = new Label();
	
	//Contains Inverter selection cell browser
	final VerticalPanel inverterBrowserPan = new VerticalPanel();
	//Contains Panel selection cell browser
	final VerticalPanel panelBrowserPan = new VerticalPanel();
	
	//Contains left inputs
	final VerticalPanel vertPan = new VerticalPanel();
	//Contains right inputs
	final VerticalPanel vertPan2 = new VerticalPanel();
	//Contains vertPan and vertPan2.
	final HorizontalPanel inputArea = new HorizontalPanel();
	//Contains input area.
	final HorizontalPanel calcMainInputArea = new HorizontalPanel();
	//Contains the outputs including generatedTable.
	final VerticalPanel calcOutputArea = new VerticalPanel();
	//Contains calcMainInputArea and calcOutputArea
	final VerticalPanel mainArea = new VerticalPanel();
	
	// Labels for the inputs
	final Label lblNumPanels = new Label();
	final Label lblPanelSelect = new Label();
	final Label lblInverterSelect = new Label();
	final Label lblDayTimeUsage = new Label();
	final Label lblPostcode = new Label();
	final Label lblEnergyProvider = new Label();
	final Label lblInitInstalCost = new Label();
	final Label lblInterestRate = new Label();
	final Label lbltiltAngle = new Label();
	final Label lblpanDirection = new Label();
	final Label lblbreakeven = new Label();
	
	//Scroll Panels and the flextable which is incased and holds the generated output info.
	final ScrollPanel outputstuf = new ScrollPanel();
	final FlexTable generatedTable = new FlexTable();

	final Button autoFill = new Button("Sample Data");
	final Label lblAutoFill = new Label();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {//TODO split up into methods and what not

		class UpdatingHandler implements ChangeHandler {
			@SuppressWarnings("unused")
			private void asyncOutput() {
				
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
		lblNumPanels.setText("Number of Panels: ");

		// Panel Selection
		panelSelect.addChangeHandler(hadle);
		lblPanelSelect.setText("Select Panel: ");
		vertPan.add(lblPanelSelect);
		//vertPan.add(panelSelect);
		vertPan.add(panelBrowserPan);

		// Inverter Selection
		inverterSelect.addChangeHandler(hadle);
		lblInverterSelect.setText("Select Inverter: ");
		vertPan.add(lblInverterSelect);
		//vertPan.add(inverterSelect);
		vertPan.add(inverterBrowserPan);

		// Energy Provider
		energyProvider.addChangeHandler(hadle);
		vertPan.add(lblEnergyProvider);
		vertPan.add(energyProvider);
		lblEnergyProvider.setText("Select Energy Provider: ");
				
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
		lblpanDirection.setText("Roof direction (deviation from Solar North)(degrees): ");

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
		vertPan.add(lblAutoFill);
		vertPan.add(autoFill);
		
		vertPan.add(sendButton);
		vertPan.add(errorLabel);
		

		autoFill.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				numPanels.setText("8");
				daytimeUsage.setText("10");
				postcode.setText("4053");
				initInstalCost.setText("3200");
				interestRate.setText("4.6");
				tiltAngle.setText("20");
				panDirection.setText("6");
				CellBrowserCreationPanel.CustomTreeModel.setSelectedItem("Rec250peBLK", true);
				CellBrowserCreationInverter.CustomTreeModel.setSelectedItem("SB3000", true);
			}
		});

		inputArea.add(vertPan);
		inputArea.add(vertPan2);
		
		calcMainInputArea.add(inputArea);
		
		mainArea.add(calcMainInputArea);
		mainArea.add(calcOutputArea);
		
		calcOutputArea.add(lblbreakeven);

		// **Start output table**
		outputstuf.add(generatedTable);
		outputstuf.setAlwaysShowScrollBars(true);
		outputstuf.setWidth("40%");
		calcOutputArea.add(outputstuf);

		generatedTable.setCellPadding(6);
		generatedTable.getColumnFormatter().addStyleName(0, "generListHeader");
		generatedTable.addStyleName("generList");
		generatedTable.getRowFormatter().addStyleName(0, "generListNumericColumn");
		generatedTable.getRowFormatter().addStyleName(1, "generListNumericColumn");
		generatedTable.getRowFormatter().addStyleName(2, "generListNumericColumn");
		generatedTable.getRowFormatter().addStyleName(3, "generListNumericColumn");
		generatedTable.getRowFormatter().addStyleName(4, "generListNumericColumn");
		

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

		//Hides the output area until needed.
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
			 * @throws CalcException 
			 */
			private void sendNameToServer() throws CalcException {
				
				
				errorLabel.setText("");
				
				
				boolean toSend = true;
				
				
				
				
					/*if (FieldVerifier.isNull(s) || FieldVerifier.isEmpty(s.toString())) {
						errorLabel.setText("Error, a field is empty");
						toSend = false;
						return;
					}*/
				
				//TODO improve validation and add exceptions and exception handling.
				
				if (!FieldVerifier.containsNum(interestRate.getText()) || FieldVerifier.isEmpty(interestRate.getText())) {
					errorLabel.setText("No letter in the interest rate field.");
					toSend = false;
					errorLabel.setVisible(true);
				}
				
				if (!FieldVerifier.containsNum(initInstalCost.getText()) || FieldVerifier.isEmpty(initInstalCost.getText())) {
					errorLabel.setText("No letter in the initial install cost field.");
					toSend = false;
					errorLabel.setVisible(true);
				}
				
				if (!FieldVerifier.containsNum(postcode.getText()) || FieldVerifier.isEmpty(postcode.getText())) {
					errorLabel.setText("No letters in the postcode field.");
					toSend = false;
					errorLabel.setVisible(true);
				}
				
				if (!FieldVerifier.containsNum(panDirection.getText()) || FieldVerifier.isEmpty(panDirection.getText())) {
					errorLabel.setText("No letter in the Roof Dirction field.");
					toSend = false;
					errorLabel.setVisible(true);
				}
				
				if (!FieldVerifier.containsNum(tiltAngle.getText()) || FieldVerifier.isEmpty(tiltAngle.getText())) {
					errorLabel.setText("No letter in the roof tilt angle field.");
					toSend = false;
					errorLabel.setVisible(true);
				}
				
				if (!FieldVerifier.containsNum(daytimeUsage.getText()) || FieldVerifier.isEmpty(daytimeUsage.getText())) {
					errorLabel.setText("No letter in the Day time usage field.");
					toSend = false;
					errorLabel.setVisible(true);
				}
				
				if (!FieldVerifier.containsNum(numPanels.getText()) || FieldVerifier.isEmpty(numPanels.getText())) {
					errorLabel.setText("No letters in the the Number of Panels field.");
					errorLabel.setVisible(true);
					toSend = false;
				}
				
				
				if (toSend == true) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("panelSelect", CellBrowserCreationPanel.CustomTreeModel.getSelectedItem().toString());
					map.put("panelNumber", numPanels.getText());
					map.put("postcode", postcode.getText());
					map.put("inverterSelect", CellBrowserCreationInverter.CustomTreeModel.getSelectedItem().toString());
					map.put("energyProvider", energyProvider.getItemText(energyProvider.getSelectedIndex()));
					map.put("dayTimeUsage", daytimeUsage.getText());
					map.put("tiltAngle", tiltAngle.getText());
					map.put("panelDirection", panDirection.getText());
					map.put("initialInstallCost", initInstalCost.getText());
					map.put("interestRate", interestRate.getText());
					
					map.put("suburb", "STAFFORD");
				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				serverResponseLabel.setText("");

				calcService.CalculationsServer(map,
						new AsyncCallback<ArrayList<ArrayList<String>>>() {
						//TODO improve descriptions
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
								serverResponseLabel.setHTML(SERVER_SUCCESS);
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
		

	    Button b2 = new Button("Show graph!");

	    b2.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        // Create the new popup.
	        final MyPopup popup = new MyPopup();
	        // Position the popup 1/3rd of the way down and across the screen, and
	        // show the popup. Since the position calculation is based on the
	        // offsetWidth and offsetHeight of the popup, you have to use the
	        // setPopupPositionAndShow(callback) method. The alternative would
	        // be to call show(), calculate the left and top positions, and
	        // call setPopupPosition(left, top). This would have the ugly side
	        // effect of the popup jumping from its original position to its
	        // new position.
	        popup.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
	          public void setPosition(int offsetWidth, int offsetHeight) {
	            int left = (Window.getClientWidth() - offsetWidth) / 3;
	            int top = (Window.getClientHeight() - offsetHeight) / 3;
	            popup.setPopupPosition(left, top);
	          }
	        });
	      }
	    });

	    RootPanel.get().add(b2);
		
	} // End onModuleLoad()
	
	/**
	 * This creates the lists for Panel, Inverter and Energy Providers selection.
	 */
	private void createLists() {
		setupService
				.SetupServer(new AsyncCallback<ArrayList<ArrayList<String>>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Failed");//TODO improve description
					}

					@Override
					public void onSuccess(ArrayList<ArrayList<String>> result) {
						ArrayList<String> array0 = result.get(0);//Panel
						ArrayList<String> array1 = result.get(1);//Inverter
						ArrayList<String> array2 = result.get(2);//Energy Provider

						/*
						 * Panel creation
						 */
						// Create a model for the browser.
					    TreeViewModel panelModel = new CellBrowserCreationPanel.CustomTreeModel(array0, "Select Panel");

					    /*
					     * Create the browser using the model. We use <code>null</code> as the
					     * default value of the root node. The default value will be passed to
					     * CustomTreeModel#getNodeInfo();
					     */
					    CellBrowser panelBrowser = new CellBrowser(panelModel, null);
					    panelBrowser.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
					    //RootLayoutPanel.get().add(panelBrowser);
					    panelBrowserPan.add(panelBrowser);
					    panelBrowser.setSize("450px", "200px");
					    
					    
					    
					    /*
					     * Inverter creation
					     */
						// Create a model for the browser.
					    TreeViewModel inverterModel = new CellBrowserCreationInverter.CustomTreeModel(array1, "Select Inverter");

					    /*
					     * Create the browser using the model. We use <code>null</code> as the
					     * default value of the root node. The default value will be passed to
					     * CustomTreeModel#getNodeInfo();
					     */
					    
					    final CellBrowser inverterBrowser = new CellBrowser(inverterModel, null);
					    inverterBrowser.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

					    // Add the browser to the root layout panel.
					    inverterBrowserPan.add(inverterBrowser);
					    inverterBrowser.setSize("450px", "200px");

						for (int i = 0; i < array2.size(); i++) {
							energyProvider.addItem(array2.get(i));
						}

					}

				});
	}

	/**
	 * Generates the table filled with the output.
	 * @param result the output to be inserted into the table.
	 */
	private void generatingOutput(ArrayList<ArrayList<String>> result) {

		ArrayList<String> array0 = result.get(0);
		ArrayList<String> array1 = result.get(1);
		ArrayList<String> array2 = result.get(2);
		ArrayList<String> array3 = result.get(3);
		ArrayList<String> array4 = result.get(4);
		ArrayList<String> array5 = result.get(5);
		
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
		
		if (Double.parseDouble(array5.get(0)) <= 0){
			lblbreakeven.setText("Break Even: Does not break even");
		} else {		
			lblbreakeven.setText("Break Even: " + array5.get(0).toString() + " yrs");
		}

	}
	
	private static class MyPopup extends PopupPanel {
		public MyPopup() {
	      // PopupPanel's constructor takes 'auto-hide' as its boolean parameter.
	      // If this is set, the panel closes itself automatically when the user
	      // clicks outside of it.
	      super(true);

	      // PopupPanel is a SimplePanel, so you have to set it's widget property to
	      // whatever you want its contents to be.
	      //setWidget(new Label(penis()));
	      //setWidget(makeGraph());
	      setWidget(new HTML("<div id=\"chart_div\" style=\"width: 900px; height: 500px;\"></div>"));
	      //drawChart();
	      

	    }//end MyPopup constructor
		
		public static native void drawChart() /*-{
			google.setOnLoadCallback(drawChart);
		}-*/;
		
		public static native String penis() /*-{
			return 'penis';
		}-*/;
		
		public static native void makeGraph() /*-{
			
			google.load("visualization", "1", {
				packages : [ "corechart" ]
			});
			google.setOnLoadCallback(drawChart);
			function drawChart() {
				var data = google.visualization.arrayToDataTable([
						[ 'Year', 'Yearly Savings', 'Investment Return' ],
						[ '1', 712.06, 3347.2 ], [ '2', 1419.15, 3501.17 ],
						[ '3', 2121.24, 3662.23 ], [ '4', 2818.35, 3830.69 ],
						[ '5', 3510.48, 4006.9 ], [ '6', 4197.62, 4191.22 ],
						[ '7', 4879.78, 4384.01 ], [ '8', 5556.95, 4585.68 ] ]);

				var options = {
					title : 'Company Performance'
				};

				var chart = new google.visualization.LineChart(document
						.getElementById('chart_div'));
				chart.draw(data, options);
			}
		}-*/;
		
	  }

}
