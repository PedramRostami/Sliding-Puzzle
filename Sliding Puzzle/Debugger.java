import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Debugger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        State state = new State();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state.getState()[i][j] = scanner.nextInt();
            }
        }
        Problem problem = new Problem(true);
        problem.initialState(state);
        problem.getBolt().addToBoltOpenList(state);
        int expandedSize = 0;
        while (problem.getOpenListFIFO().size() != 0) {
            State current = problem.getOpenListFIFO().pop();
            try {
                problem.getBolt().deleteFromBoltOpenList(current);
                List<State> states = problem.expandBolt(current);
                for (int i = 0; i < states.size(); i++) {
                    problem.getOpenListFIFO().push(states.get(i));
                    problem.getBolt().addToBoltOpenList(states.get(i));
                }
                expandedSize += states.size();
                problem.getBolt().addToBoltCloseList(current);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            } catch (NoSuchElementException e) {
                System.out.println(expandedSize);
                e.printStackTrace();
            }
        }
        System.out.println(expandedSize);

    }
}
