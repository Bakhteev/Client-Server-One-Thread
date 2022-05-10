package models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public enum EyesColor implements Serializable {
    @SerializedName("green")
    GREEN,
    @SerializedName("blue")
    BLUE,
    @SerializedName("orange")
    ORANGE;

    static public void showColorsList() {
        for (EyesColor color : values()) {
            System.out.println(color);
        }
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }


}
