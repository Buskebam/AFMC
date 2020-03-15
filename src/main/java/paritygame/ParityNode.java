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

    @Override
    public String toString() {

        String completeString = identifier + " " + priority + " ";

        if(owner)
        {
            completeString += 1 + " ";
        }
        else
        {
            completeString += 0 + " ";
        }


        for(int i = 0; i< successors.length ; i++)
        {
            completeString += String.valueOf(successors[i]);

            if(i < successors.length - 1) {
                completeString += ",";
            }
        }
        if(!name.equals(""))
        {
            completeString += " \"" + name +"\"";
        }

        completeString += ";\n";

        return completeString;
    }
}
