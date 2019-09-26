/**
 * A class to represent the abstract data type of a hexadecimal number
 * 
 * @author Fowad Sohail
 * @version 12/4/2018
 *
 */
public class HexNumber {
	/*
	 * 5. Encapsulation. Will you need/want private methods?
		 * The only method I made private was the answer to number 4. 
		 * Other methods don't need to be private because it allows users to use the 
		 * methods and constructors that they need to. 
		 * The answer to number 4 uses the defined math operation methods to 
		 * find the answer to (A + (40 / 4) - 14) * 2 in hex.

	
	 *    7. Build and implement a custom exception to prevent clients from instantiating invalid hex numbers like "G2". 
	 *    What would a good superclass be?
	 	* A good superclass for this custom exception is the Exception class. This is because we are creating a 
	 	* custom checked exception. This an exception that needs to be treated explicity to stop users from creating
	 	* invalid HexNumbers. This also is not an exception thrown at runtime and therefore is not a custom unchecked
	 	* exception, which would extend the RunTimeException class.
		 
	 *  * 8. If you are bulding a cohesive abstract data type, is there anything else worth including?
		 * Using interfaces to define an ADT is worth including. This allows us to have multiple 
		 * implementations of the same ADT in the same program. For instance, if instead of Hex, 
		 * we made a binary ADT, an interface would allow us to define two similar ADTs for signed 
		 * and unsigned binary numbers. 
		 * In signed binary, the last bit defines if the number is positive or negative. So an ADT
		 * for signed binary could be its own class and another for unsigned binary. Both of these classes 
		 * would implement the same interface.
		 * This would also force all of the methods of the interface to be defined, meaning the only place 
		 * for errors is in the implementations of those methods.
		 * 
		 * Other things worth including are: isDecimal() because we have isHex() and should have the other corresponding method as well.
		 * 
	 * 
	 */

	//instance variables
	private String hexValue;
	private long decimalValue;

	/**
	 * Constructor to make a HexNumber from a valid hexadecimal String
	 * 
	 * @param hexValue
	 *            The String representation of a hexadecimal number
	 * @throws InvalidHexException
	 *             Thrown when hexValue does not have valid hexadecimal characters
	 *             (0-9, A-F)
	 */
	public HexNumber(String hexValue) throws InvalidHexException {
		try {
			if(!(isHex(hexValue))) {
				throw new InvalidHexException(hexValue);
			}
			
			else {
				this.hexValue = hexValue;
				this.decimalValue = decimalValue(hexValue);
			}
		}
		
		catch(InvalidHexException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor to make a HexNumber from a long
	 * 
	 * @param decimalValue
	 *            A long value for a decimal number
	 */
	public HexNumber(long decimalValue) {
		this.hexValue = hexValue(decimalValue);
		this.decimalValue = decimalValue;
	}

	/**
	 * Adds one hexadecimal number to another
	 * 
	 * @param hex
	 *            The number to add to the calling object
	 * @return a new HexNumber object equivalent to the sum of the two
	 */
	public HexNumber add(HexNumber hex) {
		return new HexNumber(decimalValue + hex.getDecimalValue());
	}

	/**
	 * Analyzes a hexadecimal String and returns its corresponding decimal value
	 * 
	 * @param hexValue
	 *            The hexadecimal value to convert
	 * @return val The decimal value
	 */
	public static long decimalValue(String hexValue) {

		String digits = "0123456789ABCDEF"; // each value is equal to its index value
		hexValue = hexValue.toUpperCase();
		long val = 0;
		for (int i = 0; i < hexValue.length(); i++) {
			if (hexValue.charAt(0) == '-' && i == 0) // checking negative value
			{
				i = 1;
			}
			char c = hexValue.charAt(i);
			long d = digits.indexOf(c);
			val = 16 * val + d;
		}

		if (hexValue.charAt(0) == '-')
			return val * -1;
		else {
			return val;
		}
	}

	/**
	 * Divides one hexadecimal number by another
	 * 
	 * @param hex
	 *            The hexadecimal number which is the divisor in the quotient
	 * @return The quotient of the two hexadecimal numbers
	 */
	public HexNumber divide(HexNumber hex) {
		return new HexNumber(decimalValue / hex.getDecimalValue());
	}

	/**
	 * Converts a decimal value into a hexadecimal String
	 * 
	 * @param decimalValue
	 *            The decimal value to convert to hex
	 * @return The hex value of decimalValue
	 */
	public static String hexValue(long decimalValue) {
		// For storing remainder
		long rem;

		// For storing result
		String str2 = "";

		// Digits in hexadecimal number system
		String digits = "0123456789ABCDEF"; // each value is equal to its index value

		boolean check = false;
		if (decimalValue < 0) {
			decimalValue = -1 * decimalValue;
			check = true;
		}

		if (decimalValue == 0) {
			return "0";
		}

		while (decimalValue > 0) {

			rem = decimalValue % 16;
			str2 = digits.charAt((int) rem) + str2;
			decimalValue = decimalValue / 16;
		}

		if (check)
			return "-" + str2;
		else {
			return str2;
		}
	}

	/**
	 * Subtracts one hexadecimal number from the other
	 * 
	 * @param hex
	 *            The number subtracted from the other
	 * @return a new HexNumber equivalent to the difference of the two
	 */
	public HexNumber subtract(HexNumber hex) {
		return new HexNumber(decimalValue - hex.getDecimalValue());
	}

	/**
	 * Multiplies one hexadecimal number to another
	 * 
	 * @param hex
	 *            A factor of the product
	 * @return The product of two hexadecimal numbers
	 */
	public HexNumber multiply(HexNumber hex) {
		return new HexNumber(decimalValue * hex.getDecimalValue());
	}

	/**
	 * Standard getter method; returns the hexadecimal value (base 16)
	 * representation of an instance of this class.
	 * 
	 * @return hexValue field of an instance of this class
	 */
	public String getHexValue() {
		return hexValue;
	}

	/**
	 * Standard getter method; returns the decimal value (base 10) representation of
	 * this class
	 * 
	 * @return decimalValue field of an instance of this class
	 */
	public long getDecimalValue() {
		return decimalValue;
	}

	/**
	 * Returns true for valid hexadecimal strings
	 * @param potentialHexString The String checked for hexadecimal validity
	 * @return True if potentialHexString is valid for a hexadecimal value
	 */
	public static boolean isHex(String potentialHexString) {
		/*
		 * 6. Why make this validation method static?
			 * We make this validation check static so that we don't have to have an instance
			 * of HexNumber to call this method. If we already have a HexNumber instance, its
			 * hexValue instance variable is already valid. Instead, we write this validation
			 * with a String parameter so that we can check validity before trying to create
			 * a HexNumber object.
		 */
		
		String digits = "0123456789ABCDEF";		//as long as the characters of potentialHexString 
												//are in this String (0-9, A-F), it's valid
		
		char[] check = potentialHexString.toCharArray();
		
		for(char c : check) {
			if(digits.indexOf(c) == -1) {
				return false;
			}
		}
		
		return true;
	}
	
	public String toString() {
		return this.hexValue;
	}
	
	/**
	 * Number 4 on the assignment pdf.
	 * Uses the add(), subtract(), multiply() and divide() methods to calculate (A + (40/4) - 14) * 2 in hexadecimal
	 * @return The HexNumber object representing (A + (40/4) - 14) * 2
	 * @throws InvalidHexException If invalid Strings are used to create HexNumber objects
	 */
	private static HexNumber ArithmeticMethodsExampleProblem4() throws InvalidHexException {
		return new HexNumber("A").add(new HexNumber(40/4)).subtract(new HexNumber(14)).multiply(new HexNumber(2));
	}
}
