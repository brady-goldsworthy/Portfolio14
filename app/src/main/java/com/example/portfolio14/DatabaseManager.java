package com.example.portfolio14;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "candyTable" , ID = "id", PRICE = "Price", NAME = "Name";


    //Override the onCreate and onUpdate SQLite function here i think

    public void insert(Candy candy) {
        //GEt the db
        SQLiteDatabase database = getWritableDatabase();

        String sqlInsert = "insert into " + TABLE_NAME + " values( null, '" + candy.getName()
                            + "', " + "'" + candy.getPrice() + "' )";

        database.execSQL(sqlInsert);

        database.close();

    } //end of insert


    public ArrayList<Candy> selectAll() {
        String sqlQuery = "select * from " + TABLE_NAME;

        SQLiteDatabase database = getWritableDatabase();

        Cursor cursor = database.rawQuery(sqlQuery, null);

        ArrayList<Candy> candies  = new ArrayList<>();

        //Loop that transfers info from cursor to arraylist
        while (cursor.moveToNext()) {
            Candy currentCandy = new Candy(Integer.parseInt(cursor.getString(0)),
                                                            cursor.getString(1),
                                                            cursor.getDouble(2));

            //Put candy object into arrayList
            candies.add(currentCandy);
        }

        database.close();

        //return arraylist

        return candies;

    } //end selectAll

    public void deleteByID(int id) {
        String sqlDelete = "DELETE FROM " + TABLE_NAME + "WHERE " + ID + " = " + id;

        SQLiteDatabase database = getWritableDatabase();

        database.execSQL(sqlDelete);

        database.close();

    }

    public void updateByID(int currentId, String newName, double newPrice) {
        SQLiteDatabase database = getWritableDatabase();

        String sqlUpdate = "UPDATE " + TABLE_NAME + " set " + NAME + " = '" + newName + "', "
                                                            + PRICE + " = '" + newPrice + "'" +
                                                            " where " + ID + " = " + currentId;

        database.execSQL(sqlUpdate);
        database.close();
    }

    public Candy selectByID(int currentId) {
        SQLiteDatabase database = getWritableDatabase();

        String sqlSelectCandy = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + currentId;

        Cursor cursor = database.rawQuery(sqlSelectCandy, null);

        Candy candy = null;

        if (cursor.moveToFirst()) {
            candy = new Candy(Integer.parseInt(cursor.getString(0)),
                                                cursor.getString(1),
                                                cursor.getDouble(2));
        }

        database.close();

        return candy;
    }




}
