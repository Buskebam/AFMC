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

        for(int i = 0; i< game.getHighestNodeIdentifier() ; i++)
        {
            ParityNode node = game.getNode(i);

            if(node.isPriorityOdd()) { //increase count on odd priorities
                M.increaseCountPriority(node.getPriority());
            }
        }

        parityProgress = new PriorityInformation[game.getHighestNodeIdentifier()];

        for(int i = 0; i< game.getHighestNodeIdentifier(); i++)
        {
            parityProgress[i] = new PriorityInformation(game.getHighestPriority());
        }
    }
    /*
    PriorityInformation[] calculate(){

    }*/

    void lift(int identifier){
        PriorityInformation currentInfo = parityProgress[identifier];

        if(!currentInfo.isMaxed()) {

            ParityNode node = game.getNode(identifier);

            int[] successors = node.getSuccessors();

            if(!node.isOwnerOdd()) {

                PriorityInformation currentSmallest = parityProgress[successors[0]]
                        .progressMeasure(M,node.getPriority(),node.isPriorityOdd());

                for (int i = 1; i < successors.length; i++) {
                    currentSmallest = currentSmallest.getSmallest(
                            parityProgress[successors[i]]
                            .progressMeasure(M,node.getPriority(),node.isPriorityOdd()));

                }
                parityProgress[identifier] = currentSmallest;
            }
            else
            {
                PriorityInformation currentBiggest = parityProgress[successors[0]]
                        .progressMeasure(M,node.getPriority(),node.isPriorityOdd());

                for (int i = 1; i < successors.length; i++) {
                    currentBiggest = currentBiggest.getBiggest(
                            parityProgress[successors[i]]
                            .progressMeasure(M,node.getPriority(),node.isPriorityOdd()));

                }
                parityProgress[identifier] = currentBiggest;

            }
        }

    }
}
