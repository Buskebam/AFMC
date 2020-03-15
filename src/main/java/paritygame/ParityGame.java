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
}
