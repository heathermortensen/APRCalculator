//**Package: 602FP
//  File: Run.java
//  Author: Heather Mortensen
//  Class: SIES 602,02
//  Date: Apr 22, 2017
//  Project: Final Project
//  
//  This file contains the code for the application window - four text fields and a button.
//  It is run from the start() method in file Run.java.
//  
// 

import javafx.scene.layout.HBox;

//There should be NO awt in here!!!!!
import javafx.scene.control.Label;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
//used to change label color
//import javafx.scene.paint.Color;


import javafx.scene.control.TextField;

import javafx.scene.control.ChoiceBox;
//http://docs.oracle.com/javafx/2/collections/jfxpub-collections.htm#CIHEJJFH
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.control.SelectionModel;


//provides the control libraries for buttons, textfields, etc.
import javafx.scene.*;
import javafx.application.*;
import javafx.geometry.Insets;
//http://stackoverflow.com/questions/25222811/access-restriction-the-type-application-is-not-api-restriction-on-required-l
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
//This file contains the View portion of the project Finance Calculator

public class GraphicalInterface extends BorderPane
{
		private final Model myModel;
	
			//Labels
	
			Label amountOfMoneyLbl = new Label("                        Principal           ");
			Label aprLbl = new Label("                               APR           ");
			Label numPaymentsLbl = new Label("     Number of payments           ");
			Label paymentLbl = new Label("           Monthly Payment           ");
			Label assumptionsLbl = new Label("xxx");
			
			//Text Fields
			
			TextField amountOfMoneyTextFld = new TextField("");
			TextField aprTextFld = new TextField("");
			TextField numPaymentsTextFld = new TextField("");
			TextField paymentTextFld = new TextField("");
			
			Button calc = new Button("Calculate");
	


	public GraphicalInterface(Model model, Stage primaryStage) 
	{
		this.myModel = model;
		createView(primaryStage);

	}
	
	@SuppressWarnings("restriction")
	public void createView(Stage primaryStage)
	{
		
		
		// Create my root pane

				BorderPane myBorderPane = new BorderPane();
				
				//make all the HBoxes and Calc Button
				
					//color codes at: https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#typecolor
			
				HBox HBox1 = new HBox();
				HBox1 = addHBox1(amountOfMoneyLbl, amountOfMoneyTextFld);

				HBox HBox2 = new HBox();
				HBox2 = addHBox2(aprLbl,aprTextFld/*, percentSignLbl*/);
				
				HBox HBox3 = new HBox();
				HBox3 = addHBox3(numPaymentsLbl,numPaymentsTextFld);
				
				HBox HBox4 = new HBox();
				HBox4 = addHBox4(paymentLbl,paymentTextFld);
				
				assumptionsLbl.setVisible(false);
				assumptionsLbl.setStyle("-fx-text-fill: white");
				assumptionsLbl.setFont(new Font("Arial", 14));
				
				myBorderPane.setCenter(addAllHBoxes(HBox1,HBox2, HBox3, HBox4, calc, assumptionsLbl ));
				
				

				// create scene and place it in the stage

				Scene scene = new Scene(myBorderPane, 600, 450);
				
				primaryStage.setScene(scene);
				primaryStage.setTitle("Monthly Finance calculator");
				primaryStage.show();
					
		//fun colors:
		//more st thomas purple
		//vBox.setStyle("-fx-background-color:#850085");
		//st thomas purple
		//vBox.setStyle("-fx-background-color:#270027");
		//blood red
		//vBox.setStyle("-fx-background-color:#271215");
		//color become a naval blue
		//vBox.setStyle("-fx-background-color:#272744");
		//vBox.setStyle("-fx-background-color:black");
	
		
		
	}
	//added
	private VBox addAllHBoxes(HBox HBox1,HBox HBox2, HBox HBox3, HBox HBox4, Button calc,Label assumptionsLbl) 
	{
		VBox vbox = new VBox(25);
		vbox.setStyle("-fx-background-color:#2f4f4f");
		//#2f4f4f

		vbox.getChildren().addAll(HBox1,HBox2, HBox3, HBox4);
		
		vbox.setAlignment(Pos.CENTER);
		
		vbox.getChildren().addAll(calc);
		
		vbox.getChildren().addAll(assumptionsLbl);
		
		return vbox;
	}
	
	private HBox addHBox1(Label amountOfMoneyLbl,TextField amountOfMoneyTextFld) 
	{
		
		HBox hbox1 = new HBox(25);
	
		hbox1.setVisible(true);
		hbox1.setPadding(new Insets(15, 15, 15, 15));
		hbox1.setStyle("-fx-background-color:#8fbc8f");
		
		//fun colors:
		//hbox1.setStyle("-fx-background-color:#270027");
		//#3cb371
		//hbox1.setStyle("-fx-background-color:#122215");
		
		amountOfMoneyLbl.setFont(new Font("Arial", 20));
		
	
		
		//getChildren is inherited from scene
		hbox1.getChildren().addAll(amountOfMoneyLbl,amountOfMoneyTextFld);
		
		return hbox1;
	}
	
	private HBox addHBox2(Label aprLbl,TextField aprTextFld/*, Label percentSignLbl*/) 
	{
		
		HBox hbox2 = new HBox(25);
	
		hbox2.setVisible(true);
		hbox2.setPadding(new Insets(15, 15, 15, 15));
		hbox2.setStyle("-fx-background-color:#8fbc8f");
		
		//fun colors:
		//hbox1.setStyle("-fx-background-color:#272744");
		//hbox2.setStyle("-fx-background-color:#8fbc8f");
		//https://www.youtube.com/watch?v=-JJQL42WLk8

		aprLbl.setFont(new Font("Arial", 20));
		//percentSignLbl.setFont(new Font("Arial", 20));
	
		
		//getChildren is inherited from scene
		hbox2.getChildren().addAll(aprLbl,aprTextFld/*, percentSignLbl*/);
		
		return hbox2;
	}
	
	private HBox addHBox3(Label numPaymentsLbl,TextField numPaymentsTextFld) 
	{
		
		HBox hbox3 = new HBox(25);
	
		hbox3.setVisible(true);
		hbox3.setPadding(new Insets(15, 15, 15, 15));
		hbox3.setStyle("-fx-background-color:#8fbc8f");
		//fun colors:
		//hbox1.setStyle("-fx-background-color:#272744");
		//hbox2.setStyle("-fx-background-color:#8fbc8f");
		//https://www.youtube.com/watch?v=-JJQL42WLk8
	
		numPaymentsLbl.setFont(new Font("Arial", 20));
	
		
		//getChildren is inherited from scene
		hbox3.getChildren().addAll(numPaymentsLbl,numPaymentsTextFld);
		
		return hbox3;
	}
	
	private HBox addHBox4(Label paymentLbl,TextField paymentTextFld) 
	{
		
		HBox hbox4 = new HBox(25);
	
		hbox4.setVisible(true);
		hbox4.setPadding(new Insets(15, 15, 15, 15));
		hbox4.setStyle("-fx-background-color:#8fbc8f");
		
		//fun colors
		//hbox1.setStyle("-fx-background-color:#272744");
		//hbox2.setStyle("-fx-background-color:#8fbc8f");
		//https://www.youtube.com/watch?v=-JJQL42WLk8
		
		paymentLbl.setFont(new Font("Arial", 20));
		
		//getChildren is inherited from scene
		hbox4.getChildren().addAll(paymentLbl,paymentTextFld);
		
		return hbox4;
	}


}
	



