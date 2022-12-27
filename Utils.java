import java.util.*;

public class Utils {

    public static final String GOAL = "012345678";

    public static Node generatePuzzle() {
        Node random = null;
        boolean solveable = false;
        while (!solveable) {
            ArrayList<Integer> a = new ArrayList<>();
            for (int i = 0; i < 9; i++) { 
                a.add(i);
            }       
            Collections.shuffle(a);
            String state = "";
            for (int e : a) {
                state += String.valueOf(e);
            }

            random = new Node(state, null, 0, 0);
            solveable = (random.getInversions() % 2 == 0) ? true : false;
        }
        return random;
    }

    public static Node getUserInput(Scanner scan) {
        String state = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                state += scan.nextLine();
            }            
        }

        return new Node(state.replace(" ", ""), null, 0, 0);
    }

    public static void displayBoard(String s) {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            System.out.print(s.charAt(i) + " ");
            if (i == 2 || i == 5) {
                System.out.println();
            }
        }
        System.out.println();
    }

}
