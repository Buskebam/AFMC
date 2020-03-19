package smallprogressmeasure;

import java.util.Arrays;

public class PriorityInformation {

    int[] info = null;
    boolean maxed = false;

    PriorityInformation(int maximumPriority)
    {
        info = new int[maximumPriority+1];
    }
    PriorityInformation(int[] info)
    {
        this.info = info;
    }

    PriorityInformation progressMeasure(PriorityInformation M, int priority, boolean isPriorityOdd )
    {
        PriorityInformation leastM = new PriorityInformation(info.length-1);

        if (maxed) {
            //When maxed the least is also maxed.
            leastM.setMaxed(true);
        }
        else
        {
            //We first copy the current info to the least. For the even case we stop here
            //Because this is the smallest case that is equal or greater then the current info.

            for (int i = 1; i <= priority; i += 2) {
                leastM.setCountPriority(i, info[i]);
            }

            if(isPriorityOdd)
            {
                //The odd case still has to be increased before it is valid.

                boolean valueIncreased = false;

                if(!isPriorityOdd)
                {
                    priority--; //we want to start at highest odd number
                }

                //We loop from highest odd priority to the smallest
                for(int i = priority; i >= 1 && !valueIncreased;i-= 2 )
                {

                    int mPrio = M.getCountPriority(i);
                    int infoPrio = info[i];

                    //When values are equal, we need to increase another part because this value
                    //is maxed out now. So we reset to zero and try to increase a number with a lower
                    //priority

                    if(mPrio==infoPrio)
                    {
                        leastM.setCountPriority(i,0);
                    }

                    //When there is still room to increase the value this is done and the boolean is set to
                    //stop looking for a priority to increase.
                    if(mPrio>infoPrio)
                    {
                        leastM.setCountPriority(i,infoPrio+1);
                        valueIncreased = true;
                    }

                }
                //If the value could not be increased at any place we still have to increase it but now to maxed.
                if(!valueIncreased)
                {
                    leastM.setMaxed(true);
                }

            }

        }

        return leastM;
    }

    PriorityInformation getBiggest(PriorityInformation other)
    {
        if(this.maxed)
        {
            return this;
        }
        else if(other.isMaxed())
        {
            return other;
        }
        else
        {
            for(int i = 1; i<this.info.length; i += 2)
            {
                int thisPrio = this.info[i];
                int otherPrio = other.getCountPriority(i);

                if(thisPrio < otherPrio )
                {
                    return other;
                }
                else if(thisPrio > otherPrio)
                {
                    return this;
                }
            }
            return this; // apparently the same size

        }

    }

    PriorityInformation getSmallest(PriorityInformation other)
    {
        if(this.maxed)
        {
            return other;
        }
        else if(other.isMaxed())
        {
            return this;
        }
        else
        {
            for(int i = 1; i<this.info.length; i += 2)
            {
                int thisPrio = this.info[i];
                int otherPrio = other.getCountPriority(i);

                if(thisPrio < otherPrio )
                {
                    return this;
                }
                else if(thisPrio > otherPrio)
                {
                    return other;
                }
            }
            return this; // apparently the same size
        }

    }

    void setCountPriority(int priority, int value)
    {
        info[priority] = value;
    }


    int getCountPriority(int priority)
    {
        //commented out for speed, during testing this message came never up
        /*
        if(maxed)
        {
            System.out.println("WARNING: Priority is read while it is maxed, this value does not represent anything!");
            return Integer.MAX_VALUE;
        }
         */
        return info[priority];
    }


    public void increaseCountPriority(int priority)
    {
        info[priority]++;
    }

    public boolean isMaxed() {
        return maxed;
    }

    public void setMaxed(boolean maxed) {
        this.maxed = maxed;
    }

    public boolean equals(PriorityInformation obj) {

        if(this.isMaxed() == obj.isMaxed())
        {
            if (!this.isMaxed()) {
                for (int i = 1; i < this.info.length; i += 2) {
                    if (this.info[i] != obj.getCountPriority(i)) {
                        return false;
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        if(maxed) {
            return "maxed";
        }
        else
        {
            return Arrays.toString(info);
        }
    }
}
