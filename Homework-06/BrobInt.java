/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
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

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( Integer.valueOf( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( Integer.valueOf( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( Long.valueOf( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( Long.valueOf( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   public  String internalValue = "";        // internal String representation of this BrobInt
   private byte   sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // th backwards version of the internal String representation
   private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
     this.internalValue = value;
     this.validateDigits();
     this.byteVersion = this.reverser().getBytes();
     for (int i = 0; i < this.byteVersion.length; i++) {
       this.byteVersion[i] -= (byte) 48;
     }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() {
     if (this.internalValue.length() < 1) {
       throw new IllegalArgumentException("The string cannot be empty");
     }
        else if (String.valueOf(this.internalValue.charAt(0)).equals("+") || String.valueOf(this.internalValue.charAt(0)).equals("-")) {
         if (String.valueOf(this.internalValue.charAt(0)).equals("-")) {
          this.sign = 1;
         }
         if (this.internalValue.length() == 1) {
           throw new IllegalArgumentException("The string has to be more than a '+' or '-' sign");
         }
         else {
           this.internalValue = this.internalValue.substring(1);
         }
       }
      Boolean leadingZero = true;
       for (int i = 0; i < this.internalValue.length(); i++) {
         if (!Character.isDigit(this.internalValue.charAt(i))) {
          throw new IllegalArgumentException("The string must consist of an optional sign followed by valid integers");
        }
        if (leadingZero == true) {
          if (this.internalValue.charAt(i) == '0' && this.internalValue.length() > 1) {
            this.internalValue = this.internalValue.substring(1);
           i--;
          }
          else if (this.internalValue.length() > 1) {
            leadingZero = false;
          }
        }
       }
      return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String reverser() {
         byte[] tempBytes = this.internalValue.getBytes();
         byte[] newBytes = new byte[tempBytes.length];
         for (int i = 0; i < tempBytes.length; i++) {
         newBytes[i] = tempBytes[tempBytes.length-i-1];
        }
       this.reversed = new String(newBytes);
      return this.reversed;
     }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverseSign( BrobInt gint ) {
          String newString = (gint.sign == 1 ? "+" : "-") + gint.internalValue;
        BrobInt newBrobInt = new BrobInt(newString);
        return newBrobInt;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using byte array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt gint ) {
       if (this.compareTo(gint) == -1) {
         return gint.add(this);
       }
      boolean negative = false;
       if (this.sign == 1 && gint.sign == 1) {
           negative = true;
      }
      else if (this.sign == 0 && gint.sign == 1) {
        return this.subtract(this.reverseSign(gint));
      }
       else if (this.sign == 1 && gint.sign == 0) {
         return gint.subtract(this.reverseSign(this));
       }
       byte[] newBytes = new byte[Math.max(this.byteVersion.length, gint.byteVersion.length) + 1];
       for (int i = 0; i < newBytes.length - 1; i++) {
         if (this.byteVersion.length > gint.byteVersion.length) {
           if (i < gint.byteVersion.length) {
               newBytes[i] += (byte) (gint.byteVersion[i] + this.byteVersion[i]);
           }
          else {
             newBytes[i] += (byte) this.byteVersion[i];
           }
        }
        else {
          newBytes[i] += (byte) (this.byteVersion[i] + gint.byteVersion[i]);
        }
        if (newBytes[i] >= (byte) 10) {
           newBytes[i] -= (byte) 10;
           newBytes[i + 1] = (byte) 1;
         }
       }
       byte[] finalBytes = new byte[newBytes.length];
       for (int i = 0; i < newBytes.length; i++) {
         finalBytes[i] = newBytes[newBytes.length-i-1];
       }
       String newString = (negative ? "-" : "+") + Arrays.toString(finalBytes).replaceAll("[,\\s]", "").replaceAll("\\[", "").replaceAll("\\]","");
       BrobInt newBrobInt = new BrobInt(newString);
       return newBrobInt;
    }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using bytes
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt gint ) {
      if (gint.sign == 1) {
         return this.add(this.reverseSign(gint));
       }
       else if (this.compareTo(gint) == -1) {
       return this.reverseSign(gint.subtract(this));
       }
         byte[] newBytes = new byte[Math.max(this.byteVersion.length, gint.byteVersion.length)];
       for (int i = 0; i < newBytes.length; i++) {
         if (i < gint.byteVersion.length) {
         newBytes[i] += (byte) (this.byteVersion[i] - gint.byteVersion[i]);
         }
        else {
          newBytes[i] += (byte) this.byteVersion[i];
       }
        if (newBytes[i] < (byte) 0) {
          newBytes[i] += (byte) 10;
          newBytes[i + 1] = (byte) -1;
           }
        }
      byte[] finalBytes = new byte[newBytes.length];
      for (int i = 0; i < newBytes.length; i++) {
        finalBytes[i] = newBytes[newBytes.length-i-1];
      }
      String newString = Arrays.toString(finalBytes).replaceAll("[,\\s]", "").replaceAll("\\[", "").replaceAll("\\]","");
       BrobInt newBrobInt = new BrobInt(newString);
      return newBrobInt;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply this by
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
     if (this.compareTo(gint) == -1) {
       return gint.multiply(this);
     }
     boolean negative = false;
     if (this.sign != gint.sign) {
       negative = true;
     }
     BrobInt totalBrobInt = new BrobInt(Arrays.toString(new byte[this.byteVersion.length + gint.byteVersion.length]).replaceAll("[,\\s]", "").replaceAll("\\[", "").replaceAll("\\]",""));
     BrobInt partialBrobInt;
     byte[] nextLine;
     byte[] reverseLine;
     for (int i = 0; i < gint.byteVersion.length; i++) {
       nextLine = new byte[this.byteVersion.length + gint.byteVersion.length];
       for (int j = 0; j < this.byteVersion.length; j++) {
         nextLine[j + i] += (byte) (gint.byteVersion[i] * this.byteVersion[j]);
         while (nextLine[j + i] >= (byte) 10) {
           nextLine[j + i] -= (byte) 10;
           nextLine[j + i + 1] += (byte) 1;
         }
       }
       reverseLine = new byte[nextLine.length];
       for (int k = 0; k < nextLine.length; k++) {
         reverseLine[k] = nextLine[nextLine.length-k-1];
       }
       partialBrobInt = new BrobInt(Arrays.toString(reverseLine).replaceAll("[,\\s]", "").replaceAll("\\[", "").replaceAll("\\]",""));
       totalBrobInt = totalBrobInt.add(partialBrobInt);
     }
     byte[] finalBytes = new byte[totalBrobInt.byteVersion.length];
     for (int i = 0; i < totalBrobInt.byteVersion.length; i++) {
       finalBytes[i] = totalBrobInt.byteVersion[totalBrobInt.byteVersion.length-i-1];
     }
     String newString = (negative ? "-" : "+") + Arrays.toString(finalBytes).replaceAll("[,\\s]", "").replaceAll("\\[", "").replaceAll("\\]","");
     BrobInt newBrobInt = new BrobInt(newString);
     return newBrobInt;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
     if (gint.equals(this.ZERO)) {
       throw new IllegalArgumentException("Cannot divide by zero");
     }
     if(this.sign == 1 && gint.sign == 0) {
       return this.reverseSign(this.reverseSign(this).divide(gint));
     }
     if (this.compareTo(gint) == -1) {
       return new BrobInt("0");
     }
     boolean negative = false;
     if (this.sign != gint.sign) {
       negative = true;
     }
     byte[] result = new byte[this.byteVersion.length];
     String remainderString;
     BrobInt divisor = new BrobInt(gint.internalValue);
     BrobInt dividend = new BrobInt(this.internalValue.substring(0, 1));
     int multiples;
     for (int i = 0; i < this.byteVersion.length; i++) {
       multiples = 0;
       if (dividend.compareTo(divisor) >= 0) {
         multiples = 1;
         while(divisor.multiply(new BrobInt("" + (multiples + 1))).compareTo(dividend) <= 0) {
           multiples++;
         }
       }
       result[i] = (byte) (multiples);
       remainderString = dividend.subtract(divisor.multiply(new BrobInt("" + multiples))).internalValue + this.internalValue.substring(Math.min((i + 1), this.internalValue.length()), Math.min((i + 2), this.internalValue.length()));
       dividend = new BrobInt(remainderString);
     }
     String newString = (negative ? "-" : "+") + Arrays.toString(result).replaceAll("[,\\s]", "").replaceAll("\\[", "").replaceAll("\\]","");
     BrobInt newBrobInt = new BrobInt(newString);
     return newBrobInt;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
     if (gint.equals(this.ZERO)) {
       throw new IllegalArgumentException("Cannot divide by zero");
     }
     if(this.sign == 1 && gint.sign == 0) {
       return this.reverseSign(this.reverseSign(this).remainder(gint));
     }
     if (this.compareTo(gint) == -1) {
       return gint;
     }
     boolean negative = false;
     if (this.sign != gint.sign) {
       negative = true;
     }
     byte[] result = new byte[this.byteVersion.length];
     String remainderString;
     BrobInt divisor = new BrobInt(gint.internalValue);
     BrobInt dividend = new BrobInt(this.internalValue.substring(0, 1));
     int multiples;
     for (int i = 0; i < this.byteVersion.length; i++) {
       multiples = 0;
       if (dividend.compareTo(divisor) >= 0) {
         multiples = 1;
         while(divisor.multiply(new BrobInt("" + (multiples + 1))).compareTo(dividend) <= 0) {
           multiples++;
         }
       }
       result[i] = (byte) (multiples);
       remainderString = dividend.subtract(divisor.multiply(new BrobInt("" + multiples))).internalValue + this.internalValue.substring(Math.min((i + 1), this.internalValue.length()), Math.min((i + 2), this.internalValue.length()));
       dividend = new BrobInt(remainderString);
     }
     String newString = (negative ? "-" : "+") + dividend;
     BrobInt newBrobInt = new BrobInt(newString);
     return newBrobInt;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method does not do a lexicographical comparison using the java String "compareTo()" method
   *        It takes into account the length of the two numbers, and if that isn't enough it does a
   *        character by character comparison to determine
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {

     // handle the signs here
      if( 1 == sign && 0 == gint.sign ) {
         return -1;
      } else if( 0 == sign && 1 == gint.sign ) {
         return 1;
      }

     // the signs are the same at this point
     // check the length and return the appropriate value
     //   1 means this is longer than gint, hence larger
     //  -1 means gint is longer than this, hence larger
      if( internalValue.length() > gint.internalValue.length() ) {
         return 1;
      } else if( internalValue.length() < gint.internalValue.length() ) {
         return (-1);

     // otherwise, they are the same length, so compare absolute values
      } else {
         for( int i = 0; i < internalValue.length(); i++ ) {
            Character a = Character.valueOf( internalValue.charAt(i) );
            Character b = Character.valueOf( gint.internalValue.charAt(i) );
            if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
               return 1;
            } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
               return (-1);
            }
         }
      }
      return 0;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" using the
   *        java String "equals()" method -- THAT was easy..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value    long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( Long.valueOf( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      String byteVersionOutput = "";
      for( int i = 0; i < byteVersion.length; i++ ) {
         byteVersionOutput = byteVersionOutput.concat( Byte.toString( byteVersion[i] ) );
      }
      byteVersionOutput = new String( new StringBuffer( byteVersionOutput ).reverse() );
      return internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Helper method to display an Array representation of this BrobInt as its bytes
   *  @param  d  byte array to represent
   *  @see https://docs.oracle.com/javase/9/docs/api/java/util/Arrays.html
   *  NOTE: the java.utils.Arrays class contains a toString() method which is overridden to take quite a
   *        few different array data types as arguments.  To use this with your code, simply change the
   *        data type for the argument to match the data type of the array you want represented.  For
   *        example, change "byte[]" to "int[]" to make this method hand int arrays.
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( byte[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  NOTE:  we don't really care about these, since we test the BrobInt class with the BrobIntTester
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

      System.exit( 0 );
   }
}
