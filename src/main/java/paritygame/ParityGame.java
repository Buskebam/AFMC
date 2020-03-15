package paritygame;

public class ParityGame {

    ParityNode[] nodes = null;

    ParityGame(int highestNodeIdentifier)
    {
        nodes = new ParityNode[highestNodeIdentifier+1];
    }

    ParityNode getnode(int identifier)
    {
        return nodes[identifier];
    }

    void setnode(ParityNode node)
    {
        nodes[node.identifier] = node;
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
