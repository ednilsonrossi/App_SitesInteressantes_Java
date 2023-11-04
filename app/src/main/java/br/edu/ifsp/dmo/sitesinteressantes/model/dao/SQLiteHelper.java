package br.edu.ifsp.dmo.sitesinteressantes.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(@Nullable Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Ativar o uso de foreign key.
        db.execSQL("PRAGMA foreign_keys=ON;");

         // Criar tabelas
        db.execSQL(DatabaseContract.TableTag.CREATE_TABLE);
        db.execSQL(DatabaseContract.TableSite.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
