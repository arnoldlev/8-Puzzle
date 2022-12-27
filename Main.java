import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Node initial = null;
        int selection;
        char heu = '1';

        do {
            System.out.println("(1) Generate random Puzzle");
            System.out.println("(2) Input manual Puzzle");
            System.out.println("(3) SPECIAL CASE: Test 100 Cases from File");
            System.out.println("(-1) Quit");
            selection = scan.nextInt();

            if (selection == -1) return;

            if (selection != 3) {
                System.out.println("(1) Use Heuristic 1");
                System.out.println("(2) Use Heuristic 2");
                heu = scan.next().charAt(0);

                if (heu != '1' && heu != '2') {
                    System.out.println("Invalid Input.. Going with Heuristic 1..");
                    heu = '1';
                }
            }

            switch (selection) {
                case 1:
                    initial = Utils.generatePuzzle();
                    solve(initial, (heu == '1') ? 1 : 2);
                    break;
                case 2:
                    System.out.println("Enter manual puzzle using 3 lines with spaces:");
                    initial = Utils.getUserInput(scan);

                    if (initial.getInversions() % 2 == -1) {
                        System.out.println("(!) ERROR: This Puzzle is not Solvable :( Aborting..");
                        return;
                    }

                    solve(initial, (heu == '1') ? 1 : 2);
                    break;
                case 3:
                    testRun();
                    break;
                case -1:
                    return;
            }
        } while (selection != -1);

        scan.close();
        
    }

    public static void solve(Node initial, int type) {
        ASearch search = new ASearch(initial, (type == 1) ? true : false);

        long start = System.currentTimeMillis();
        Node goal = search.start(true);
        long end = System.currentTimeMillis();

        System.out.println("Solution Stats using Heuristic " + type);
        System.out.println( "\tDepth: " + goal.getDepth() + "\n" +
                            "\tSearch Cost: " + search.getSearchCost() + "\n" +
                            "\tDuration: " + (end - start) + " ms\n");


    }

    public static void testRun() throws IOException {
        ArrayList<Node> cases = new ArrayList<>();
        File f = new File("test.txt");
        if (f.exists()) {
            Scanner input = new Scanner(f);
            while (input.hasNext()) {
                cases.add(new Node(input.nextLine(), null, 0, 0));
            }
            input.close();
        }

        HashMap<Integer, ArrayList<Stats>> data = new HashMap<>();
        for (Node e : cases) {
            ASearch s1 = new ASearch(e, true);
            ASearch s2 = new ASearch(e, false);

            long start = System.currentTimeMillis();
            Node goal = s1.start(false);
            long end = System.currentTimeMillis();

            long start2 = System.currentTimeMillis();
            Node goal2 = s2.start(false);
            long end2 = System.currentTimeMillis();

            Stats s = new Stats(goal.getDepth(), s1.getSearchCost(), s2.getSearchCost(), (end - start), (end2 - start2));
            if (!data.containsKey(goal2.getDepth())) {
                data.put(goal2.getDepth(), new ArrayList<>());
            }
            data.get(goal2.getDepth()).add(s);
        }

        System.out.println("# Of Cases | Depth | A*(H1) | A*(H2) | Time of H1 | Time of H2");
        data.entrySet().stream().forEach(e -> {
            int depth = e.getKey(), size = e.getValue().size();
            int s1Cost = 0, s2Cost = 0;
            double s1Duration = 0, s2Duration = 0;
            for (int i = 0; i < size; i++) {
                Stats each = e.getValue().get(i);
                s1Cost += each.getH1Cost();
                s2Cost += each.getH2Cost();
                s1Duration += each.getH1Time();
                s2Duration += each.getH2Time();
            }
            s1Cost/= size; 
            s2Cost /= size;
            s1Duration /= size;
            s2Duration /= size;

            System.out.println(size + " | " + depth + " | " +  s1Cost + " | " + s2Cost + " | " + s1Duration + " | " + s2Duration);
        });
        
    }


    
}
