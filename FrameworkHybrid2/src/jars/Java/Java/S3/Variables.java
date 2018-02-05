
public class Variables {

	public static void main(String[] args) {
		/*
		 * Byte
		 * Min: -128
		 * Max: 127
		 * Default: 0
		 */
		byte myByte = 100;
		System.out.println("Byte Value: " + myByte);
		
		/*
		 * Short
		 * Min: -32,768
		 * Max: 32,767
		 * Default: 0
		 */
		short myShort = 10000;
		System.out.println("Short Value: " + myShort);

		
		/*
		 * int
		 * Min: -2,147,483,648
		 * Max: 2,147,483,647
		 * Default: 0
		 */
		int MyInt = 10000000;
		System.out.println("Int Value: " + MyInt);		
		
		/*
		 * long
		 * Min: -2^63
		 * Max: 2^63 -1
		 * Default: 0
		 */
		long myLong = 100000000000000000L;
		System.out.println("Long Value: " + myLong);

		
		/*
		 * float
		 * Default: 0.0f
		 */
		float myFloat = 20.0f;
		System.out.println("Float Value: " + myFloat);

		 
		 /*
		  * double
		  * Default: 0.0d
		  */
		double myDouble = 21.0;
		System.out.println("Double Value: " + myDouble);

		 
		 /*
		  * boolean
		  * false and true
		  * Default: false
		  */
		boolean myBoolean = true;
		System.out.println("Boolean Value: " + myBoolean);

		 
		 /*
		  * char
		  * Min: 0
		  * Max: 65,535
		  */
		char myChar = 'a';
		System.out.println("Char Value: " + myChar);
	}
}
