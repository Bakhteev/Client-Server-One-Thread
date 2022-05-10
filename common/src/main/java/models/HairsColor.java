package models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public enum HairsColor implements Serializable {
    @SerializedName("black")
    BLACK,
    @SerializedName("white")
    WHITE,
    @SerializedName("brown")
    BROWN;

    static public void showColorsList() {
        for (HairsColor color : values()) {
            System.out.println(color);
        }
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
