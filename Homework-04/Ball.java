import java.text.DecimalFormat;
 
public class Ball {
  private Timer time = new Timer();
  private DecimalFormat dFPosition = new DecimalFormat("###0.000");
  private DecimalFormat dFVelocity = new DecimalFormat("#0.0000");
  private final double minVelocity = 0.083;
  private final doubleradius= 4.45/12;
  private double friction = 0.99;
  public double x = 0;
  public double y = 0;
  public double dx = 0;
  public double dy = 0;
 
 
    public Ball(double xPosition, double yPosition, double xVelocity, double yVelocity) {
         this.y = yPosition;
         this.x = xPosition;
         this.dy = yVelocity;
         this.dx = xVelocity;
    }
 
    public void move(double timeSlice){
    friction = 1.0 - (0.01 * timeSlice);
        x += (dx * timeSlice);
        y += (dy * timeSlice);
            if (Math.abs(dx) <= minVelocity){
                  dx = 0;
            } 
            else {
                  dx *= friction;
            }
            if (Math.abs(dy) <= minVelocity) {
                 dy = 0;
             } 
             else {
                 dy *= friction;
            }
        }

    public boolean inContact(Ball b) {
         return (Math.sqrt(Math.pow(this.y - b.y, 2) + Math.pow(this.x - b.x, 2) ) <=radius* 2);
     }

    public double getminVelocity() {
          return minVelocity;
      }
 
    public String toString() {
         if (Math.abs(this.dx) <= minVelocity && Math.abs(this.dy) <= minVelocity){
                return "position <" + dFPosition.format(this.x) + ", " + dFPosition.format(this.y) +
                ">\t< at rest >";
            } 
             else {
                return "position <" + dFPosition.format(this.x) + ", " + dFPosition.format(this.y) +
                ">\tvelocity: <" + dFVelocity.format(this.dx) + " X and " + dFVelocity.format(this.dy) + " Y> ft/sec";
      }
}
 
    public String getLocation() {
          return "position <" + dFPosition.format(this.x) + ", " + dFPosition.format(this.y) + ">";
       }
}
