package org.example;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.gson.stream.JsonToken.BEGIN_OBJECT;

public class ShopAdapter extends TypeAdapter<Shop>{
    @Override
    public void write(JsonWriter jsonWriter, Shop shop) throws IOException {

    }

    @Override
    public Shop read(JsonReader jsonReader) throws IOException {
        Shop sh = new Shop();



        int sec = 0,min = 0,h = 0,cost = 0;
        String name="",img ="";

        boolean sport = false;
        String tagName = null;

        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            jsonReader.nextName();
            jsonReader.beginArray();

            while (jsonReader.hasNext()) {

                JsonToken newToken = jsonReader.peek();
                System.out.println(newToken);
                if (JsonToken.BEGIN_OBJECT.equals(newToken)) {

                    jsonReader.beginObject();

                } else if (JsonToken.NAME.equals(newToken)) {

                    tagName = jsonReader.nextName();
                    System.out.println(tagName);

                } else if (JsonToken.STRING.equals(newToken)) {
                    String str = jsonReader.nextString();
                    //System.out.println(tagName);
                    System.out.println(str);
                    switch (tagName) {
                        case "_name":
                            name = str;
                            break;
                        case "_img":
                            if (sport) {
                                Clock cl = new SportClock(sec, h, min, cost, name);
                                cl.SetImage(img);
                                sh.Append(cl);

                            } else {
                                Clock cl = new BaseClock(h, min, cost, name);
                                cl.SetImage(img);
                                sh.Append(cl);
                            }
                            jsonReader.endObject();
                            sec = 0;
                            min = 0;
                            h = 0;
                            cost = 0;
                            name = "";
                            img = "";
                            sport = false;
                            img = str;
                            break;
                        case null:
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + tagName);
                    }

                } else if (JsonToken.NUMBER.equals(newToken)) {
                    int value = jsonReader.nextInt();
                    //System.out.println(tagName);
                    //System.out.println(value);
                    //System.out.println(tagName);
                    switch (tagName) {
                        case "_sec":
                            sec = value;
                            sport = true;
                            break;
                        case "_min":
                            min = value;
                            break;
                        case "_h":
                            h = value;
                            break;
                        case "_cost":
                            cost = value;
                            break;
                        case null:
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + tagName);
                    }

                } else if (JsonToken.NULL.equals(newToken)) {

                } else if (JsonToken.END_OBJECT.equals(newToken)) {
                    jsonReader.endObject();


                }
            }
            jsonReader.endArray();
        }
        jsonReader.endObject();

        System.out.println("317541297259845124795412");
        System.out.println(sh.coll);
        return sh;
    }
}
