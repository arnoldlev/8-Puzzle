import java.util.*;

public class Utils {

    public static final String GOAL = "012345678";

    public static int getInversions(String state) {
        int[] arr = new int[9];
        for (int i = 0; i < state.length(); i++) {
            arr[i] = state.charAt(i) - '0';
        }

        int inv = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (arr[i] > 0 && arr[j] > 0 && arr[i] > arr[j])
                    inv++;
            }
        }

        return inv;
    }

    public static String generatePuzzle() {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < 9; i++) { 
            a.add(i);
        }       
        Collections.shuffle(a);
        String state = "";
        for (int e : a) {
            state += String.valueOf(e);
        }
        return state;
    }

    public static String getUserInput(Scanner scan) {
        String state = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                state += scan.nextLine();
            }            
        }

        return state.replace(" ", "");
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

    public static int h1(Node n) {
        String s = n.getState();
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0' && s.charAt(i) != GOAL.charAt(i)) {
                c++;
            }
        }
        return c;
    }

    public static int h2(Node n) {
        String s = n.getState();
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int x = s.charAt(i) - '0';
            if (x == i || x == 0) {
                continue;
            }

            int row = x/3, col = x % 3;
            int goalR = i / 3, goalC = i % 3;
            sum += Math.abs(col - goalC) + Math.abs(row - goalR);
        }
        return sum;
    }
    
}
