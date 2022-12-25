package org.example;
public class BaseClock implements Clock {
    private int _h, _min, _cost;
    private String _name,_img = "C:\\Users\\Стёпа\\IdeaProjects\\untitled\\src\\asserts\\cl0.png";

    @Override
    public void SetImage(String path)
    {
        _img = path;
    }

    @Override
    public String GetImage()
    {
        return _img;
    }

    public BaseClock(int h, int min, int cost, String name) {
        _h = h;
        _min = min;
        _cost = cost;
        _name = name;
    }

    public void Newtime(int msec) throws TimeExeption {
        if (msec / msec / 1000 / 60 / 60 > 12) throw new TimeExeption(msec / msec / 1000 / 60 / 60, "Time > 12h");
        _h = msec / 1000 / 60 / 60;
        _min = msec / 1000 / 60 % 60;
    }

    @Override
    public String GetName() {
        return _name;
    }

    @Override
    public int GetCost() {
        return _cost;
    }


    public void SetTime(ClH clh, int time) throws TimeExeption {
        if (clh == ClH.HOUR) {
            if (time > 12 | time < 0) throw new TimeExeption(time, "Time > 12h");
            else _h = (time + _h) % 12;

        } else if (clh == ClH.MINUTE) {
            if (time > 60 | time < 0) throw new TimeExeption(time, "Time > 60min");
            else _min = (time + _min) % 12;
        } else {
            throw new TimeExeption(time, "Time formate dont support");
        }
    }


    public String toString() {
        return "Cost: " + _cost + "$ Name: " + _name;
    }
}
