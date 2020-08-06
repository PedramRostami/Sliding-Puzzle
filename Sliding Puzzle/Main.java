import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        State state = new State();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state.getState()[i][j] = scanner.nextInt();
            }
        }
        Algorithm algorithm = new Algorithm();
//        State goalState = new State();
//        goalState.setState(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
//        Output output = algorithm.bidirectionalSearch(state, goalState);
        Output output = algorithm.AStar(state);
        System.out.println(output.toString());


//        for (int i = 0; i < path.size(); i++) {
//            path.get(i).print();
//            System.out.println("---------------------------");
//        }

    }
}
