package paritygame;

import java.util.ArrayList;

import static helperfunctions.ArrayHelper.listToArray;

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
    
    public void calculatePredecessors()
    {
        ArrayList<Integer>[] predecessorList = new ArrayList[nodes.length];

        //initialize lists
        for (int i = 0; i < predecessorList.length; i++) {
            predecessorList[i] = new ArrayList<Integer>();
        }

        for(int i = 0;i<nodes.length;i++)
        {
            int[] successors = nodes[i].getSuccessors();

            for(int j = 0; j< successors.length; j++)
            {
                int successor = successors[j];

                predecessorList[successor].add(i); //add node to list of predesessor of its successor
            }
        }

        //save found values
        for(int i = 0; i<nodes.length;i++)
        {
            nodes[i].setPredecessors(listToArray(predecessorList[i]));
        }

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

        String completeString = "parity " + (nodes.length - 1) + ";" + System.lineSeparator();

        for(int i = 0; i< nodes.length ; i++)
        {
            completeString += nodes[i].toString();
        }

        return completeString;
    }
}
