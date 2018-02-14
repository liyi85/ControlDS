package com.example.andrearodriguez.controlds.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andrearodriguez on 2/6/18.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private static final String tipo = " TEXT";
    private static final String Coma = ",";

    private static final String Sentencia =
            "CREATE TABLE " + Estructura.EstructuraBase.TABLE1_NAME + " ("
                    + Estructura.EstructuraBase.COLUMNA_ID + " INTEGER PRIMARY KEY, "
                    + Estructura.EstructuraBase.COLUMNA_NAME + tipo + Coma
                    + Estructura.EstructuraBase.COLUMNA_LASTNAME + tipo + Coma
                    + Estructura.EstructuraBase.COLUMNA_EDAD + tipo + Coma
                    + Estructura.EstructuraBase.COLUMNA_TIPOSANGRE + tipo + Coma
                    + Estructura.EstructuraBase.COLUMNA_RH + tipo + Coma
                    + Estructura.EstructuraBase.COLUMNA_ESTATURA + tipo + Coma +
                    Estructura.EstructuraBase.COLUMNA_PESO + tipo + " )";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ListaDonantes.sqlite";
    //si la tabla existe
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Estructura.EstructuraBase.TABLE1_NAME;

    public BaseDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Sentencia);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);

    }
}
