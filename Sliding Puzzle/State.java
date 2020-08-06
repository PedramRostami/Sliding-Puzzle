public class State {
    private static long indexer = 1;
    private long id;
    private long fatherId;
    private int depth;
    private int[][] state;
    private int expandStatesCount;

    public State() {
        id = State.indexer;
        State.indexer++;
        state = new int[3][3];
        depth = 0;
    }

    @Override
    public boolean equals(Object obj) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] != ((State) obj).state[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected State clone() throws CloneNotSupportedException {
        State state = new State();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state.state[i][j] = this.state[i][j];
            }
        }
        return state;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++)
                result += state[i][j] + " ";
            result += "\n";
        }
        return result;
    }

    public void print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] +" ");
            }
            System.out.println();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFatherId() {
        return fatherId;
    }

    public void setFatherId(long fatherId) {
        this.fatherId = fatherId;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

    public int getExpandStatesCount() {
        return expandStatesCount;
    }

    public void setExpandStatesCount(int expandStatesCount) {
        this.expandStatesCount = expandStatesCount;
    }
}
