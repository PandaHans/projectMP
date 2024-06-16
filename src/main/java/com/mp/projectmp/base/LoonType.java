package com.mp.projectmp.base;

import java.io.Serial;
import java.io.Serializable;

public class LoonType implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

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
