/**
 * Reference: http://www2.cs.tum.edu/projects/cup/index.php
 */
public class Calculator {
    public static void main(String[] argv) throws Exception {
        System.out.println("Please type your arithmetic expression:");
        Parser p = new Parser(new scanner());
        p.parse();
    }
}
