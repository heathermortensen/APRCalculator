/**
 * Package: 602FP
 * File: Run.java
 * Author: Heather Mortensen
 * Class: SIES 602,02
 * Date: Apr 23, 2017
 * Project: Final Project
 * 
 * This file runs the GUI and verifies user input in main().
 * 
 */

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//Good Tutorial.........
		//http://docs.oracle.com/javafx/2/events/processing.htm#CEGJAAFD
public class Run extends Application implements EventHandler<ActionEvent>{
	
	//holds the number of user inputs
	int countValidInputs = 0;
	int countInValidInputs = 0;

	public static void main(String[] args)
	{
		//this method is from inside the Application class.
		//It sets up your program as a javaFx Application, then calls start().
		
		// Application.launch() -> Application.start()
		
		Application.launch(args);
		
	}

	//The primaryStage is the main window.
	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primaryStage) throws Exception {

		Model model = new Model();
		
	//CREATE GUI
		
		//The window is called the stage. The stuff inside the 
		//window is called the scene.
		
		//This view object receives the stage and creates the scene.
		
		GraphicalInterface view = new GraphicalInterface(model,primaryStage);

		view.createView(primaryStage);
		
		//Button submit = new Button("Submit data");
		
		//VBox box = new VBox();
		
		
		//box.getChildren().addAll(submit);
		
		//view.getChildren().addAll(box);
		
		
		//validate text field input
		//view.calc.setOnAction(e -> isValidPrincipal(view.amountOfMoneyTextFld, view.amountOfMoneyTextFld.getText()));
		//view.calc.setOnAction(e -> isValidAPR(view.aprTextFld, view.aprTextFld.getText()));
		//view.calc.setOnAction(e -> isValidNumPaymentsTextFld(view.numPaymentsTextFld, view.numPaymentsTextFld.getText()));
		//view.calc.setOnAction(e -> isValidPaymentTextFld(view.paymentTextFld, view.paymentTextFld.getText()));
		
	
	
		
//		boolean a = isValidPrincipal(view.amountOfMoneyTextFld, view.amountOfMoneyTextFld.getText());
//		boolean b = isValidAPR(view.aprTextFld, view.aprTextFld.getText());
//		boolean c = isValidNumPaymentsTextFld(view.numPaymentsTextFld, view.numPaymentsTextFld.getText());
//		boolean d = isValidPaymentTextFld(view.paymentTextFld, view.paymentTextFld.getText());
		
		
		//boolean a = view.amountOfMoneyTextFld.hasProperties()
		//Setting an action for the Submit button
		
		//view.calc.setDisable(true);
		
		//Implemented when the user clicks the 'calculate' button
		
		view.calc.setOnAction(new EventHandler<ActionEvent>() 
		{

			
		@Override
		    public void handle(ActionEvent e) 
			{
			
				//if ( d = 0 )    ||  ( c = 0)      ||  ( b = 0)        || ( a = 0)
				//if ((a && b && c) || (a && b && d) || (a && c && d) || (b && c && d))
			
				boolean a = isValidPrincipal(view.amountOfMoneyTextFld, view.amountOfMoneyTextFld.getText());
				boolean b = isValidAPR(view.aprTextFld, view.aprTextFld.getText());
				boolean c = isValidNumPaymentsTextFld(view.numPaymentsTextFld, view.numPaymentsTextFld.getText());
				boolean d = isValidPaymentTextFld(view.paymentTextFld, view.paymentTextFld.getText());
				
				System.out.println("");
			
				//calculate monthly payment
				if ((a && b && c) && (!d))
				{
	
					view.calc.setDisable(false);
					
					if ( ! (view.paymentTextFld.getText().equals("") ) )
					{
						System.out.println("");
						System.out.println("Invalid entry for Payment.");
						System.out.println("Payment will be calculated according to Principal, APR, and Number of Payments.");
					}
					
					//parse strings into correct data types
					double principal = Double.parseDouble(view.amountOfMoneyTextFld.getText());
					double apr = Double.parseDouble(view.aprTextFld.getText());
					int numPayments = Integer.parseInt(view.numPaymentsTextFld.getText());
					//double payment = Double.parseDouble(view.paymentTextFld.getText());
					
					Model m1 = new Model(principal, apr, numPayments, 0.0);
					String result = m1.findPayment();
					
					System.out.println("Payment: " + result);
					
					
					//view.paymentTextFld.setStyle("-fx-font-weight: bold");
					view.paymentTextFld.setStyle("-fx-text-fill: green");
					view.paymentTextFld.setText(result);
					
					view.assumptionsLbl.setVisible(true);
					view.assumptionsLbl.setWrapText(true);
					view.assumptionsLbl.setText("   Assumptions: This assumes monthly payments. If user input n = 15, " + "\n" + "                          then n actually = 15*(12 monthly payments)");
	
				}
				
				//calculate  NumPayments
				else if (a && b && d && (!c))
				{
		
					view.calc.setDisable(false);
					
					
					if ( ! (view.numPaymentsTextFld.getText().equals("") ) )
					{
						System.out.println("");
						System.out.println("Invalid entry for Number of Payments.");
						System.out.println("Number of Payments will be calculated according to Principal, APR, and Payment amount.");
					}
					
					//parse strings into correct data types
					double principal = Double.parseDouble(view.amountOfMoneyTextFld.getText());
					double apr = Double.parseDouble(view.aprTextFld.getText());
					//int numPayments = Integer.parseInt(view.numPaymentsTextFld.getText());
					double payment = Double.parseDouble(view.paymentTextFld.getText());
					
					Model m1 = new Model(principal, apr, 0, payment);
					String result = m1.findNumberOfPayments();
					
					System.out.println("NumberOfPayments: " + result);
					
					view.numPaymentsTextFld.setStyle("-fx-text-fill: green");
					view.numPaymentsTextFld.setText(result);
	
				
				}
				
				//calculate APR
				else if (a && c && d && (!b))
				{
	
					view.calc.setDisable(false);
					
					if ( ! (view.aprTextFld.getText().equals("") ) )
					{
						System.out.println("");
						System.out.println("Invalid entry for APR.");
						System.out.println("APR will be calculated according to Principal, Number of payments, and Payment amount.");
					}
					
					//parse strings into correct data types
					double principal = Double.parseDouble(view.amountOfMoneyTextFld.getText());
					//double apr = Double.parseDouble(view.aprTextFld.getText());
					int numPayments = Integer.parseInt(view.numPaymentsTextFld.getText());
					double payment = Double.parseDouble(view.paymentTextFld.getText());
					
					Model m1 = new Model(principal, 0, numPayments, payment);
					
					
					String result = m1.findAPR_NewtonRalphson();
					
					System.out.println("APR: " + result);	
					
					//view.paymentTextFld.setStyle("-fx-font-weight: bold");
					view.aprTextFld.setStyle("-fx-text-fill: green");
					view.aprTextFld.setText(result);
				
				}
				
				//calculate Principal
				else if (b && c && d && (!a))
				{
		
					view.calc.setDisable(false);
					
					if ( ! (view.amountOfMoneyTextFld.getText().equals("") ) )
					{
						System.out.println("");
						System.out.println("Invalid entry for principal.");
						System.out.println("Principal will be calculated according to APR, Number of payments, and Payment amount.");
					}
					
					//parse strings into correct data types
					double apr = Double.parseDouble((view.aprTextFld.getText()));
					int numPayments = Integer.parseInt(view.numPaymentsTextFld.getText());
					double payment = Double.parseDouble(view.paymentTextFld.getText());
					
					Model m1 = new Model(0.0, apr, numPayments, payment);
					String result = m1.findPrincipal();
					
					System.out.println("Principal: " + result);
					
					view.amountOfMoneyTextFld.setStyle("-fx-text-fill: green");
					view.amountOfMoneyTextFld.setText(result);
				
				}
				else 
				{
					if (countValidInputs != 3)
					{
						view.calc.setDisable(true);
						
						boolean f = isNotValidPrincipal(view.amountOfMoneyTextFld, view.amountOfMoneyTextFld.getText());
						boolean g = isNotValidAPR(view.aprTextFld, view.aprTextFld.getText());
						boolean h = isNotValidNumPaymentsTextFld(view.numPaymentsTextFld, view.numPaymentsTextFld.getText());
						boolean i = isNotValidPaymentTextFld(view.paymentTextFld, view.paymentTextFld.getText());
					
						System.out.println("");
						System.out.println("You MUST enter 3 valid inputs. ");
					
						if (countValidInputs < 3)
						{
							System.out.println("You only entered " + countValidInputs + " valid inputs.");
						}
						else if (countValidInputs > 3)
						{
							System.out.println("You entered " + (countValidInputs) + " valid inputs.");
							System.out.println("You entered " + (countInValidInputs) + " invalid inputs.");
						}
						else
						{
							System.out.println("Thanks for entering the correct number of inputs.");
						}
					}//end if
					
					
				}//end else

			} 
		
		     
		 });//end event handler
		
		


	}
	
	private void setCount ()
	{
		this.countValidInputs = this.countValidInputs + 1;
	}
	
	private void setBadCount ()
	{
		this.countInValidInputs = this.countInValidInputs + 1;
	}

	
	private boolean isValidPrincipal(TextField amountOfMoneyTextFld,  String text ) {
		
		try
		{
			double principal = Double.parseDouble(amountOfMoneyTextFld.getText());
			
			
			System.out.println("Princiapl = $" + principal);
			
			//Increase count to show that there is valid data in one field
			setCount();
			
			return true;
		}
		catch (NumberFormatException e)
		{
			setBadCount();
			
			return false;
		}
	}


	
	private boolean isNotValidPrincipal(TextField amountOfMoneyTextFld,  String text ) {
		
		try
		{
			double principal = Double.parseDouble(amountOfMoneyTextFld.getText());
			
			return false;
		}
		catch (NumberFormatException e)
		{
			
			System.out.println("----ERROR: Your input into 'principal' was not a double!!!!!");
			
			amountOfMoneyTextFld.setStyle("-fx-font-weight: bold");
			amountOfMoneyTextFld.setStyle("-fx-text-fill: red");
			amountOfMoneyTextFld.setText(text);
			
			
			return true;
		}
	}
	
	private boolean isValidAPR(TextField aprTextFld, String text) {
		
		try
		{
			double apr = Double.parseDouble(aprTextFld.getText());
			
			
			System.out.println("APR = " + apr + "%");
			
			//Increase count to show that there is data in one field
			setCount();
			
			return true;
		}
		catch (NumberFormatException e)
		{
			setBadCount();
			
			return false;
		}
	}
	
	private boolean isNotValidAPR(TextField aprTextFld, String text) {
		
		try
		{
			double apr = Double.parseDouble(aprTextFld.getText());
			
			
			return false;
		}
		catch (NumberFormatException e)
		{
			System.out.println("----ERROR: Your 'APR' input was not a double!!!!!");
			
			aprTextFld.setStyle("-fx-font-weight: bold");
			aprTextFld.setStyle("-fx-text-fill: red");

			
			return true;
		}
	}
	private boolean isValidNumPaymentsTextFld(TextField numPaymentsTextFld, String text) {
		
		try
		{
			int numPayments = Integer.parseInt(numPaymentsTextFld.getText());
			
			
			System.out.println("Number of payments = " + numPayments );
			
			//Increase count to show that there is data in one field
			setCount();
			
			return true;
		}
		catch (NumberFormatException e)
		{
			setBadCount();
			
			return false;
		}
	}
	
	private boolean isNotValidNumPaymentsTextFld(TextField numPaymentsTextFld, String text) {
		
		try
		{
			int numPayments = Integer.parseInt(numPaymentsTextFld.getText());
			
	
			
			return false;
		}
		catch (NumberFormatException e)
		{
			System.out.println("----ERROR: Your input into 'Number of payments' was not an integer!!!!!");
			
			numPaymentsTextFld.setStyle("-fx-font-weight: bold");
			numPaymentsTextFld.setStyle("-fx-text-fill: red");
			
			return true;
		}
	}
	
	private boolean isValidPaymentTextFld(TextField paymentTextFld, String text) {
		
		try
		{
			double payment = Double.parseDouble(paymentTextFld.getText());
			
			
			System.out.println("Payments = $" + payment );
			
			//Increase count to show that there is data in one field
			setCount();
			
			return true;
		}
		catch (NumberFormatException e)
		{
			setBadCount();
			
			return false;
		}
	}
	
	private boolean isNotValidPaymentTextFld(TextField paymentTextFld, String text) {
		
		try
		{
			double payment = Double.parseDouble(paymentTextFld.getText());
			
			return false;
		}
		catch (NumberFormatException e)
		{
			System.out.println("----ERROR: Your input into 'Payment' was not an double!!!!!");
			
			paymentTextFld.setStyle("-fx-font-weight: bold");
			paymentTextFld.setStyle("-fx-text-fill: red");
			
			return true;
		}
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
