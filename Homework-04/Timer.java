import java.text.DecimalFormat;
 
public class Timer {
    private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
    private double timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
    private double totalTimeSeconds = 0;

 
    public Timer() {
    }
 
    public double validateTimeSliceArg( String argValue ) {
        if (Double.parseDouble(argValue) < 1800.00 && Double.parseDouble(argValue) > 0.0) {
             timeSlice = Double.parseDouble(argValue);
                return Double.parseDouble(argValue);
         } 
         else {
            return 60.0;
         }
    }

    public double tick() {
         totalTimeSeconds += timeSlice;
         return totalTimeSeconds;
    }

    public double getTotalSeconds() {
            return totalTimeSeconds;
        }

    public double getTimeSlice() {
            return timeSlice;
        }
 
    public String toString() {
             DecimalFormat dFSeconds = new DecimalFormat("#0.000s");
             DecimalFormat dFMinHour = new DecimalFormat("00");
             int hoursAtAngle = (int) Math.floor(totalTimeSeconds / 60 / 60);
             int minsAtAngle = (int) Math.floor((totalTimeSeconds - (hoursAtAngle * 60 * 60)) / 60);
             String secsAtAngle = dFSeconds.format((totalTimeSeconds - (hoursAtAngle * 60 * 60)) % 60);
             return dFMinHour.format(hoursAtAngle) + " : " + dFMinHour.format(minsAtAngle) + " : " + secsAtAngle;
        }
}
