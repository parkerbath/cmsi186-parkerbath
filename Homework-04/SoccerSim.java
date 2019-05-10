public class SoccerSim {
    private static final double maxPos = 1000.0;
    static private final double poleX = 1000;
    static private final double poleY = 1000;
    static Ball[] b = null;

 
 public SoccerSim() {
     super();
    }


public static boolean poleCollision (Ball[] b) {
    boolean ballHitsPole = false;
                for (int i = 0; i <= b.length - 1; i++) {
                    if (b[i].getDistance(poleX)) {
                        System.out.println ("Ball " + i + " had collided with the pole\n");
                                                System.exit(0);
                    }
                    ballHitsPole = true;
        }
}

  public static void handleInitialArguments( String args[] ) {
    System.out.println("\nHello world from the SoccerSim program!\n");
    if( 0 == args.length ) {
                System.out.println( "  Sorry, you have to enter more than four arguments\n" +
                                                                                                                                                "   Usage: java SoccerSim <x> <y> <X Velocity> <Y Velocity> {Time Slice}\n" +
                                                                                                                                                "   Please try again...\n" );
                                                System.exit( 0 );
        } 
        else if (args.length % 4 != 0 ) {
                        if((args.length - 1) % 4 != 0 ){
                                        System.out.println( "   Sorry you must enter a correct number of balls\n" +
                                                                                                                                                "   Usage: java SoccerSim <x> <y> <X Velocity> <Y Velocity> {Time Slice}\n" +
                                                                                                                                                "   Please try again...\n" );
                                                System.exit( 0 );
                   }
        }

         for (int i = 0; i < args.length - 1; i += 4) {
            try {
                b[i/4] = new Ball(Double.parseDouble[i], Double.parseDouble[i+1], Double.parseDouble[i+2], Double.parseDouble[i+3]);
            }
            catch( NumberFormatException nfe ) {
                     System.out.println( USAGE_MESSAGE );
                     System.exit( 5 );
                    }
                   i += 4;
                   j++;
                   }

                System.out.println("FIELD SIZE is: " + maxPos + "ft by " + maxPos + "ft");
                System.out.println("POLE LOCATION is at: " + poleX + ", " + poleY);
    }
 
    public void checkCollision(Ball[] b){
                for(int i = 0; i < b.length; i++){
                        for (int j = i + 1; j < b.length; j++) {
                                if (b[i].getDistance(b[j])) {
                                                System.out.println("Ball " + i + " and Ball " + j + " collided at:");
                                                System.out.println("Ball " + i + " location: " + b[i].toString());
                                                System.out.println("Ball " + j + " location: " + b[j].toString());
                                                System.exit(0);
                                                    }
                                     }
                    }
         }



public boolean moreThanOneBallMoving() {
    for( int i = 0; i < soccerBalls.length; i++ ) {
        if(b[i].isMoving() ) {
            return true;
        }
    }
    return false;
}


public boolean inBounds() {
    for( int i = 0; i < soccerBalls.length; i++ ) {
        if(b[i].isOnField() ) {
            return true;
        }
    }
    return false;
}


 
  public static void main( String args[] ) {
        SoccerSim sSim = new SoccerSim();
        int iteration = 0;
  Ball[] b = new Ball[numberOfBalls];
        int numberOfBalls = (int) args.length/4;
        double timeSlice = 1.0;
        Timer time = new Timer();
        sSim.handleInitialArguments( args );
        int j = 0;
   if ((args.length - 1) % 4 == 0) {
            time.validateTimeSliceArg(args[args.length - 1]);
            timeSlice = Double.parseDouble(args[args.length - 1]);
                for(int i = 0; i < args.length - 1; i += 4) {
                        b[j] = new Ball(Double.parseDouble(args[i]), Double.parseDouble(args[i + 1]),
                                        Double.parseDouble(args[i + 2]), Double.parseDouble(args[i + 3]));
                                j++;
                        }
                } 
                else {
                    timeSlice = time.getTimeSlice();
                    for(int i = 0; i < args.length; i+=4) {
                            b[j] = new Ball(Double.parseDouble(args[i]), Double.parseDouble(args[i + 1]),
                                     Double.parseDouble(args[i + 2]), Double.parseDouble(args[i + 3]));
                                j++;
                        }
                }
            System.out.println("TIMESLICE of: " + timeSlice);
            System.out.println("INITIAL REPORT at: " + time.toString());
            for (int i = 0; i < b.length; i++) {
                                System.out.println("Ball " + i + ": " + b[i].toString());
        }
            System.out.println();
    while ( true ) {
                    time.tick();
                    for (int i = 0; i < b.length; i++) {
                            b[i].move(timeSlice);
                            if (Math.abs(b[i].x) >= sSim.maxPos || Math.abs(b[i].y) >= sSim.maxPos) {
                                            b[i].dx = 0;
                                            b[i].dy = 0;
                                }
                }
                        System.out.println("PROGRESS REPORT at: " + time.toString());
                        for (int i = 0; i < b.length; i++) {
                                    System.out.println("Ball " + i + ": " + b[i].toString());
                            }
                                sSim.checkCollision(b);
                                boolean flag = true;
      for(int i = 0; i < b.length && flag; i++) {
        if ( Math.abs(b[i].dx) >= b[i].getMinVelocity() || Math.abs(b[i].dy) >= b[i].getMinVelocity() ) {
            flag = false;
        }
      }
      if (flag) {
            System.out.println("This simulation ended with no collisions\n");
                              System.exit( 0 );
                }
                    System.out.println();
            }
      }
}
