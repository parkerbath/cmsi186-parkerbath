/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   private double totalSeconds = 0.0;
   private double timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
   private int stringMinutes = 0;
   private int stringHours = 0;
   private int stringSeconds = 0;
  /**
   *  Constructor goes here
   */
   public Clock() {
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current Clock tick
   */
   public double tick() {
     totalSeconds += timeSlice;
     getHandAngle();
     getMinuteHandAngle();
     getHourHandAngle();
     return totalSeconds;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {
   if (Double.parseDouble(argValue) < MAXIMUM_DEGREE_VALUE && Double.parseDouble(argValue) >= 0.0 ) {
       return Double.parseDouble(argValue);
     } 
     else {
     throw new NumberFormatException();
     }
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) {
     if (Double.parseDouble(argValue) >= 0.0 && Double.parseDouble(argValue) < 1800.00) {
       timeSlice = Double.parseDouble(argValue);
     return Double.parseDouble(argValue);
      } 
         else {
            return timeSlice;
      }
   }
  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
    return (HOUR_HAND_DEGREES_PER_SECOND * totalSeconds);
   }
  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      return (totalSeconds * MINUTE_HAND_DEGREES_PER_SECOND)%360;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      return Math.abs(getHourHandAngle() - getMinuteHandAngle());
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return totalSeconds;
   }


  /**
   *  Method to return a String representation of this Clock
   *  @return String value of the current Clock
   */
   public String toString() {
        double secondsForTheString = totalSeconds;
        stringHours = (int)Math.floor(secondsForTheString / 3600);
        stringMinutes = (int)Math.floor((secondsForTheString - stringHours * 3600) / 60);
        stringSeconds = (int)secondsForTheString - ((stringHours * 3600) + (stringMinutes * 60));
        return (stringHours + ":" + String.format("%02d", stringMinutes) + ":" + String.format("%02d", stringSeconds));
    }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */

   public static void main( String args[] ) {
      System.out.println( "\nClock CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new Clock: " );
      Clock Clock = new Clock();
        for (int i = 0; i < 43200; i++) {
          Clock.tick();
          } 
      System.out.println( "    New Clock created: " + Clock.toString() );
      System.out.println("Hour Hand Angle = " + Clock.getHourHandAngle());
      System.out.println("Minute Hand Angle = " + Clock.getMinuteHandAngle());
      System.out.println("Hand Angle = " + Clock.getHandAngle());
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
    try { System.out.println( (0.0 == Clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
