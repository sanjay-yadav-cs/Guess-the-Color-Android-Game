package com.rjcollege.gusessthecolor;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "mycolors")
public class MyColor {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "colorName")
    private  String colorName;

    @ColumnInfo(name = "colorCode")
    private String colorCode;

    public MyColor(int id, String colorName, String colorCode) {
        this.id = id;
        this.colorName = colorName;
        this.colorCode = colorCode;
    }

    @Ignore
    public MyColor(String colorName, String colorCode) {
        this.colorName = colorName;
        this.colorCode = colorCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
