import java.util.Arrays;

public class DynamicChangeMaker {
    static int[] denominations;
    static int target;
    static boolean goodArgs = true;

    public DynamicChangeMaker (String[]arguments) throws IllegalArgumentException {
        String temporaryArray[] = new String[arguments[0].length()];
        temporaryArray = arguments[0].split(",");
        denominations = new int[temporaryArray.length];
            if (denominations.length <= 1) {
                throw new IllegalArgumentException("There are not enough arguments.");
        }
          for (int i = 0; i < temporaryArray.length; i++) {
              
                    denominations[i] = Integer.parseInt(temporaryArray[i]);
                    if (denominations[i] <= 0) {
                      throw new IllegalArgumentException();
                  }
              } 
                 target = Integer.parseInt(arguments[arguments.length - 1]);
                    if (target <= 0) {
                     throw new IllegalArgumentException();
                    } 
                    for (int i = 0; i < denominations.length; i++) {
                        for (int k = 0; k < denominations.length; k++) {
                           if (denominations[i] == denominations[k] && i != k ) {
                                 throw new IllegalArgumentException();
                              }
                        }
                    }
    }

    public static Tuple makeChangeWithDynamicProgramming (int[] denominations, int target) {
            for (int i = 0; i < denominations.length; i++) {
                if (denominations[i] <= 0) {
                    goodArgs = false;
                     }
                     for (int k = 0; k < denominations.length; k++) {
                        if (denominations[i] == denominations[k] && i != k ) {
                             goodArgs = false;
                      }
                   }  
                }
                if (target <= 0) {
                    goodArgs = false;
                }
        if (!goodArgs) {
            return Tuple.IMPOSSIBLE;
        }
        
        Tuple[][] tArray;
            tArray = new Tuple[denominations.length][target + 1];
            for (int i = 0; i < denominations.length; i++) {
                 for (int k = 0; k < target + 1; k++) {
                     if (denominations[i] <= k) {
                              tArray[i][k] = new Tuple (denominations.length);
                           for (int p = 0; p < tArray[i][k].length(); p++) {
                               if (p != i) {
                                     tArray[i][k].setElement(p, 0); 
                            }
                                else{
                                    tArray[i][k].setElement(p, 1); 
                                     
                                  }
                             }
                        }
                     else {
                            if (k == 0) {
                           tArray[i][k] = new Tuple (denominations.length);
                          }
                        else{
                            tArray[i][k] = Tuple.IMPOSSIBLE;
                         }
                      }
                  if (k >= denominations[i]) {
                    if (tArray[i][k - denominations[i]].isImpossible()) {
                        tArray[i][k] = Tuple.IMPOSSIBLE;                        
                    }else{
                        tArray[i][k] = tArray[i][k].add(tArray[i][k - denominations[i]]);
                    }
                }
                     if (i > 0) {
                            if (!tArray[i - 1][k].isImpossible()) {
                            if (tArray[i][k].isImpossible()) {
                                 tArray[i][k] = tArray[i - 1][k];
                              }
                              if (tArray[i][k].total() > tArray[i - 1][k].total()) {
                              tArray[i][k] = tArray[i - 1][k];
                        }
                    }
                }
            }
        }
        return tArray[denominations.length - 1][target];
    }
    public static void main (String[]arguments) {
        DynamicChangeMaker dcm = new DynamicChangeMaker(arguments);  
        System.out.println(dcm.makeChangeWithDynamicProgramming(denominations, target).toString());
        System.out.println("The optimal number of coins are: " + dcm.makeChangeWithDynamicProgramming(denominations, target).total());             
    }
}
