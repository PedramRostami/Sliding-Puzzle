import java.util.List;

public class Output {
    private int totalNodesSize;
    private int openListSize;
    private int closeListSize;
    private int answerDepth;
    private List<State> answer;


    public int getTotalNodesSize() {
        return totalNodesSize;
    }

    public void setTotalNodesSize(int totalNodesSize) {
        this.totalNodesSize = totalNodesSize;
    }

    public int getOpenListSize() {
        return openListSize;
    }

    public void setOpenListSize(int openListSize) {
        this.openListSize = openListSize;
    }

    public int getCloseListSize() {
        return closeListSize;
    }

    public void setCloseListSize(int closeListSize) {
        this.closeListSize = closeListSize;
    }

    public int getAnswerDepth() {
        return answerDepth;
    }

    public void setAnswerDepth(int answerDepth) {
        this.answerDepth = answerDepth;
    }

    public List<State> getAnswer() {
        return answer;
    }

    public void setAnswer(List<State> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        String result = "OpenList Size = " + openListSize + "\n" +
                "Close List Size = " + closeListSize + "\n" +
                "Total Nodes = " + totalNodesSize + "\n" +
                "Answer Depth = " + answerDepth + "\n" +
                "--------------------" + "\n";
        if (answer != null) {
            for (int i = 0; i < answer.size(); i++) {
                result += answer.get(i).toString() + "\n" +
                        "--------------------" + "\n";
            }
        }
        return result;
    }
}
