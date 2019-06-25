/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  Collatz.java
 * Purpose    :  Uses BrobInt.java to run the Collatz sequence based on user input
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class Collatz {

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to calculate and output the Collatz sequence
   *  @return int that is the number of steps to finish the Collatz sequence
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static int sequence(BrobInt input) {
     int steps = 0;
     BrobInt current = input;
     System.out.print("\n[" + current.toString());
     while (!current.equals(BrobInt.ONE)) {
       steps++;
       if (current.remainder(BrobInt.TWO).equals(BrobInt.ZERO)) {
         current = current.divide(BrobInt.TWO);
       }
       else {
         current = current.multiply(BrobInt.THREE).add(BrobInt.ONE);
       }
       System.out.print(", " + current.toString());
     }
     System.out.print("]\n");
     return steps;
   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method runs the Collatz sequence based on the arguments
   *  @param  args  String array which contains command line arguments
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the Collatz program!!\n" );
      BrobInt input = null;
      try {
        input = new BrobInt(args[0]);
      }
      catch (Exception e) {
        System.out.println("Please enter a positive integer.");
        System.exit( 0 );
      }
      if (input.compareTo(BrobInt.ZERO) != 1) {
        System.out.println("Please enter a positive integer.");
        System.exit( 0 );
      }
      System.out.println("Running Collatz Sequence on: " + input.toString());

      System.out.println("The sequence took " + sequence(input) + " steps.");
   }
}
