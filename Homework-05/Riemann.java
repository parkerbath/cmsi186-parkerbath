import java.util.*;
public class Riemann {
	public static double uBound;
	public static double lBound;
	public static double percentage;
	public static double areaLast;
	public static double areaCurrently;
	public static double width;
	public static double height;
	public static double numOfRects;
	public static double[] coeff;

public Riemann () {
	this.percentage = 1;
	this.numOfRects = 1.0;
}

public void argumentHandler (String[] args) {
	if (args[args.length-1].contains("%")){
		try {
			percentage = Double.parseDouble(args[args.length-1].substring(0, args[args.length-1].length()-1));
		}
		catch (Exception e) {
			throw new IllegalArgumentException ("The percentage value you entered is invalid. Please try again.");
		}
	if (percentage <= 0) {
		throw new IllegalArgumentException ("The percentage value you entered is invalid. Please try again.");
		}
		try {
			coeff = new double [args.length - 4];
		}
		catch (Exception e) {
			throw new IllegalArgumentException ("This is an invalid number of arguments for percentage specified. Please try again");
		}
		try {
			lBound = Double.parseDouble(args[args.length - 3]);
			uBound = Double.parseDouble(args[args.length - 2]);
			for (int i = 1; i < args.length - 3; i++) {
					coeff[i - 1] = Double.parseDouble(args[i]);
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException ("The arguments where the percentage is specified are invalid. Please try again.");
		}
	}
	else {
		try{
			coeff = new double[args.length - 3];
		}
		catch (Exception e) {
			throw new IllegalArgumentException ("This is an invalid number of arguments for default percentage.");
		}
		try{
			lBound = Double.parseDouble(args[args.length - 2]);
			uBound = Double.parseDouble(args[args.length - 1]);
			for (int i = 1; i < args.length - 2; i++) {
				coeff[i-1] = Double.parseDouble(args[i]);
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException ("Arguments with default percentages are still invalid arguments. Please try Again. ");
		}
		if (lBound > uBound) {
			throw new IllegalArgumentException ("The uBound entered ALWAYS has to be greater than the lBound entered");
		}
	}
	switch(args[0]) {
		case "poly": 
		Riemann.poly();
		break;
		case "sin": 
		Riemann.sin();
		break;
		case "cos": 
		Riemann.cos();
		break;
		case "tan": 
		Riemann.tan();
		break;
		case "csc": 
		Riemann.csc();
		break;
		case "sec": 
		Riemann.sec();
		break;
		case "cot": 
		Riemann.cot();
		break;
		case "sqrt": 
		Riemann.sqrt();
		break;
		case "exp": 
		Riemann.exp();
		break;
		case "log": 
		Riemann.log();
		break;
		default: 
			throw new IllegalArgumentException ("Sorry, this type of function isn't supported by the program");
	}
} 

public static double poly () {
	if (coeff.length == 0) {
		throw new IllegalArgumentException ("You must enter actual coefficient arguments");
	}
	width = Math.sqrt(Math.pow(uBound-lBound, 2));
	do {
		areaLast = areaCurrently;
		areaCurrently = 0;
		double xVal = (((width/numOfRects)*.5 + (lBound)));
		for (double i = xVal; i < uBound; i += (width/numOfRects)) {
			for (int k = 0; k < coeff.length; k++) {
				height += (Math.pow(i, k)*coeff[k]);
			}
			areaCurrently += (height * (width/numOfRects));
				height = 0;
		}
		numOfRects += 1.0;
	}
	while ((Math.abs((areaCurrently-areaLast)/areaCurrently) >= percentage) || (Math.abs(areaCurrently/areaLast) == 0));
	return (areaCurrently);
}

public static double sin () {
	if (coeff.length == 0) {
		throw new IllegalArgumentException ("Must enter coefficient arguments");
		}
	width = Math.sqrt(Math.pow(uBound-lBound, 2));
	  do {
		areaLast = areaCurrently;
		areaCurrently = 0;
		for (double i = (((width/numOfRects)/2.0) + (lBound)); i < uBound; i += (width/numOfRects)) {
			for (int k = 0; k < coeff.length; k++) {
				height += coeff[k] * Math.pow(i, k);
			}
			areaCurrently += (Math.sin(height) * (width/numOfRects));
			height = 0;
		}
		numOfRects += 1.0;
	}
	while ((Math.abs(areaCurrently/areaLast) == 0) || (Math.abs((areaCurrently - areaLast)/areaCurrently) >= percentage));
	return (areaCurrently);
}

public static double cos () {
	if (coeff.length == 0) {
		coeff = new double[2];
		coeff[0] = 0;
		coeff[1] = 1;
	}
	width = Math.sqrt(Math.pow(uBound-lBound, 2));
	  do {
		areaLast = areaCurrently;
		areaCurrently = 0;
		for (double i = (((width/numOfRects)/2.0) + (lBound)); i < uBound; i += (width/numOfRects)) {
			for (int k = 0; k < coeff.length; k++) {
				height += coeff[k] * Math.pow(i, k);
			}
			areaCurrently += (Math.cos(height) * (width/numOfRects));
			height = 0;
		}
		numOfRects += 1.0;
	}
	while ((Math.abs(areaCurrently/areaLast) == 0) || (Math.abs((areaCurrently - areaLast)/areaCurrently) >= percentage));
	return (areaCurrently);
}

public static double tan () {
	if (coeff.length == 0) {
		coeff = new double[2];
		coeff[0] = 0;
		coeff[1] = 1;
	}
	width = Math.sqrt(Math.pow(uBound-lBound, 2));
	  do {
		areaLast = areaCurrently;
		areaCurrently = 0;
		for (double i = (((width/numOfRects)/2.0) + (lBound)); i < uBound; i += (width/numOfRects)) {
			for (int k = 0; k < coeff.length; k++) {
				height += coeff[k] * Math.pow(i, k);
			}
			areaCurrently += ((Math.sin(height)/Math.cos(height)) * (width/numOfRects));
			height = 0;
		}
		numOfRects += 1.0;
	}
	while ((Math.abs(areaCurrently/areaLast) == 0) || (Math.abs((areaCurrently - areaLast)/areaCurrently) >= percentage));
	return (areaCurrently);
}

public static double csc () {
	if (coeff.length == 0) {
		coeff = new double[2];
		coeff[0] = 0;
		coeff[1] = 1;
	}
	width = Math.sqrt(Math.pow(uBound-lBound, 2));
	  do {
		areaLast = areaCurrently;
		areaCurrently = 0;
		for (double i = (((width/numOfRects)/2.0) + (lBound)); i < uBound; i += (width/numOfRects)) {
			for (int k = 0; k < coeff.length; k++) {
				height += coeff[k] * Math.pow(i, k);
			}
			areaCurrently += ((1.0/Math.sin(height)) * (width/numOfRects));
			height = 0;
		}
		numOfRects += 1.0;
	}
	while ((Math.abs(areaCurrently/areaLast) == 0) || (Math.abs((areaCurrently - areaLast)/areaCurrently) >= percentage));
	return (areaCurrently);
}

public static double sec () {
	if (coeff.length == 0) {
		coeff = new double[2];
		coeff[0] = 0;
		coeff[1] = 1;
	}
	width = Math.sqrt(Math.pow(uBound-lBound, 2));
	  do {
		areaLast = areaCurrently;
		areaCurrently = 0;
		for (double i = (((width/numOfRects)/2.0) + (lBound)); i < uBound; i += (width/numOfRects)) {
			for (int k = 0; k < coeff.length; k++) {
				height += coeff[k] * Math.pow(i, k);
			}
			areaCurrently += ((1/Math.cos(height)) * (width/numOfRects));
			height = 0;
		}
		numOfRects += 1.0;
	}
	while ((Math.abs(areaCurrently/areaLast) == 0) || (Math.abs((areaCurrently - areaLast)/areaCurrently) >= percentage));
	return (areaCurrently);
}

public static double cot () {
	if (coeff.length == 0) {
		coeff = new double[2];
		coeff[0] = 0;
		coeff[1] = 1;
	}
	width = Math.sqrt(Math.pow(uBound-lBound, 2));
	  do {
		areaLast = areaCurrently;
		areaCurrently = 0;
		for (double i = (((width/numOfRects)/2.0) + (lBound)); i < uBound; i += (width/numOfRects)) {
			for (int k = 0; k < coeff.length; k++) {
				height += coeff[k] * Math.pow(i, k);
			}
			areaCurrently += ((Math.cos(height)/Math.sin(height)) * (width/numOfRects));
			height = 0;
		}
		numOfRects += 1.0;
	}
	while ((Math.abs(areaCurrently/areaLast) == 0) || (Math.abs((areaCurrently - areaLast)/areaCurrently) >= percentage));
	return (areaCurrently);
}

public static double sqrt () {
	if (coeff.length == 0) {
		coeff = new double[2];
		coeff[0] = 0;
		coeff[1] = 1;
	}
	width = Math.sqrt(Math.pow(uBound-lBound, 2));
	  do {
		areaLast = areaCurrently;
		areaCurrently = 0;
		for (double i = (((width/numOfRects)/2.0) + (lBound)); i < uBound; i += (width/numOfRects)) {
			for (int k = 0; k < coeff.length; k++) {
				height += coeff[k] * Math.pow(i, k);
			}
			areaCurrently += (Math.sqrt(Math.abs(height)) * (width/numOfRects));
			height = 0;
		}
		numOfRects += 1.0;
	}
	while ((Math.abs(areaCurrently/areaLast) == 0) || (Math.abs((areaCurrently - areaLast)/areaCurrently) >= percentage));
	return (areaCurrently);
}

public static double exp () {
	if (coeff.length == 0) {
		coeff = new double[2];
		coeff[0] = 0;
		coeff[1] = 1;
	}
	width = Math.sqrt(Math.pow(uBound-lBound, 2));
	  do {
		areaLast = areaCurrently;
		areaCurrently = 0;
		for (double i = (((width/numOfRects)/2.0) + (lBound)); i < uBound; i += (width/numOfRects)) {
			for (int k = 0; k < coeff.length; k++) {
				height += coeff[k] * Math.pow(i, k);
			}
			areaCurrently += (Math.exp(height) * (width/numOfRects));
			height = 0;
		}
		numOfRects += 1.0;
	}
	while ((Math.abs(areaCurrently/areaLast) == 0) || (Math.abs((areaCurrently - areaLast)/areaCurrently) >= percentage));
	return (areaCurrently);
}

public static double log () {
	if (coeff.length == 0) {
		coeff = new double[2];
		coeff[0] = 0;
		coeff[1] = 1;
	}
	width = Math.sqrt(Math.pow(uBound-lBound, 2));
	  do {
		areaLast = areaCurrently;
		areaCurrently = 0;
		for (double i = (((width/numOfRects)/2.0) + (lBound)); i < uBound; i += (width/numOfRects)) {
			for (int k = 0; k < coeff.length; k++) {
				height += coeff[k] * Math.pow(i, k);
			}
			areaCurrently += (Math.log(height) * (width/numOfRects));
			height = 0;
		}
		numOfRects += 1.0;
	}
	while ((Math.abs(areaCurrently/areaLast) == 0) || (Math.abs((areaCurrently - areaLast)/areaCurrently) >= percentage));
	return (areaCurrently);
}

public static void main (String[] args) {
		Riemann riemann = new Riemann();
		riemann.argumentHandler(args);
			System.out.println("The range of error is: " + percentage + "%");
			System.out.println("The area is close to: " + areaCurrently);
	} 
}
