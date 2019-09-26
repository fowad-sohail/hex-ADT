/**
 * A class to represent the abstract data type of a hexadecimal number
 * 
 * @author Fowad Sohail
 * @version 12/4/2018
 *
 */
public class HexNumber {

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
	 * Uses the add(), subtract(), multiply() and divide() methods to calculate (A + (40/4) - 14) * 2 in hexadecimal
	 * @return The HexNumber object representing (A + (40/4) - 14) * 2
	 * @throws InvalidHexException If invalid Strings are used to create HexNumber objects
	 */
	private static HexNumber ArithmeticMethodsExampleProblem4() throws InvalidHexException {
		return new HexNumber("A").add(new HexNumber(40/4)).subtract(new HexNumber(14)).multiply(new HexNumber(2));
	}
}
