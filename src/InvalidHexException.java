/**
 * A custom exception thrown when invalid Strings are used to try to instantiate HexNumber objects
 * @author Fowad Sohail
 * @version 12/8/18
 *
 */
public class InvalidHexException extends Exception {

	/**
	 * Constructor to make an InvalidHexException object
	 * @param invalid The String which is invalid for a HexNumber object
	 */
	public InvalidHexException(String invalid) {
		super(invalid);
		System.err.println("Invalid input: Hex values can only have characters 0-9 and A-F");
	}
}
