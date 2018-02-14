package com.example.andrearodriguez.controlds;


import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.andrearodriguez.controlds.DB.BaseDatos;
import com.example.andrearodriguez.controlds.adapter.DonanteAdapter;
import com.example.andrearodriguez.controlds.dialogo.DialogoEditarDonante;
import com.example.andrearodriguez.controlds.dialogo.DialogoRegistrarDonante;
import com.example.andrearodriguez.controlds.model.Donante;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DonanteAdapter.OnEventDonanteListener{

    RecyclerView recyclerView;
    EditText idBusqueda;
    ImageButton buscar;
    ImageButton limpiar;
    Cursor cursor;

    AlertDialog.Builder eliminarDonante;

    String cadena;
    //private SQLiteDatabase myDatabase;

    BaseDatos myDatabase;

    private List<Donante> donantes;

    private DonanteAdapter donanteAdapter;

    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        idBusqueda = (EditText)findViewById(R.id.edit_id_persona);
        buscar = (ImageButton)findViewById(R.id.buscar);
        limpiar = (ImageButton)findViewById(R.id.limpiar);

        donantes = new ArrayList<>();

        myDatabase = new BaseDatos(getApplicationContext());
        SQLiteDatabase sq = myDatabase.getWritableDatabase();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        donanteAdapter = new DonanteAdapter(donantes);
        donanteAdapter.setOnEventDonanteListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(donanteAdapter);
        donanteAdapter.notifyDataSetChanged();

        updateListDonantes();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogoRegistrarDonante dialogo = new DialogoRegistrarDonante();
                dialogo.show(getSupportFragmentManager(), "DialogFragment");
                donanteAdapter.notifyDataSetChanged();
                updateListDonantes();
                Snackbar.make(view, "Todos los donantes", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        updateListDonantes();

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idBusqueda.setText(null);
                updateListDonantes();
                donanteAdapter.notifyDataSetChanged();
                Snackbar.make(view, "Limpiar busqueda", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateListDonantes();
                donanteAdapter.notifyDataSetChanged();
                Snackbar.make(view, "Resultado busqueda", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    private void updateListDonantes() {

        myDatabase = new BaseDatos(getApplicationContext());
        SQLiteDatabase sq = myDatabase.getWritableDatabase();

        cadena = idBusqueda.getText().toString();

        cursor = sq.rawQuery("SELECT * FROM DONANTES;", null);

        if (cadena != null) {
            if (!cadena.equals("")) {
                cursor = sq.rawQuery("SELECT * FROM DONANTES where ID=?", new String[]{cadena});
            }
        }

        int a = 0;

        donantes.clear();


        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex("ID"));
                String name = cursor.getString(cursor.getColumnIndex("NOMBRE"));
                String lastname = cursor.getString(cursor.getColumnIndex("APELLIDO"));
                String edad = cursor.getString(cursor.getColumnIndex("EDAD"));
                String tipoSangre = cursor.getString(cursor.getColumnIndex("TIPOSANGRE"));
                String rh = cursor.getString(cursor.getColumnIndex("RH"));
                String estatura = cursor.getString(cursor.getColumnIndex("ESTATURA"));
                String peso = cursor.getString(cursor.getColumnIndex("PESO"));
                donantes.add(new Donante(id, name, lastname, edad, tipoSangre, rh, estatura, peso));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }


    @Override
    public void deleteDonante(final int position) {

        eliminarDonante = new AlertDialog.Builder(MainActivity.this);
        eliminarDonante.setTitle("Confirmar");
        eliminarDonante.setMessage("Desea eliminar Donante");
        eliminarDonante.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                donanteEliminado(position);
                Toast.makeText(getApplicationContext(), "Donante eliminado", Toast.LENGTH_LONG).show();
            }
        });

        eliminarDonante.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        eliminarDonante.show();

    }

    private void donanteEliminado(int position) {
        myDatabase = new BaseDatos(getApplicationContext());
        SQLiteDatabase sq = myDatabase.getWritableDatabase();

        Donante donante = donantes.get(position);
        sq.execSQL("DELETE FROM DONANTES WHERE ID=?;", new Object[]{donante.getId()});
        updateListDonantes();
        donanteAdapter.notifyDataSetChanged();
        Log.i(TAG, "deleteDonante: Se ha eliminado el donante con id = " + donante.getId());
    }


    @Override
    public void editDonante(int position) {
        myDatabase = new BaseDatos(getApplicationContext());
        SQLiteDatabase sq = myDatabase.getWritableDatabase();

        Donante donante = donantes.get(position);
        String name = donante.getName().toString();
        String lastname = donante.getLastname().toString();
        String id = donante.getId().toString();
        String edad = donante.getAge().toString();
        String tipo = donante.getTipoSangre().toString();
        String rh = donante.getRh().toString();
        String estatura = donante.getEstatura().toString();
        String peso = donante.getPeso().toString();


        DialogoEditarDonante dialogoEditar = new DialogoEditarDonante();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("lastname", lastname);
        args.putString("id", id);
        args.putString("edad", edad);
        args.putString("tipo", tipo);
        args.putString("rh", rh);
        args.putString("estatura", estatura);
        args.putString("peso", peso);

        dialogoEditar.setArguments(args);
        dialogoEditar.show(getSupportFragmentManager(), "DialogFragment");
        donanteAdapter.notifyDataSetChanged();
        updateListDonantes();

        Toast.makeText(getApplicationContext(), "Editar" + position + name + lastname + id, Toast.LENGTH_LONG).show();
    }
}
