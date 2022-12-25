package org.example;

public interface Clock {
    enum ClH {HOUR, MINUTE, SECUND};

    void SetImage(String path);

    String GetImage();

    void Newtime(int msec) throws TimeExeption;

    String GetName();

    int GetCost();
    void SetTime(ClH clh, int time) throws TimeExeption;


}
