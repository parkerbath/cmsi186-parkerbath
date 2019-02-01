public class CountTheDays extends CalendarStuff {
   public static void main (String [] args ) {
       try {
           long month1 = Long.parseLong(args[0]);
           long day1 = Long.parseLong(args[1]);
           long year1 = Long.parseLong(args[2]);
           long month2 = Long.parseLong(args[3]);
           long day2 = Long.parseLong(args[4]);
           long year2 = Long.parseLong(args[5]);
           if ((CalendarStuff.isValidDate(month1, day1, year1)) && (CalendarStuff.isValidDate(month2, day2, year2))) {
               System.out.println("The amount of days between these two dates are" + " " + CalendarStuff.daysBetween(month1, day1, year1, month2, day2, year2) + ".");
           }else{
               System.out.println("This date is Invalid. Please try again.");
           }
       } catch (NumberFormatException e) {
           System.out.println("This argument is Invalid");
       }
   }
}
