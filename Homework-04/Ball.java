import java.text.DecimalFormat;
 
public class Ball {

  private static final double radius = 4.45/12.0;
  private static final int x = 0;
  private static final int y = 1;
  private static final double frictionValue = .99;

private double friction = frictionValue;
private double[] currentPosition = new double [2];
private double[] currentVelocity = new double [2];
private boolean isOnField = true;
 
    public Ball(double xPosition, double yPosition, double xVelocity, double yVelocity) {
        currentPosition[0] = xPosition;
        currentPosition[1] = yPosition;
        currentVelocity[0] = xVelocity;
        currentVelocity[1] = yVelocity;
    }

public double[] getCurrentPosition() {
  return currentPosition;
}


public double[] getDistance(double otherX, double otherY) {
  double ac = (yPos - otherY);
  double cb = (xPos - otherX);
    return Math.hypot (ac, cb);
}

public double[] getCurrentVelocity() {
  return currentVelocity;
} 

public boolean isMoving() {
  return (1.0 <= Math.abs(currentVelocity[x]*12));
}


public void isOnField(double fieldWidth, double fieldHeight) {
if ((Math.abs(currentPosition[y]) >= (fieldHeight / 2)) || (Math.abs(currentPosition[x]) >= (fieldWidth / 2)) ) {
      isOnField = false;
      currentVelocity[y] = 0;
      currentVelocity[x] = 0;
  }
}

public void move (double timeSlice) {
    currentVelocity = changeVel(timeSlice);
    currentPosition[x] += (currentVelocity[x] * timeSlice);
    currentPosition[y] += (currentVelocity[y] * timeSlice);
}

public double[] changeVel( double timeSlice ) {
  friction = Math.pow(0.99, timeSlice); 
  currentVelocity[x] *= friction;
  currentVelocity[y] *= friction;
      return currentVelocity;
}

public String toString() {
   DecimalFormat dfp = new DecimalFormat( "#0.000");
   DecimalFormat dfv = new DecimalFormat( "#0.0000" );
   String output = "position <" + dfp.format( currentPosition[x] ) + ", " + dfp.format( currentPosition[y] ) + ">";
      if( !isOnField ) {
         output += "\t<out of bounds>";
      } else if( (1.0 > Math.abs(currentVelocity[x] * 12)) || (1 > Math.abs(currentVelocity[y] * 12)) ) {
         output += "\t<at rest>";
      } else {
         output += "\tvelocity <" + dfv.format( currentVelocity[x] ) + " X and " + dfv.format( currentVelocity[y] ) + " Y> ft/sec";
      }
      return output;
   }

}
