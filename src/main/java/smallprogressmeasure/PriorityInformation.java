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
            leastM.setMaxed(true);
        }
        else
        {

            for (int i = 1; i <= priority; i += 2) {
                leastM.setCountPriority(i, info[i]);
            }

            if(isPriorityOdd)
            {

                boolean valueIncreased = false;

                if(!isPriorityOdd)
                {
                    priority--; //we want to start at highest odd number
                }

                for(int i = priority; i >= 1 && !valueIncreased;i-= 2 )
                {

                    int mPrio = M.getCountPriority(i);
                    int infoPrio = info[i];

                    if(mPrio==infoPrio)
                    {
                        leastM.setCountPriority(i,0);
                    }
                    if(mPrio>infoPrio)
                    {
                        leastM.setCountPriority(i,infoPrio+1);
                        valueIncreased = true;
                    }

                }

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
        if(maxed)
        {
            System.out.println("WARNING: Priority is read while it is maxed, this value does not represent anything!");
            return Integer.MAX_VALUE;
        }
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
