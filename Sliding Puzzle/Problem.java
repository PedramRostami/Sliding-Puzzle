import java.util.*;

@SuppressWarnings("ALL")
public class Problem {
    private State goalState;
    private LinkedList<State> openListFIFO;
//    private Stack<State> openListFILO;
    private List<State> closeList;
    private List<State> path;
    private Bolt bolt;

    public Problem() {
        goalState = new State();
        goalState.setState(new int[][]{{1, 2, 3} ,{4, 5, 6} ,{7, 8, 0}});
        openListFIFO = new LinkedList<>();
        closeList = new ArrayList<>();
        path = new ArrayList<>();
    }

    public Problem(boolean boltEn) {
        if (boltEn) {
            goalState = new State();
            goalState.setState(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
            path = new ArrayList<>();
            openListFIFO = new LinkedList<>();
            bolt = new Bolt();
        } else {
            goalState = new State();
            goalState.setState(new int[][]{{1, 2, 3} ,{4, 5, 6} ,{7, 8, 0}});
            openListFIFO = new LinkedList<>();
            closeList = new ArrayList<>();
            path = new ArrayList<>();
        }
    }

    public void initialState(State initialState) {
        openListFIFO = new LinkedList<>();
        initialState.setFatherId(0);
        initialState.setDepth(1);
        openListFIFO.push(initialState);
    }

    public boolean goalTest(State state) {
        return state.equals(goalState);
    }

    public List<State> expandFIFO(State state) throws CloneNotSupportedException {
        int zeroX = 0, zeroY = 0;
        State temp;
        List<State> expands = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state.getState()[i][j] == 0) {
                    zeroX = j;
                    zeroY = i;
                }
            }
        }

        List<State> newStates = new ArrayList<>();
        if (zeroX > 0) {
            temp = state.clone();
            temp.getState()[zeroY][zeroX] = temp.getState()[zeroY][zeroX - 1];
            temp.getState()[zeroY][zeroX - 1] = 0;
            newStates.add(temp);
        }
        if (zeroX < 2) {
            temp = state.clone();
            temp.getState()[zeroY][zeroX] = temp.getState()[zeroY][zeroX + 1];
            temp.getState()[zeroY][zeroX + 1] = 0;
            newStates.add(temp);
        }
        if (zeroY > 0) {
            temp = state.clone();
            temp.getState()[zeroY][zeroX] = temp.getState()[zeroY - 1][zeroX];
            temp.getState()[zeroY - 1][zeroX] = 0;
            newStates.add(temp);
        }
        if (zeroY < 2) {
            temp = state.clone();
            temp.getState()[zeroY][zeroX] = temp.getState()[zeroY + 1][zeroX];
            temp.getState()[zeroY + 1][zeroX] = 0;
            newStates.add(temp);
        }

        for (int i = 0; i < newStates.size(); i++) {
            boolean isDuplicate = false;
            for (State item : openListFIFO) {
                if (newStates.get(i).equals(item)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate)
                continue;
            for (State item : closeList) {
                if (newStates.get(i).equals(item)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate)
                expands.add(newStates.get(i));
        }
        return expands;
    }

    public List<State> expandBolt(State state) throws CloneNotSupportedException {
        int zeroX = 0, zeroY = 0;
        State temp;
        List<State> expands = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state.getState()[i][j] == 0) {
                    zeroX = j;
                    zeroY = i;
                }
            }
        }

        List<State> newStates = new ArrayList<>();
        if (zeroX > 0) {
            temp = state.clone();
            temp.getState()[zeroY][zeroX] = temp.getState()[zeroY][zeroX - 1];
            temp.getState()[zeroY][zeroX - 1] = 0;
            newStates.add(temp);
        }
        if (zeroX < 2) {
            temp = state.clone();
            temp.getState()[zeroY][zeroX] = temp.getState()[zeroY][zeroX + 1];
            temp.getState()[zeroY][zeroX + 1] = 0;
            newStates.add(temp);
        }
        if (zeroY > 0) {
            temp = state.clone();
            temp.getState()[zeroY][zeroX] = temp.getState()[zeroY - 1][zeroX];
            temp.getState()[zeroY - 1][zeroX] = 0;
            newStates.add(temp);
        }
        if (zeroY < 2) {
            temp = state.clone();
            temp.getState()[zeroY][zeroX] = temp.getState()[zeroY + 1][zeroX];
            temp.getState()[zeroY + 1][zeroX] = 0;
            newStates.add(temp);
        }

        for (int i = 0; i < newStates.size(); i++) {
            boolean isDuplicate = false;
            List<String> boltOpenList = bolt.getBoltOpenList().get(Bolt.getKey(newStates.get(i)));
            if (boltOpenList != null) {
                for (int j = 0; j < boltOpenList.size(); j++) {
                    if (boltOpenList.get(j).equals(Bolt.getVlaue(newStates.get(i))))
                        isDuplicate = true;
                }
            }
            List<String> boltCloseList = bolt.getBoltCloseList().get(Bolt.getKey(newStates.get(i)));
            if (boltCloseList != null) {
                for (int j = 0; j < boltCloseList.size(); j++) {
                    if (boltCloseList.get(j).equals(Bolt.getVlaue(newStates.get(i))))
                        isDuplicate = true;
                }
            }
            if (!isDuplicate)
                expands.add(newStates.get(i));
        }
        return expands;
    }

    public List<State> backTracking(State state) {
        long fatherId = state.getFatherId();
        List<State> resultPath = new ArrayList<>();
        resultPath.add(state);
        for (int i = path.size() - 1; i >= 0 ; i--) {
            if (path.get(i).getId() == fatherId) {
                resultPath.add(path.get(i));
                fatherId = path.get(i).getFatherId();
            }
        }
        return resultPath;
    }

    public double h(State state) {
        double result = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result += calDistance(i, j, state.getState()[i][j]);
            }
        }
        return result;
    }

    private double calDistance(int i, int j, int num) {
        switch (num) {
            case 0:
                return Math.sqrt(Math.pow(2 - i, 2) + Math.pow(2 - j, 2));
            case 1:
                return Math.sqrt(Math.pow(0 - i, 2) + Math.pow(0 - j, 2));
            case 2:
                return Math.sqrt(Math.pow(0 - i, 2) + Math.pow(1 - j, 2));
            case 3:
                return Math.sqrt(Math.pow(0 - i, 2) + Math.pow(2 - j, 2));
            case 4:
                return Math.sqrt(Math.pow(1 - i, 2) + Math.pow(0 - j, 2));
            case 5:
                return Math.sqrt(Math.pow(1 - i, 2) + Math.pow(1 - j, 2));
            case 6:
                return Math.sqrt(Math.pow(1 - i, 2) + Math.pow(2 - j, 2));
            case 7:
                return Math.sqrt(Math.pow(2 - i, 2) + Math.pow(0 - j, 2));
            case 8:
                return Math.sqrt(Math.pow(2 - i, 2) + Math.pow(1 - j, 2));
                default:
                    return 0;
        }
    }

    public State getGoalState() {
        return goalState;
    }

    public void setGoalState(State goalState) {
        this.goalState = goalState;
    }

    public LinkedList<State> getOpenListFIFO() {
        return openListFIFO;
    }

    public void setOpenListFIFO(LinkedList<State> openListFIFO) {
        this.openListFIFO = openListFIFO;
    }

    public List<State> getCloseList() {
        return closeList;
    }

    public void setCloseList(List<State> closeList) {
        this.closeList = closeList;
    }

    public List<State> getPath() {
        return path;
    }

    public void setPath(List<State> path) {
        this.path = path;
    }

    public Bolt getBolt() {
        return bolt;
    }

    public void setBolt(Bolt bolt) {
        this.bolt = bolt;
    }
}
