package paritygame;

public class ParityGame {
    //A game has a array of nodes and value indicating the highest priority that is found in system.
    //It is saved so we do not look through the entire system when we need it.

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

        //Keep track of maximum, so it only has to done once
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
        //We print in the same structure as the supplied files.
        //Now we can test parser easy by comparing printed structure
        //and original file.

        String completeString = "parity " + (nodes.length - 1) + ";\n";

        for(int i = 0; i< nodes.length ; i++)
        {
            completeString += nodes[i].toString();
        }

        return completeString;
    }
}
