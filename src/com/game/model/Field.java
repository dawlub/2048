package com.game.model;

import java.awt.*;

//Class of single tile with specific color dependent from score

public class Field {
    private int value = 0;

    public Field() {
    }
    public Field(int value) {
        this.value = value;
    }
    public boolean isEmpty(){
        return value == 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Color fontColor(){
        if(value <= 8)
            return new Color(0x5B5854);
        return new Color(0xF6F3F3);
    }

    public Color getFieldColor() {
        switch (value) {
            case 0:
                return new Color(0xDCC7AC);
            case 2:
                return new Color(0xD9BC9B);
            case 4:
                return new Color(0xEFCF97);
            case 8:
                return new Color(0xCE9869);
            case 16:
                return new Color(0xF38C57);
            case 32:
                return new Color(0xF56947);
            case 64:
                return new Color(0xD24A2A);
            case 128:
                return new Color(0xE1C265);
            case 256:
                return new Color(0xEECE6A);
            case 512:
                return new Color(0xE8C24B);
            case 1024:
                return new Color(0xF1C636);
            case 2048:
                return new Color(0xF6C51D);

            default:
                return new Color(0xCE2424);
        }
    }
}
