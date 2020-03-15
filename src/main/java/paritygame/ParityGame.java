package paritygame;

public class ParityGame {

    ParityNode[] nodes = null;

    int highestPriority = 0;

    public ParityGame(int highestNodeIdentifier)
    {
        nodes = new ParityNode[highestNodeIdentifier+1];
    }

    public ParityNode getNode(int identifier)
    {
        return nodes[identifier];
    }

    public void setNode(ParityNode node)
    {
        nodes[node.getIdentifier()] = node;
        highestPriority = Math.max(highestPriority,node.getPriority());
    }

    public int getHighestPriority() {
        return highestPriority;
    }

    public void setHighestPriority(int highestPriority) {
        this.highestPriority = highestPriority;
    }

    public int getHighestNodeIdentifier()
    {
        return nodes.length-1;
    }


    @Override
    public String toString() {

        String completeString = "parity " + (nodes.length - 1) + ";\n";

        for(int i = 0; i< nodes.length ; i++)
        {
            completeString += nodes[i].toString();
        }

        return completeString;
    }
}
