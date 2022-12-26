import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String initialState = "";
        int selection, inv;

        do {
            System.out.println("(1) Generate random Puzzle");
            System.out.println("(2) Input manual Puzzle");
            System.out.println("(-1) Quit");

            selection = scan.nextInt();

            switch (selection) {
                case 1:
                    initialState = Utils.generatePuzzle();
                    break;
                case 2:
                    initialState = Utils.getUserInput(scan);
                    break;
                case -1:
                    return;
            }

            System.out.println("\nStarting to solve...");

            inv = Utils.getInversions(initialState);
            if (inv % 2 == 1) {
                System.out.println("\n(!) Error: Puzzle is not solvable! Aborting...");
                selection = -1;
            } else {
                Node start = new Node(initialState, null, null, 0);
                ASearch searchH1 = new ASearch(start, true);
                ASearch searchH2 = new ASearch(start, false);


                long begin = System.currentTimeMillis();
                Node goal1 = searchH1.start();
                long end = System.currentTimeMillis();
                long totalFirst = end - begin;

                begin = System.currentTimeMillis();
                Node goal2 = searchH2.start();
                end = System.currentTimeMillis();
                long totalSecond = end - begin;

                System.out.println("Solved Using H1\nDepth: " + goal1.getCost() 
                + " - Search Cost: " + searchH1.getSearchCost()
                + " - Total Time: " + totalFirst + " ms");

                System.out.println("Solved Using H2\nDepth: " + goal2.getCost() 
                + " - Search Cost: " + searchH2.getSearchCost()
                + " - Total Time: " + totalSecond + " ms"+"\n");


            }



        } while (selection != -1);

        scan.close();

        
    }
    
}
