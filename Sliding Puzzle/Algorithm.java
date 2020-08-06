import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Algorithm {

    public Output bfs(State initialState) {
        Problem problem = new Problem();
        if (problem.goalTest(initialState)) {
            problem.getPath().add(initialState);
            Output output = new Output();
            output.setAnswer(problem.getPath());
            output.setCloseListSize(0);
            output.setOpenListSize(1);
            output.setAnswerDepth(0);
            return output;
        }
        problem.initialState(initialState);
        while (true) {
            State currentState = problem.getOpenListFIFO().pop();
            try {
                List<State> neighbourStates = problem.expandFIFO(currentState);
                problem.getPath().add(currentState);
                boolean isGoal = false;

                for (int i = 0; i < neighbourStates.size(); i++) {
                    neighbourStates.get(i).setFatherId(currentState.getId());
                    if (problem.goalTest(neighbourStates.get(i))) {
                        problem.getCloseList().add(currentState);
                        problem.getPath().add(neighbourStates.get(i));
                        isGoal = true;
                        break;
                    }
                }
                if (isGoal) {
                    Output output = new Output();
                    output.setOpenListSize(problem.getOpenListFIFO().size());
                    output.setCloseListSize(problem.getCloseList().size());
                    output.setAnswer(problem.backTracking(problem.getPath().get(problem.getPath().size() - 1)));
                    output.setAnswerDepth(output.getAnswer().size());
                    return output;
                }
                if (!isGoal) {
                    for (int i = 0; i < neighbourStates.size(); i++)
                        problem.getOpenListFIFO().add(neighbourStates.get(i));
                    problem.getCloseList().add(currentState);
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        }
    }

    public Output boltBFS(State initialState) {
        Problem problem = new Problem(true);
        if (problem.goalTest(initialState)) {
            problem.getPath().add(initialState);
            Output output = new Output();
            output.setOpenListSize(problem.getBolt().getBoltOpenList().size());
            output.setCloseListSize(problem.getBolt().getBoltCloseList().size());
            output.setTotalNodesSize(output.getCloseListSize() + output.getOpenListSize());
            output.setAnswer(problem.getPath());
            output.setAnswerDepth(output.getAnswer().size());
            return output;
        }
        problem.initialState(initialState);
        problem.getBolt().addToBoltOpenList(initialState);
        while (true) {
            State currentState = new State();
            try {
                currentState = problem.getOpenListFIFO().pop();
            } catch (Exception e) {
                e.printStackTrace();
            }
            problem.getBolt().deleteFromBoltOpenList(currentState);
            try {
                if (currentState.getState()[0][0] == 1 && currentState.getState()[0][1] == 2 && currentState.getState()[0][2] == 3
                && currentState.getState()[1][0] == 4 && currentState.getState()[1][1] == 5 && currentState.getState()[1][2] == 6) {
                    System.out.println("HERE");
                }
                List<State> neighbourStates = problem.expandBolt(currentState);
                problem.getPath().add(currentState);
                boolean isGoal = false;

                for (int i = 0; i < neighbourStates.size(); i++) {
                    neighbourStates.get(i).setFatherId(currentState.getId());
                    if (problem.goalTest(neighbourStates.get(i))) {
//                        problem.getCloseList().add(currentState);
                        problem.getBolt().addToBoltCloseList(currentState);
                        problem.getPath().add(neighbourStates.get(i));
                        isGoal = true;
                        break;
                    }
                }
                if (isGoal) {
                    Output output = new Output();
                    output.setOpenListSize(problem.getBolt().getBoltOpenList().size());
                    output.setCloseListSize(problem.getBolt().getBoltCloseList().size());
                    output.setTotalNodesSize(output.getCloseListSize() + output.getOpenListSize());
                    output.setAnswer(problem.backTracking(problem.getPath().get(problem.getPath().size() - 1)));
                    output.setAnswerDepth(output.getAnswer().size());
                    return output;

                }
                else {
                    for (int i = 0; i < neighbourStates.size(); i++) {
                        problem.getOpenListFIFO().add(neighbourStates.get(i));
                        problem.getBolt().addToBoltOpenList(neighbourStates.get(i));
                    }
                    currentState.setExpandStatesCount(neighbourStates.size());
//                    problem.getCloseList().add(currentState);
                    problem.getBolt().addToBoltCloseList(currentState);
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        }
    }

    public Output dfs(State initialState) {
        Problem problem = new Problem();
        if (problem.goalTest(initialState)) {
            problem.getPath().add(initialState);
            Output output = new Output();
            output.setCloseListSize(problem.getCloseList().size());
            output.setOpenListSize(problem.getOpenListFIFO().size());
            output.setAnswer(problem.getPath());
            output.setAnswerDepth(1);
            return output;
        }
        problem.initialState(initialState);
        while (true) {
            State currentState = problem.getOpenListFIFO().pop();
            try {
                List<State> neighbourStates = problem.expandFIFO(currentState);
                problem.getPath().add(currentState);
                boolean isGoal = false;

                for (int i = 0; i < neighbourStates.size(); i++) {
                    neighbourStates.get(i).setFatherId(currentState.getId());
                    if (problem.goalTest(neighbourStates.get(i))) {
                        problem.getCloseList().add(currentState);
                        problem.getPath().add(neighbourStates.get(i));
                        isGoal = true;
                        break;
                    }
                }
                if (isGoal) {
                    Output output = new Output();
                    output.setOpenListSize(problem.getOpenListFIFO().size());
                    output.setCloseListSize(problem.getCloseList().size());
                    output.setAnswer(problem.backTracking(problem.getPath().get(problem.getPath().size() - 1)));
                    output.setAnswerDepth(output.getAnswer().size());
                    return output;
                }
                if (!isGoal) {
                    for (int i = 0; i < neighbourStates.size(); i++)
                        problem.getOpenListFIFO().push(neighbourStates.get(i));
                    problem.getCloseList().add(currentState);
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        }
    }

    public Output boltDFS(State initialState) {
        Problem problem = new Problem(true);
        if (problem.goalTest(initialState)) {
            problem.getPath().add(initialState);
            Output output = new Output();
            output.setOpenListSize(problem.getBolt().getBoltOpenList().size());
            output.setCloseListSize(problem.getBolt().getBoltCloseList().size());
            output.setTotalNodesSize(output.getCloseListSize() + output.getOpenListSize());
            output.setAnswer(problem.getPath());
            output.setAnswerDepth(output.getAnswer().size());
            return output;
        }
        problem.initialState(initialState);
        problem.getBolt().addToBoltOpenList(initialState);
        State currentState = new State();
        while (true) {
            try {
                currentState = problem.getOpenListFIFO().pop();
            } catch (Exception e) {
                int i = problem.getBolt().getBoltCloseList().size();
                Set<String> keySets = problem.getBolt().getBoltCloseList().keySet();
                for (String key :
                        keySets) {
                    List<String> states = problem.getBolt().getBoltCloseList().get(key);

                }
                int nodeSize = 0;
//                for (List<State> states :
//                        problem.getBolt().getBoltCloseList().) {
//
//                }
                e.printStackTrace();

            }
            problem.getBolt().deleteFromBoltOpenList(currentState);
            try {
                List<State> neighbourStates = problem.expandBolt(currentState);
                problem.getPath().add(currentState);
                boolean isGoal = false;
                int goalIndex = 0;
                for (int i = 0; i < neighbourStates.size(); i++) {
                    neighbourStates.get(i).setFatherId(currentState.getId());
                    if (problem.goalTest(neighbourStates.get(i))) {
//                        problem.getCloseList().add(currentState);
                        problem.getBolt().addToBoltCloseList(currentState);
                        problem.getPath().add(neighbourStates.get(i));
                        goalIndex = i;
                        isGoal = true;
                        break;
                    }
                }
                if (isGoal) {
                    Output output = new Output();
                    output.setOpenListSize(problem.getBolt().getBoltOpenList().size());
                    output.setCloseListSize(problem.getBolt().getBoltCloseList().size());
                    output.setTotalNodesSize(output.getCloseListSize() + output.getOpenListSize());
                    output.setAnswer(problem.backTracking(neighbourStates.get(goalIndex)));
                    output.setAnswerDepth(output.getAnswer().size());
                    return output;
                }
                if (!isGoal) {
                    for (int i = 0; i < neighbourStates.size(); i++) {
                        problem.getOpenListFIFO().push(neighbourStates.get(i));
                        problem.getBolt().addToBoltOpenList(neighbourStates.get(i));
                    }
//                    problem.getCloseList().add(currentState);
                    problem.getBolt().addToBoltCloseList(currentState);
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        }


    }

    public Output depthLimitedSearch(State initialState, int l) {
        Problem problem = new Problem();
        if (problem.goalTest(initialState)) {
            problem.getPath().add(initialState);
            Output output = new Output();
            output.setCloseListSize(problem.getCloseList().size());
            output.setOpenListSize(problem.getOpenListFIFO().size());
            output.setAnswer(problem.getPath());
            output.setAnswerDepth(output.getAnswer().size());
            return output;
        }
        problem.initialState(initialState);
        while (true) {
            if (problem.getOpenListFIFO().isEmpty()) {
                Output output = new Output();
                output.setCloseListSize(problem.getCloseList().size());
                output.setOpenListSize(problem.getOpenListFIFO().size());
                output.setAnswer(null);
                return output;
            }
            State currentState = problem.getOpenListFIFO().pop();
            if (currentState.getDepth() < l) {
                try {
                    List<State> neighbourStates = problem.expandFIFO(currentState);

                    problem.getPath().add(currentState);
                    boolean isGoal = false;

                    for (int i = 0; i < neighbourStates.size(); i++) {
                        neighbourStates.get(i).setFatherId(currentState.getId());
                        if (problem.goalTest(neighbourStates.get(i))) {
                            problem.getCloseList().add(currentState);
                            neighbourStates.get(i).setFatherId(currentState.getId());
                            problem.getPath().add(neighbourStates.get(i));

                            Output output = new Output();
                            output.setOpenListSize(problem.getOpenListFIFO().size());
                            output.setCloseListSize(problem.getCloseList().size());
                            output.setTotalNodesSize(output.getCloseListSize() + output.getOpenListSize() + i + 1);
                            output.setAnswer(problem.backTracking(problem.getPath().get(problem.getPath().size() - 1)));
                            output.setAnswerDepth(output.getAnswer().size());
                            return output;
                        }
                    }
                    for (int i = 0; i < neighbourStates.size(); i++) {
                        neighbourStates.get(i).setDepth(currentState.getDepth() + 1);
                        problem.getOpenListFIFO().push(neighbourStates.get(i));
                    }
                    problem.getCloseList().add(currentState);

                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public Output iterativeDeepeningDFS(State initialState) {
        int l = 1;
        ArrayList<Output> outputs = new ArrayList<>();
        while (true) {
            Output output = depthLimitedSearch(initialState, l);
            outputs.add(output);
            if (output.getAnswer() != null) {
                Output finalOutput = new Output();
                finalOutput.setAnswer(output.getAnswer());
                finalOutput.setAnswerDepth(output.getAnswerDepth());
                finalOutput.setCloseListSize(output.getCloseListSize());
                finalOutput.setOpenListSize(output.getOpenListSize());
                finalOutput.setTotalNodesSize(0);
                for (int i = 0; i < outputs.size(); i++) {
                    finalOutput.setTotalNodesSize(finalOutput.getTotalNodesSize() + outputs.get(i).getOpenListSize() + outputs.get(i).getCloseListSize());
                }
                return finalOutput;
            }
            l++;
        }
    }

    public Output bidirectionalSearch(State initialState, State goalState) {
        Problem problem1 = new Problem();
        if (problem1.goalTest(initialState)) {
            problem1.getPath().add(initialState);
            Output output = new Output();
            output.setOpenListSize(problem1.getOpenListFIFO().size());
            output.setCloseListSize(problem1.getCloseList().size());
            output.setAnswer(problem1.getPath());
            output.setAnswerDepth(output.getAnswer().size());
            output.setTotalNodesSize(output.getCloseListSize() + output.getOpenListSize());
            return output;
        }
        problem1.initialState(initialState);
        Problem problem2 = new Problem();
        problem2.initialState(goalState);
        int counter = 0;
        int l1Counter = 1, l2Counter = 1;
        while (true) {
            if (counter % 2 == 0) {
                int tmp = 0;
                for (int i = 0; i < l1Counter; i++) {
                    State currentState = problem1.getOpenListFIFO().pop();
                    try {
                        List<State> neighbourStates = problem1.expandFIFO(currentState);
                        tmp += neighbourStates.size();
                        for (int j = 0; j < neighbourStates.size(); j++) {
                            neighbourStates.get(j).setFatherId(currentState.getId());
                            problem1.getOpenListFIFO().add(neighbourStates.get(j));
                        }
                        problem1.getPath().add(currentState);
                        problem1.getCloseList().add(currentState);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
                for (State item1 : problem1.getOpenListFIFO()) {
                    for (State item2 : problem2.getOpenListFIFO()) {
                        if (item1.equals(item2)) {
                            List<State> result = new ArrayList<>();
                            List<State> resultPath1 = problem1.backTracking(item1);
                            for (int i = resultPath1.size() - 1; i >= 0; i--) {
                                result.add(resultPath1.get(i));
                            }
                            List<State> resultPath2 = problem2.backTracking(item2);
                            for (int i = 1; i < resultPath1.size() - 1 ; i++) {
                                result.add(resultPath2.get(i));
                            }
                            Output output = new Output();
                            output.setCloseListSize(problem1.getCloseList().size() + problem2.getCloseList().size());
                            output.setOpenListSize(problem1.getOpenListFIFO().size() + problem2.getOpenListFIFO().size());
                            output.setAnswer(result);
                            output.setAnswerDepth(output.getAnswer().size());
                            output.setTotalNodesSize(output.getCloseListSize() + output.getOpenListSize());
                            return output;
                        }
                    }
                }
                l1Counter = tmp;
            } else {
                int tmp = 0;
                for (int i = 0; i < l2Counter; i++) {
                    State currentState = problem2.getOpenListFIFO().pop();
                    try {
                        List<State> neighbourStates = problem2.expandFIFO(currentState);
                        tmp += neighbourStates.size();
                        for (int j = 0; j < neighbourStates.size(); j++) {
                            neighbourStates.get(j).setFatherId(currentState.getId());
                            problem2.getOpenListFIFO().add(neighbourStates.get(j));
                        }
                        problem2.getPath().add(currentState);
                        problem2.getCloseList().add(currentState);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
                for (State item1 : problem1.getOpenListFIFO()) {
                    for (State item2 : problem2.getOpenListFIFO()) {
                        if (item1.equals(item2)) {
                            List<State> result = new ArrayList<>();
                            List<State> resultPath1 = problem1.backTracking(item1);
                            for (int i = resultPath1.size() - 1; i >= 0; i--) {
                                result.add(resultPath1.get(i));
                            }
                            List<State> resultPath2 = problem2.backTracking(item2);
                            for (int i = 1; i < resultPath1.size() - 1 ; i++) {
                                result.add(resultPath2.get(i));
                            }
                            Output output = new Output();
                            output.setCloseListSize(problem1.getCloseList().size() + problem2.getCloseList().size());
                            output.setOpenListSize(problem1.getOpenListFIFO().size() + problem2.getOpenListFIFO().size());
                            output.setAnswer(result);
                            output.setAnswerDepth(output.getAnswer().size());
                            output.setTotalNodesSize(output.getCloseListSize() + output.getOpenListSize());
                            return output;
                        }
                    }
                }
                l2Counter = tmp;
            }
            counter++;
        }

    }

    public Output uniformCost(State initialState) {
        LinkedList<Integer> cost = new LinkedList<>();
        Problem problem = new Problem();
        if (problem.goalTest(initialState)) {
            problem.getPath().add(initialState);
            Output output = new Output();
            output.setAnswer(problem.getPath());
            output.setOpenListSize(problem.getOpenListFIFO().size());
            output.setCloseListSize(problem.getCloseList().size());
            output.setTotalNodesSize(output.getCloseListSize() + output.getOpenListSize());
            output.setAnswerDepth(output.getAnswer().size());
            return output;
        }
        problem.initialState(initialState);
        cost.add(1);
        while (true) {
            int minCostIndex = 0;
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < cost.size(); i++) {
                if (cost.get(i) < minCost) {
                    minCost = cost.get(i);
                    minCostIndex = i;
                }
            }
            State currentState = problem.getOpenListFIFO().get(minCostIndex);
            problem.getCloseList().add(currentState);
            problem.getOpenListFIFO().remove(minCostIndex);
            cost.remove(minCostIndex);
            if (!problem.goalTest(currentState)) {
                try {
                    List<State> neighbourStates = problem.expandFIFO(currentState);
                    problem.getPath().add(currentState);
                    for (int i = 0; i < neighbourStates.size(); i++) {
                        neighbourStates.get(i).setFatherId(currentState.getId());
                        problem.getOpenListFIFO().add(neighbourStates.get(i));
                        cost.add(minCost + 1);
                    }
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

            } else {
                Output output = new Output();
                output.setAnswer(problem.backTracking(currentState));
                output.setCloseListSize(problem.getCloseList().size());
                output.setOpenListSize(problem.getOpenListFIFO().size());
                output.setTotalNodesSize(output.getCloseListSize() + output.getOpenListSize());
                output.setAnswerDepth(output.getAnswer().size());
                return output;
            }
        }
    }

    public Output AStar(State initialState) {
        LinkedList<Double> cost = new LinkedList<>();
        LinkedList<Double> heuristic = new LinkedList<>();
        Problem problem = new Problem();
        if (problem.goalTest(initialState)) {
            problem.getPath().add(initialState);
            Output output = new Output();
            output.setAnswer(problem.getPath());
            output.setOpenListSize(problem.getOpenListFIFO().size());
            output.setCloseListSize(problem.getCloseList().size());
            output.setTotalNodesSize(output.getCloseListSize() + output.getOpenListSize());
            output.setAnswerDepth(output.getAnswer().size());
            return output;
        }
        problem.initialState(initialState);
        cost.add(1d);
        heuristic.add(problem.h(initialState));
        while (true) {
            int minIndex = 0;
            double min = Double.MAX_VALUE;
            double minCost = 0;
            for (int i = 0; i < cost.size(); i++) {
                if (cost.get(i) + heuristic.get(i) < min) {
                    min = cost.get(i) + heuristic.get(i);
                    minIndex = i;
                    minCost = cost.get(i);
                }
            }
            State currentState = problem.getOpenListFIFO().get(minIndex);
            problem.getOpenListFIFO().remove(minIndex);
            cost.remove(minIndex);
            heuristic.remove(minIndex);
            if (!problem.goalTest(currentState)) {
                problem.getPath().add(currentState);
                try {
                    List<State> neighbourStates = problem.expandFIFO(currentState);
                    for (int i = 0; i < neighbourStates.size(); i++) {
                        neighbourStates.get(i).setFatherId(currentState.getId());
                        cost.add(minCost + 1);
                        heuristic.add(problem.h(neighbourStates.get(i)));
                        problem.getOpenListFIFO().add(neighbourStates.get(i));
                    }
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            } else {
                Output output = new Output();
                output.setAnswer(problem.backTracking(currentState));
                output.setCloseListSize(problem.getCloseList().size());
                output.setOpenListSize(problem.getOpenListFIFO().size());
                output.setAnswerDepth(output.getAnswer().size());
                output.setTotalNodesSize(output.getCloseListSize() + output.getOpenListSize());
                return output;
            }

        }
    }


}
