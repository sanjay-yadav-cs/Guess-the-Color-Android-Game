package com.rjcollege.gusessthecolor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface colorsDao {

    @Query("select * from mycolors")
    List<MyColor> getMyColors();

    @Insert
    void insertMyColor(MyColor myColor);

    @Query("delete from mycolors")
    void cleartable();
}
