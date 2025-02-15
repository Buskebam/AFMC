package paritygame;

public class ParityNode {

    int identifier = 0;
    int priority = 0;
    boolean ownerOdd = false;
    boolean priorityOdd = false;
    int[] successors = null;
    String name = null;

    int[] predecessors = null;

    public ParityNode(int identifier, int priority, boolean ownerOdd, int[] successors, String name)
    {
        this.identifier = identifier;
        this.priority = priority;
        this.ownerOdd = ownerOdd;
        this.priorityOdd = priority%2 == 1;
        this.successors = successors;
        this.name = name;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isOwnerOdd() {
        return ownerOdd;
    }

    public void setOwnerOdd(boolean ownerOdd) {
        this.ownerOdd = ownerOdd;
    }

    public int[] getSuccessors() {
        return successors;
    }

    public void setSuccessors(int[] successors) {
        this.successors = successors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPriorityOdd() {
        return priorityOdd;
    }

    public void setPriorityOdd(boolean priorityOdd) {
        this.priorityOdd = priorityOdd;
    }

    public int[] getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(int[] predecessors) {
        this.predecessors = predecessors;
    }

    @Override
    public String toString() {
        //We print in the same structure as the supplied files.
        //Now we can test parser easy by comparing printed structure
        //and original file.

        String completeString = identifier + " " + priority + " ";

        if(ownerOdd)
        {
            completeString += "1 ";
        }
        else
        {
            completeString += "0 ";
        }


        for(int i = 0; i< successors.length ; i++)
        {
            completeString += String.valueOf(successors[i]);

            //add , after every successor except the last.
            if(i < successors.length - 1) {
                completeString += ",";
            }
        }
        if(!name.equals(""))
        {
            completeString += " \"" + name +"\"";
        }

        completeString += ';'+ System.lineSeparator();

        return completeString;
    }
}
