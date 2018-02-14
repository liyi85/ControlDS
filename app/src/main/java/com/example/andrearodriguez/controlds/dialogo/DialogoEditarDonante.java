package com.example.andrearodriguez.controlds.dialogo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andrearodriguez.controlds.DB.BaseDatos;
import com.example.andrearodriguez.controlds.R;
import com.example.andrearodriguez.controlds.model.Donante;

import static com.example.andrearodriguez.controlds.DB.Estructura.EstructuraBase.COLUMNA_LASTNAME;
import static com.example.andrearodriguez.controlds.DB.Estructura.EstructuraBase.COLUMNA_NAME;
import static com.example.andrearodriguez.controlds.DB.Estructura.EstructuraBase.TABLE1_NAME;

/**
 * Created by andrearodriguez on 2/13/18.
 */

public class DialogoEditarDonante extends DialogFragment{

    public static final String TAG = "editar registro";

    BaseDatos basedatos;
    String name, lastname, id, age, tipoS, rhD, estaturaD, pesoD;

    public interface OnEditarDonanteListener{
        void onEditarRegistro(Donante donante);
    }

    OnEditarDonanteListener listener;

    private TextView identificacion;
    private EditText  nombre, apellido, edad, tipoSangre, rh, estatura, peso;
    private Button btnCancelar, btnEditarRegistro;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogEditar = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_editar_donante, null);

        name = getArguments().getString("name");
        lastname = getArguments().getString("lastname");
        id = getArguments().getString("id");
        age = getArguments().getString("edad");
        tipoS = getArguments().getString("tipo");
        rhD = getArguments().getString("rh");
        estaturaD = getArguments().getString("estatura");
        pesoD = getArguments().getString("peso");


        dialogEditar.setView(view);

        init(view);

        nombre.setText(name);
        apellido.setText(lastname);
        identificacion.setText(id);
        identificacion.setEnabled(false);
        edad.setText(age);
        tipoSangre.setText(tipoS);
        rh.setText(rhD);
        estatura.setText(estaturaD);
        peso.setText(pesoD);


        btnEditarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreDonante = nombre.getText().toString();
                String apellidoDonante = apellido.getText().toString();
                String idDonante = identificacion.getText().toString();
                String edadDonante = edad.getText().toString();
                String tipoSangreDonante = tipoSangre.getText().toString();
                String rhDonante = rh.getText().toString();
                String estaturaDonante = estatura.getText().toString();
                String pesoDonante = peso.getText().toString();

                if (nombreDonante.equals("") ||
                        apellidoDonante.equals("") ||
                        idDonante.equals("") ||
                        edadDonante.equals("") ||
                        tipoSangreDonante.equals("") ||
                        rhDonante.equals("") ||
                        estaturaDonante.equals("") ||
                        pesoDonante.equals("")) {
                    Toast.makeText(getActivity(), "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else if (!nombreDonante.equals("") ||
                        !apellidoDonante.equals("") ||
                        !idDonante.equals("") ||
                        !edadDonante.equals("") ||
                        !tipoSangreDonante.equals("") ||
                        !rhDonante.equals("") ||
                        !estaturaDonante.equals("") ||
                        !pesoDonante.equals("")) {

                    basedatos = new BaseDatos(getActivity().getApplicationContext());
                    SQLiteDatabase sq = basedatos.getWritableDatabase();

                    sq.execSQL("UPDATE DONANTES SET NOMBRE=?  WHERE ID=?", new Object[]{
                            nombreDonante, idDonante
                    });

                    Toast.makeText(getActivity(), "Editar", Toast.LENGTH_LONG).show();
                }
                dismiss();
            }

        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        return dialogEditar.create();
    }

    private void init(View view) {
        nombre = (EditText) view.findViewById(R.id.editarDonanteNombre);
        apellido = (EditText) view.findViewById(R.id.editarDonanteApellido);
        identificacion = (TextView) view.findViewById(R.id.editarIdentificacion);
        edad = (EditText) view.findViewById(R.id.editarEdad);
        tipoSangre = (EditText) view.findViewById(R.id.editartipoSangre);
        rh = (EditText) view.findViewById(R.id.editarRh);
        estatura = (EditText) view.findViewById(R.id.editarEstatura);
        peso = (EditText) view.findViewById(R.id.editarPeso);

        btnEditarRegistro = (Button) view.findViewById(R.id.btn_editar);
        btnCancelar = (Button) view.findViewById(R.id.btn_cancelarE);
    }
}
