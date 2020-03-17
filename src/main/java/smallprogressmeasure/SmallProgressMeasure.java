package smallprogressmeasure;

import paritygame.ParityGame;
import paritygame.ParityNode;

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

    void calculate(){

        boolean somethingLifted = true;

        while(somethingLifted)
        {
            somethingLifted = false;

            for(int i = 0; i< parityProgress.length; i++) {

                PriorityInformation current = parityProgress[i];

                if (!current.isMaxed())
                {
                    lift(i);

                    while (!current.equals(parityProgress[i])){
                        current = parityProgress[i];
                        lift(i);
                        somethingLifted = true;
                    }
                }
            }
        }
    }

    void lift(int identifier){
        PriorityInformation currentInfo = parityProgress[identifier];

        ParityNode node = game.getNode(identifier);

        int[] successors = node.getSuccessors();

        ParityNode firstSuccessor = game.getNode(successors[0]);

        PriorityInformation info = parityProgress[successors[0]]
                    .progressMeasure(M, firstSuccessor.getPriority(), firstSuccessor.isPriorityOdd());

        if(!node.isOwnerOdd()) {

            for (int i = 1; i < successors.length; i++) {

                ParityNode successor = game.getNode(successors[i]);

                PriorityInformation smallProgress = parityProgress[successors[i]]
                            .progressMeasure(M,successor.getPriority(),successor.isPriorityOdd());

                info = info.getSmallest(smallProgress);
            }
        }
        else {

            for (int i = 1; i < successors.length; i++) {

                ParityNode successor = game.getNode(successors[i]);

                PriorityInformation smallProgress = parityProgress[successors[i]]
                        .progressMeasure(M,successor.getPriority(),successor.isPriorityOdd());

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

    void print()
    {
        for(int i = 0; i< parityProgress.length; i++) {
            System.out.println("Identifer: " + i + " Found: " + parityProgress[i] );
        }
    }
}
