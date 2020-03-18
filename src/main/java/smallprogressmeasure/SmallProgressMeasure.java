package smallprogressmeasure;

import paritygame.ParityGame;
import paritygame.ParityNode;

import java.util.Arrays;

public class SmallProgressMeasure {

    PriorityInformation M = null;

    PriorityInformation[] parityProgress = null;

    ParityGame game = null;

    SmallProgressMeasure(ParityGame game){

        this.game = game;

        M = new PriorityInformation(game.getHighestPriority());

        for(int i = 0; i< game.getHighestNodeIdentifier() + 1; i++)
        {
            ParityNode node = game.getNode(i);

            if(node.isPriorityOdd()) { //increase count on odd priorities
                M.increaseCountPriority(node.getPriority());
            }
        }

        parityProgress = new PriorityInformation[game.getHighestNodeIdentifier() + 1];

        for(int i = 0; i< game.getHighestNodeIdentifier() + 1; i++)
        {
            parityProgress[i] = new PriorityInformation(game.getHighestPriority());
        }
    }

    void calculateNaive(){

        boolean somethingLifted = true;

        while(somethingLifted)
        {
            somethingLifted = false;

            for(int i = 0; i< parityProgress.length; i++) {

                PriorityInformation current = parityProgress[i];

                if (!current.isMaxed())
                {
                    lift(i);

                    while (!current.equals(parityProgress[i])&&!current.isMaxed()){
                        current = parityProgress[i];
                        lift(i);
                        somethingLifted = true;
                    }
                }
            }
        }
    }

    void lift(int identifier){

        ParityNode node = game.getNode(identifier);

        int[] successors = node.getSuccessors();

        PriorityInformation info = parityProgress[successors[0]]
                    .progressMeasure(M, node.getPriority(), node.isPriorityOdd());

        if(!node.isOwnerOdd()) {

            for (int i = 1; i < successors.length; i++) {

                PriorityInformation smallProgress = parityProgress[successors[i]]
                            .progressMeasure(M,node.getPriority(),node.isPriorityOdd());



                info = info.getSmallest(smallProgress);
            }
        }
        else {

            for (int i = 1; i < successors.length; i++) {

                PriorityInformation smallProgress = parityProgress[successors[i]]
                        .progressMeasure(M,node.getPriority(),node.isPriorityOdd());

                info = info.getBiggest(smallProgress);

            }
        }
        parityProgress[identifier] = info;
    }


    void printResults(){
        for(int i = 0; i< parityProgress.length; i++)
        {
            System.out.println("Identifier: " + i + " OddWins: " +parityProgress[i].isMaxed());
        }
    }

    String getOddWins(){

        boolean[] oddWins = new boolean[parityProgress.length];

        for(int i = 0; i< parityProgress.length; i++)
        {
            oddWins[i] = parityProgress[i].isMaxed();
        }
        return  Arrays.toString(oddWins);
    }

    void print()
    {
        for(int i = 0; i< parityProgress.length; i++) {
            System.out.println("Identifer: " + i + " Found: " + parityProgress[i] );
        }
    }
}
