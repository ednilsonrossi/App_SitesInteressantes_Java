package br.edu.ifsp.dmo.sitesinteressantes.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Classe de exemplo, sem utilidado para o projeto.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "aula.db";
    private static final int VERSION = 3;

    private static final String SQL_CREATE_TABLE_V1 = "CREATE TABLE Exemplo (texto TEXT)";
    private static final String SQL_CREATE_TABLE_V2 = "CREATE TABLE Exemplo (texto TEXT, numero INTEGER)";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE Exemplo (id INTEGER PRIMARY KEY, " +
            "texto TEXT, numero INTEGER)";

    public static final String SQL_UPGRADE_V1_TO_V2 = "ALTER TABLE Exemplo ADD COLUMN numero INTEGER DEFAULT -1";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        switch (oldVersion){
            case 1:
                sqLiteDatabase.execSQL(SQL_UPGRADE_V1_TO_V2);

            case 2:
                String sql = "ALTER TABLE Exemplo RENAME TO Exemplo_old";
                sqLiteDatabase.execSQL(sql);

                sqLiteDatabase.execSQL(SQL_CREATE_TABLE);

                sql = "INSERT INTO Exemplo (texto, numero) SELECT texto, numero FROM Exemplo_old";
                sqLiteDatabase.execSQL(sql);

                sql = "DROP TABLE Exemplo_old";
                sqLiteDatabase.execSQL(sql);
        }
    }
}
