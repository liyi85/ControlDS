package com.example.andrearodriguez.controlds.DB;

import android.provider.BaseColumns;

/**
 * Created by andrearodriguez on 2/6/18.
 */

public class Estructura {

    public Estructura() {
    }

    public static abstract class EstructuraBase implements BaseColumns
    {
        public static final String TABLE1_NAME = "DONANTES";
        public static final String COLUMNA_NAME = "NOMBRE";
        public static final String COLUMNA_LASTNAME = "APELLIDO";
        public static final String COLUMNA_ID = "ID";
        public static final String COLUMNA_EDAD = "EDAD";
        public static final String COLUMNA_TIPOSANGRE = "TIPOSANGRE";
        public static final String COLUMNA_RH = "RH";
        public static final String COLUMNA_ESTATURA = "ESTATURA";
        public static final String COLUMNA_PESO = "PESO";
    }
}
