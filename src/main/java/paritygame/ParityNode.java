package paritygame;

public class ParityNode {

    int identifier = 0;
    int priority = 0;
    boolean owner = false;
    int[] successors = null;
    String name = null;

    ParityNode(int identifier, int priority, boolean owner, int[] successors, String name)
    {
        this.identifier = identifier;
        this.priority = priority;
        this.owner = owner;
        this.successors = successors;
        this.name = name;
    }
}
