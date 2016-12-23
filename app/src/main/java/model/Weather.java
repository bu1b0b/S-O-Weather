package model;

/**
 * Created by bu1b0b on 22.12.2016.
 */

public class Weather {

    public Place place;
    public String iconData;
    public CurrnetCondition currentCondition = new CurrnetCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Snow snow = new Snow();
    public Clouds clouds = new Clouds();

}
