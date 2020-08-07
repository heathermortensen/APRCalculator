//**Package: 602FP
//  File: Model.java
//  Author: Heather Mortensen
//  Class: SIES 602,02
//  Date: Apr 24, 2017
//  Project: Final Project
//  
//  This file contains the business logic for the application.
//  
// 

import java.util.List;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;

public class Model {

//Violet UML
// https://sourceforge.net/projects/violet/?source=typ_redirect
	
//Used for test case:
//	http://www.datadynamica.com/fincalc/fin3.htm
	
//Derivations
//https://ocw.mit.edu/courses/mathematics/18-01sc-single-variable-calculus-fall-2010/1.-differentiation/part-b-implicit-differentiation-and-inverse-functions/session-16-the-derivative-of-a-x/MIT18_01SCF10_ex16sol.pdf

//Newton-Ralphson
//https://www.math.ubc.ca/~anstee/math104/104newtonmethod.pdf
	
			//in dollars
			private double principal;
			
			//in percent
			private double apr;
		
			//numberOfYearsOf12Payments
			private int n;
			
			//payment amount in cents
			private double payment;

	public Model() 
	{		
	
		this.principal = 0.0;
		
		this.apr = 0;

		this.n = 0;
	
		this.payment = 0.0;
		
	}
	
	public Model(double principal,double apr ,int n,double payment)
	{
		
		//convert to cents
		this.principal = principal;
		
		//convert to percent
		this.apr = apr;

		//numberOfPayments - interest gets compounded at the time of each payment.
		this.n = n;
		
		//payment amount in cents
		this.payment = payment;
		
	}
	public boolean isAPRZero()
	{
		if (this.apr == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String findPayment()
	{
		double principalInCents = 0.0;
		
		
		//Convert principal into cents
		
		if (this.principal > 0.99)
		{
			//convert to cents
			
			principalInCents = this.principal * (100);
			
			System.out.println("");
			System.out.println("Principal in cents = " + principalInCents);
			
		}
		
		//Is APR == 0?
		boolean zero =  isAPRZero();
		
		if (zero == true)
		{
			//convert number of payments, n: n * (12 monthly payments)
			this.n = this.n*12;
		
			//Put n into a double so its consistent
			double d_n = this.n;
			
			//Return principal/n into payment
			double pay = principalInCents/d_n;
			
			//format the output into a String for return to Run.java
			
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String moneyString = formatter.format(pay/100);
			
			return moneyString;
			
		}
		else
		{
		
			//convert interest rate, apr: (apr/100)/(12 months)
		
			this.apr = this.apr * 0.01;
			this.apr = this.apr/12;
			
			//convert number of payments, n: n * (12 monthly payments)
		
			this.n = this.n*12;
		
			//Put n into a double so its consistent
			
			double d_n = this.n;
			
			
			System.out.println("APR = " + this.apr );
			System.out.println("n = " + d_n );
			System.out.println("");
		
			//Solve for payment
			
			double pay = 0.0;
			double onePlusAprRaisedToThe_n = Math.pow((1 + this.apr),d_n);
			double numerator = 0.0;
			double denominator = 0.0;
			
			numerator = (this.apr)*(onePlusAprRaisedToThe_n);
			denominator = onePlusAprRaisedToThe_n - 1;
			
			pay = principalInCents*(numerator/denominator);
			
			this.payment = pay;
			
		
			
			//format the output into a String for return to Run.java
			
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String moneyString = formatter.format(pay/100);
	
			
		//test case:
			
//			If user inputs:
//				
//				Principal = 120,000
//				n = 15
//				APR = 8.125
//					
//				then,
//				
//				Monthly payment = 1,155.46
//		
		
		return moneyString;
		
		}//end else
	}
	
	public double convertThisDotAPR()
	{
	//convert interest rate, apr: (apr/100)/(12 months)
		
		this.apr = this.apr * 0.01;
		this.apr = this.apr/12;
		
		return this.apr;
	}
	
	public void convertThisDotPaymentIntoCents()
	{
		if (this.payment > 0.99)
		{
			this.payment = this.payment*100;
		}
		
	}
	
	public double convertThisDotPrincipalIntoCentsAndReturnIt()
	{
		double principalInCents = 0.0;
		
		if (this.principal > 0.99)
		{
			//convert to cents
			
			principalInCents = this.principal * (100);
			
			System.out.println("");
			System.out.println("Principal in cents = " + principalInCents);
			
		}
		
		this.principal = principalInCents;
		
		
		return principalInCents;
	}
	public double convertIntoDollars(double d)
	{
		d = d/100;
		
		return d;
	}
	
	public double convertThisDotPaymentIntoCentsAndReturnIt()
	{
		if (this.payment > 0.99)
		{
			this.payment = this.payment*100;
		}
		
		System.out.println("");
		System.out.println("Payment in cents = " + this.payment);
		
		return this.payment;
	}
	
	public double convertThisDot_nAndReturnIt()
	{
		//convert number of payments, n: n * (12 monthly payments)
		
		this.n = this.n*12;
		
		//convert n into a double
		
		double d_n = (double) this.n;
		
		return d_n;
	}
	
	//This has coupling!!!!!!!!!!
	//This no longer works! Change it if time.
//	public String findEffectiveRateForPaymentMethod(double money)
//	{
//		
//		double base = (1+(this.apr/this.n));
//		double exp = this.n;
//		
//		double ear = (Math.pow(base, exp) - 1);
//		
//		ear = ear*100;
//		
//		System.out.println("EAR = " + ear + "%");
//		
//		String sEAR = ("" + ear);
//		System.out.println("Effective APR (EAR) = " + sEAR + "%");
//		
//		//round the result
//		String style = ".####";
//		DecimalFormat d = new DecimalFormat(style);
//		String format = d.format(ear);
//		//System.out.println(format);
//		
//		System.out.println("Effective APR (EAR) = " + format + "%");
//		System.out.println("");
//		
//		return sEAR;
//	}
	

	
	public double findEffectivePercentRate()
	{
		String result = "";
		double epr = 0.0;
		
		//https://ocw.mit.edu/courses/urban-studies-and-planning/11-431j-real-estate-finance-and-investment-fall-2006/lecture-notes/lec13.pdf
		
		// n = number of compounding periods
		// i = stated interest rate
		//
		// (1 + i/n)^n  - 1   = EPR
		
		//this.apr = (this.apr*12)/100;
		
		double i = this.apr;
		
		//convert i
		//i = (i*12)*100;
		
		double n = (double) this.n;
		
		//n = n *12;
		
		epr = Math.pow((1 + (i/n)),n) - 1;
	
		System.out.println("EPR = " + epr);
		
	return 	epr;
	}
	
	public double findF(double estimateOfRoot)
	{
		double f_Of_X = 0.0;
		
		//To find f_Of_X
		double numerator = 0.0;
		double numeratorTerm1 = 0.0;
		double denominator = 0.0;
		double denominatorFactor1 = 0.0;
		
		//To find f_Of_X
		
		//Initial guess is estimateOfRoot
		
		System.out.println("EstimateOfRoot inside findF(estimatefRoot)" + estimateOfRoot);
	
		//What is interest??????????????????
		double interest = estimateOfRoot;
		System.out.println("interest " + interest);
	
		numeratorTerm1 = Math.pow((1 + interest),n);
		numerator = numeratorTerm1 - 1;
		System.out.println("numerator " + numerator);
	
		denominatorFactor1 = Math.pow((1 + interest), n);
		denominator = interest*denominatorFactor1;
		System.out.println("denominator " + denominator);
	
		f_Of_X = principal/payment - (numerator/denominator);
		System.out.println("f_Of_X = " + f_Of_X);
		System.out.println("");
//		
		return  f_Of_X ;
	}
	
	public double findFPrime(double estimateOfRoot)
	{
		double f_prime_Of_x = 0.0;
		double interest = estimateOfRoot;
		
		//To find f_prime_Of_x
		double numer1 = 0.0;
		double denom1 = 0.0;
		
		//To find f_prime_Of_x
		
		numer1 = Math.pow((1 + interest), n) + (interest*n)*(Math.pow((1 + interest), (n-1)));
		denom1 = Math.pow((interest*Math.pow((1 + interest), n)), 2);
		System.out.println("numer1 " + numer1);
		System.out.println("denom1 " + denom1);
		
		f_prime_Of_x = Math.pow(interest, (-2)) - (numer1/denom1);
		System.out.println("f_prime_Of_x " + f_prime_Of_x);
		System.out.println("");
		
		
		return f_prime_Of_x;
	}

	
	//This is the hard one that requires Newton-Ralphson Method
	public String findAPR_NewtonRalphson()
	{
		//This will work very well if the initial guess is good, and it will work terrible if 
		//   the initial guess is bad. 
		
		//Prepare the guns.....
		
		double principal_local = this.principal;
		principal_local = convertThisDotPrincipalIntoCentsAndReturnIt();
		System.out.println("Principal = " + principal_local);
		
		double payment_local = this.payment;
		payment_local = convertThisDotPaymentIntoCentsAndReturnIt();
		System.out.println("Payment = " + payment_local);
		
		double n_local = this.n;
		n_local = (double)convertThisDot_nAndReturnIt();
		System.out.println("n = " + n_local);
		
		//This doesn't output?????????????????????????????????????
		//???????????????????????????????????????????????????????
		System.out.println("");
	
		String apr = "";
	
		double f_Of_X = 0.0;
		double f_prime_Of_x = 0.0;
		
//		//To find f_Of_X
//		double numerator = 0.0;
//		double numeratorTerm1 = 0.0;
//		double denominator = 0.0;
//		double denominatorFactor1 = 0.0;
//		
//		//To find f_prime_Of_x
//		double numer1 = 0.0;
//		double denom1 = 0.0;
//		
//		
		
		double estimateOfRoot = 0.0;
		double newEstimate = 0.0;
		
//		//To find f_Of_X
//		
//			//Initial guess = P/A
			estimateOfRoot = (principal_local/payment_local)/1200;
//			
		System.out.println("EstimateOfRoot ======" + estimateOfRoot);
//		
//			//What is interest??????????????????
//			double interest = estimateOfRoot;
//			System.out.println("interest " + interest);
//		
//		numeratorTerm1 = Math.pow((1 + interest),n);
//		numerator = numeratorTerm1 - 1;
//		System.out.println("numerator " + numerator);
//		
//		denominatorFactor1 = Math.pow((1 + interest), n);
//		denominator = interest*denominatorFactor1;
//		System.out.println("denominator " + denominator);
//		
//		f_Of_X = principal/payment - (numerator/denominator);
//		System.out.println("f_Of_X = " + f_Of_X);
//		
//		//To find f_prime_Of_x
//		
//		numer1 = Math.pow((1 + interest), n) + (interest*n)*(Math.pow((1 + interest), (n-1)));
//		denom1 = Math.pow((interest*Math.pow((1 + interest), n)), 2);
//		System.out.println("numer1 " + numer1);
//		System.out.println("denom1 " + denom1);
//		
//		f_prime_Of_x = Math.pow(interest, (-2)) - (numer1/denom1);
//		System.out.println("f_prime_Of_x " + f_prime_Of_x);
//		
//		
//		
		//Format 2 decimals
		double number1 = 6.022222444444666668888;
		double number2 = 1.022222444444666668888;
		DecimalFormat numberFormat = new DecimalFormat("#.000000");
		DecimalFormat numberFormat2 = new DecimalFormat("#.0000");
		
		System.out.println("number1: " + number1);
		System.out.println("number2: " + number2);
		System.out.println("");
//		
		double newerEstimate = 0.0;
		
		double root = 1.234456777;
		double newEstimateOfRoot = 4.56789012;
		
		String rootString="a";
		String newEstimateOfRootString="b";
		
		//estimateOfRoot starts at 50.
		
		//for (int i = 0; i < 3; i++)
		while (!(rootString.equals(newEstimateOfRootString)))
		{	
			//numberFormat.format(number1);
			//numberFormat.format(number2);
			
		
			//x sub n+1 = x sub n - f(x sub n)/f'(x sub n)
			f_Of_X = findF(estimateOfRoot);
			f_prime_Of_x = findFPrime(estimateOfRoot);
			
			newEstimate = estimateOfRoot - (f_Of_X/f_prime_Of_x);
			
//			System.out.println("new Estimate " + newEstimate);
//			System.out.println("estimateOfRoot " + estimateOfRoot);
//			System.out.println("f_Of_X " + f_Of_X);
//			System.out.println("f_prime_Of_x " + f_prime_Of_x);

			
			number1 = estimateOfRoot;
			newerEstimate = newEstimate;
			number2 = newerEstimate;
			
			rootString = numberFormat.format(estimateOfRoot);
			newEstimateOfRootString = numberFormat.format(newerEstimate);
			
			System.out.println("");
			System.out.println("rootString = " + rootString);
			System.out.println("newEstimateOfRootString = " + newEstimateOfRootString);
			System.out.println("");
			
			//Print the estimates
//			System.out.println("Estimate of Root: " + number1);
//			System.out.println("Newer Root: " + number2);
//			System.out.println("");
			
			estimateOfRoot = newEstimate;
			
		}
		number2 = ((number2*12)/.01);
		System.out.println("root = " + number2);
		String sRoot = "";
		
		sRoot = numberFormat2.format(number2);
		apr = sRoot.concat("%");
		
		//Print EPR to screen
		System.out.println("" );
		System.out.println("EPR = " + number2 + "%");
		System.out.println("" );
		
		return apr;
	}
	
	public String findPrincipal()
	{
		double paymentInCents = 0.0;
		double interest = 0.0;
		double n = 0;
		
	
		
		//convert payment into cents
		paymentInCents = convertThisDotPaymentIntoCentsAndReturnIt();
		
		//convert apr
		interest = convertThisDotAPR();
		
		//convert n
		n = convertThisDot_nAndReturnIt();
		
		
		//Print EPR to screen
		double epr = findEffectivePercentRate();
		//This does NOT work at all!!!!!!!!!!!!!!! Scratch that!
		//interest = epr;
		
		
		double onePlusAprRaisedTo_n = 0.0;
		double numerator = 0.0;
		double denominator = 0.0;
		
		System.out.println("After conversion, ");
		System.out.println("paymentInCents = " + paymentInCents + " apr = " + interest + " n = " + n);
		System.out.println("");
		
		
		
		onePlusAprRaisedTo_n = Math.pow((1 + interest), n);
		System.out.println("onePlusAprRaisedTo_n = " + onePlusAprRaisedTo_n);
		
		numerator = onePlusAprRaisedTo_n - 1;
		denominator = interest*onePlusAprRaisedTo_n;
		System.out.println("numerator " + numerator);
		System.out.println("denominator = " + denominator);
		
		double principalInCents = 0.0;
		
		principalInCents = paymentInCents*(numerator/denominator);
		
		this.principal = principalInCents/100;
		
		System.out.println("Principal = $" + this.principal);
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(this.principal);
		
		//rounding error here - could find EAR and find (EAR - APR)
		
		
		return moneyString;
	}

	public String findNumberOfPayments() 
	{
		double paymentInCents = 0.0;
		double interest = 0.0;
		double principalInCents = 0.0;
		double n2 = 0;
		double n1 = 0.0;
		double denominator = 0.0;
		double numerator = 0.0;
		double quantity = 0.0;
		double num = 0.0;
		double den = 0.0;
		String numPayments = "";
		
		//convert principal
		principalInCents = convertThisDotPrincipalIntoCentsAndReturnIt();
		
		//convert payment into cents
		paymentInCents = convertThisDotPaymentIntoCentsAndReturnIt();
		
		//convert apr
		interest = convertThisDotAPR();
		
		System.out.println("After conversion, ");
		System.out.println("paymentInCents = " + paymentInCents + " apr = " + interest + " principalInCents = " + principalInCents);
		System.out.println("");
		
		//find the denominator of numberOfPayments.
		//returns the natural log of the sum of the argument and 1.
		denominator = Math.log1p(interest);

		
		//Used to calculate the quantity
		num = paymentInCents;
		den = (paymentInCents - (principalInCents*interest));
		
		quantity = num/den;
		
		//find the numerator if numberOfPayments
		//Returns the natural logarithm (base e) of a double value.
		numerator = Math.log(quantity);
		
		System.out.println("numerator/denominator = " + numerator + "/" + denominator);
		
		n1 = numerator/denominator;
		
		System.out.println("n = " + n1 );
		
		//round to a whole number
		
		n2 = (int) Math.round(n1);

		//convert n back to what it should be by dividing by 12
		
		n2 = n2/12;
		
		numPayments = ("" + n2);
		
		
		
		
		return numPayments;
	}



}//end of class Model.java
