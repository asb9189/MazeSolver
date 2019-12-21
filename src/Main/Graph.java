package Main;

        import java.util.ArrayList;

public class Graph {

    private MyNode start;
    private MyNode finish;
    private int vertices;
    private ArrayList<MyNode> nodes;

    public Graph(MyNode start, MyNode finish, int vertices, ArrayList<MyNode> nodes) {
        this.start = start;
        this.finish = finish;
        this.vertices = vertices;
        this.nodes = nodes;
    }

    public String toString() {
        String result = "";
        for (MyNode node : nodes) {
            result += node.toString() + "\n";
        }
        return result;
    }

    public MyNode getStart() {
        return start;
    }

    public MyNode getFinish() {
        return finish;
    }

    public int getSize() {
        return vertices;
    }

}
