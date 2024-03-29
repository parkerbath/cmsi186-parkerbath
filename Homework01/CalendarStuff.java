
 /*  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  <Parker Bath>
 *  Date          :  <1-31-19>
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 */
public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = MONDAY    + 1;
   private static final int WEDNESDAY = TUESDAY   + 1;
   private static final int THURSDAY  = WEDNESDAY + 1;
   private static final int FRIDAY    = THURSDAY  + 1;
   private static final int SATURDAY  = FRIDAY    + 1;

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = FEBRUARY  + 1;
   private static final int APRIL      = MARCH     + 1;
   private static final int MAY        = APRIL     + 1;
   private static final int JUNE       = MAY       + 1;
   private static final int JULY       = JUNE      + 1;
   private static final int AUGUST     = JULY      + 1;
   private static final int SEPTEMBER  = AUGUST    + 1;
   private static final int OCTOBER    = SEPTEMBER + 1;
   private static final int NOVEMBER   = OCTOBER   + 1;
   private static final int DECEMBER   = NOVEMBER  + 1;

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   */
   private static int[]    days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor called..." );
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      if(year % 100 == 0){
         return false;
      }else if(year % 400 == 0){
         return true;
      }else if(year % 4 == 0){
         return true;
      }
      return false;
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ){
     if(isLeapYear(year) && month == 2){
         return 29;
      }
      return days[(int)month-1];
   }
   
  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
       if(year1 != year2){
         return false;
      } else if(day1 != day2){
         return false;
      } else if(month1 != month2){
         return false;
      }
      return true;
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
    if(dateEquals(month1, 1, year1, month2, 1, year2) == true){
      return 0;
      }
   if(year1 > year2){
      return 1;
      }
   if(year1 == year2){
      if(month1 > month2){
         return 1;
         } 
       else {
         return -1;
         }
      }
      return -1;
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
    if(month == 2 && isLeapYear(year) && day == 29){
         return true;
    }
   return (month <= 12 && month >= 1) && (day >= 1 && day <= days[(int)month-1]) && (year >= 1 && year <= 9999);
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
   String stringMonth;
     switch (month) {
         case 1:  stringMonth = "January";
               break;
         case 2:  stringMonth = "February";
               break;
         case 3:  stringMonth = "March";
               break;
         case 4:  stringMonth = "April";
               break;
         case 5:  stringMonth = "May";
               break;
         case 6:  stringMonth = "June";
               break;
         case 7:  stringMonth = "July";
               break;
         case 8:  stringMonth = "August";
               break;
         case 9:  stringMonth = "September";
               break;
         case 10: stringMonth = "October";
               break;
         case 11: stringMonth = "November";
               break;
         case 12: stringMonth = "December";
               break;
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
        }
        return stringMonth;
   }
  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
  String stringDayOfWeek;
     switch (day) {
         case 1:  stringDayOfWeek = "Sunday";
               break;
         case 2:  stringDayOfWeek = "Monday";
               break;
         case 3:  stringDayOfWeek = "Tuesday";
               break;
         case 4:  stringDayOfWeek = "Wednesday";
               break;
         case 5:  stringDayOfWeek = "Thursday";
               break;
         case 6:  stringDayOfWeek = "Friday";
               break;
         case 7:  stringDayOfWeek = "Saturday";
               break;
       default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
        }
        return stringDayOfWeek;
   }
  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     long daysInBetween = 0;
        if (year1 == year2) {
     if (month1 == month2){
       daysInBetween += day2 - day1;
       return daysInBetween;
     }
     daysInBetween += daysInMonth(month1, year1) - day1;
     for (long i = month1 + 1; i < month2; i++) {
       daysInBetween += daysInMonth(i, year1);
     }
     daysInBetween += day2;
     return daysInBetween;
   }
      if (compareDate(month1, day1, year1, month2, day2, year2) == 0){
        return daysInBetween;
     } 
       else if(compareDate(month1, day1, year1, month2, day2, year2) > 0){
        long temporaryMonth = month1;
         month1 = month2;
         month2 = temporaryMonth;
         long temporaryDay = day1;
         day1 = day2;
         day2 = temporaryDay;
         long temporaryYear = year1;
         year1 = year2;
         year2 = temporaryYear;
      }    
   daysInBetween += daysInMonth(month1, year1) - day1;
   for ( long i = month1 + 1; i <= 12; i++) {
     daysInBetween += daysInMonth(i, year1);
   }
   daysInBetween += ((year2 - year1) - 1) * 365;
   for ( long i = 1; i < month2; i++) {
     daysInBetween += daysInMonth(i, year2);
   }
   daysInBetween += day2;
   for ( long i = year1 + 1; i < year2; i++) {
     if (isLeapYear(i)){
       daysInBetween++;
     }
   }
   return daysInBetween;
 }
}
