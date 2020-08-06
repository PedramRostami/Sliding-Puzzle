import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Bolt {
    private HashMap<String, List<String>> boltCloseList;
    private HashMap<String, List<String>> boltOpenList;


    public Bolt() {
        boltCloseList = new HashMap<>();
        boltOpenList = new HashMap<>();
    }

    public HashMap<String, List<String>> getBoltCloseList() {
        return boltCloseList;
    }

    public void setBoltCloseList(HashMap<String, List<String>> boltCloseList) {
        this.boltCloseList = boltCloseList;
    }

    public HashMap<String, List<String>> getBoltOpenList() {
        return boltOpenList;
    }

    public void setBoltOpenList(HashMap<String, List<String>> boltOpenList) {
        this.boltOpenList = boltOpenList;
    }

    public void addToBoltOpenList(State state) {
        String key = String.valueOf(state.getState()[0][0]) + String.valueOf(state.getState()[0][1]) + String.valueOf(state.getState()[0][2]);
        if (boltOpenList.get(key) == null)
            boltOpenList.put(key, new ArrayList<>());
        boltOpenList.get(key).add(String.valueOf(state.getState()[1][0]) + String.valueOf(state.getState()[1][1]) + String.valueOf(state.getState()[1][2])
                + String.valueOf(state.getState()[2][0]) + String.valueOf(state.getState()[2][1]) + String.valueOf(state.getState()[2][2]));
    }

    public void addToBoltCloseList(State state) {
        String key = Bolt.getKey(state);
        if (boltCloseList.get(key) == null)
            boltCloseList.put(key, new ArrayList<>());
        boltCloseList.get(key).add(Bolt.getVlaue(state));
    }

    public boolean deleteFromBoltOpenList(State state) {
        try {
            String key = Bolt.getKey(state);
            String value = Bolt.getVlaue(state);
            for (int i = 0; i < boltOpenList.get(key).size(); i++) {
                if (value.equals(boltOpenList.get(key).get(i))) {
                    boltOpenList.get(key).remove(i);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getKey(State state) {
        return String.valueOf(state.getState()[0][0]) + String.valueOf(state.getState()[0][1]) + String.valueOf(state.getState()[0][2]);
    }

    public static String getVlaue(State state) {
        return String.valueOf(state.getState()[1][0]) + String.valueOf(state.getState()[1][1]) + String.valueOf(state.getState()[1][2]) +
            String.valueOf(state.getState()[2][0]) + String.valueOf(state.getState()[2][1]) + String.valueOf(state.getState()[2][2]);
    }

}
