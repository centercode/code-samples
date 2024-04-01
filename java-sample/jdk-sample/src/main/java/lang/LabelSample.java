package lang;

/**
 * Reference: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/branch.html
 */
public class LabelSample {

    public static void main(String[] args) {
        LabelSample sample = new LabelSample();
        sample.breakInnerContinueOuterLoop();
    }

    void breakInnerContinueOuterLoop() {
        outer:
        for (int i = 0; i < 3; i++) {
            System.out.println("outer start.[i=" + i + "]");
            for (int j = 0; j < 3; j++) {
                System.out.println("\tinner start.[j=" + j + "]");
                if (i == 1 && j == 1) {
                    continue outer;
                }
                System.out.println("\tinner finish.[j=" + j + "]");
            }
            System.out.println("outer finish.[i=" + i + "]");
        }
    }
}
