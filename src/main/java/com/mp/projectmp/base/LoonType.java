package com.mp.projectmp.base;

public class LoonType {
    public static final LoonType UURLOON = new LoonType("Uurloon");
    public static final LoonType VASTEPRIJS = new LoonType("Vasteprijs");

    private String type;
    private float loon;

    private LoonType(String type) {
        this.type = type;
    }

    public LoonType(String type, float loon) {
        this.type = type;
        this.loon = loon;
    }
    public String getType(){
        return type;
    }
    public float getLoon(){
        return loon;
    }
}
