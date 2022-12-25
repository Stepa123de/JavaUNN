package org.example;

import java.util.*;

public class Shop {
    public Collection coll = new ArrayList<Clock>();



    public Shop(){}



    public void AllTime(int time) throws TimeExeption {
        for (Iterator it = coll.iterator(); it.hasNext(); ) {
            Clock cl = (Clock)it.next();
            cl.Newtime(time);

        }
    }

    public HashSet<Clock> ShowClock()
    {

        Set<String> StNames = new HashSet<>();
        HashSet<Clock> StClock = new HashSet<>();

        String name;

        for (Iterator it = coll.iterator(); it.hasNext(); )
        {

            Clock cl = (Clock) it.next();
            name = cl.GetName();

            if (!(StNames.contains(name)))
            {
                StClock.add(cl);
                StNames.add(name);
            }


        }

        //Arrays.sort(StNames.toArray());

        return StClock;
    }

    public Clock MaxCost()
    {
        Clock maxclock = null;
        int maxc = -1;
        maxclock = Collections.max(coll, new Comparator<Clock>() {

            @Override
            public int compare(Clock o1, Clock o2) {

                return o1.GetCost() - o2.GetCost();
            }
        });


        return maxclock;
    }

    public Clock OfthenClock()
    {

        ArrayList<Clock> arr = new ArrayList<>(coll);
        int indexmax=0, maxcount=0,count;
        for (int i = 0; i < arr.size(); i++)
        {
            count = 1;
            for (int j = i+1;j < arr.size(); j++)
            {
                if(arr.get(i).GetName() == arr.get(j).GetName())
                {
                    count++;
                }
            }
            if(count> maxcount)
            {
                maxcount = count;
                indexmax = i;
            }
        }
        return arr.get(indexmax);
    }

    public void Append(Clock cl)
    {
        coll.add(cl);
    }

    public void buy(Clock cl)
    {
        coll.remove(cl);

    }

}
