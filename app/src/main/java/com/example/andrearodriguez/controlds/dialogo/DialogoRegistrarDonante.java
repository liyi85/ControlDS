package com.example.andrearodriguez.controlds.dialogo;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andrearodriguez.controlds.DB.BaseDatos;
import com.example.andrearodriguez.controlds.R;
import com.example.andrearodriguez.controlds.model.Donante;

/**
 * Created by andrearodriguez on 2/4/18.
 */

public class DialogoRegistrarDonante extends DialogFragment{

    public static final String TAG = "agregar registro";

    BaseDatos basedatos;

    public interface OnAgregarDonanteListener{
        void onAgregarRegistro(Donante donante);
    }

    OnAgregarDonanteListener listener;

    private EditText nombre, apellido, identificacion, edad, tipoSangre, rh, estatura, peso;
    private Button btnCancelar, btnAgregarRegistro;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_donante, null);
        builder.setView(view);

        init(view);

        btnAgregarRegistro.setOnClickListener(new View.OnClickListener() {
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

                    String INSERT_DONANTE = "INSERT INTO DONANTES (ID, NOMBRE, APELLIDO, EDAD, TIPOSANGRE, RH, ESTATURA, PESO) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                    sq.execSQL(INSERT_DONANTE, new Object[]{idDonante, nombreDonante, apellidoDonante, edadDonante, tipoSangreDonante, rhDonante, estaturaDonante, pesoDonante});

                    Toast.makeText(getActivity().getApplicationContext(), "Donante guardado correctamente", Toast.LENGTH_LONG).show();
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

        return builder.create();

    }

    private void init(View view) {
        nombre = (EditText) view.findViewById(R.id.nuevoDonanteNombre);
        apellido = (EditText) view.findViewById(R.id.nuevoDonanteApellido);
        identificacion = (EditText) view.findViewById(R.id.identificacion);
        edad = (EditText) view.findViewById(R.id.edad);
        tipoSangre = (EditText) view.findViewById(R.id.tipoSandre);
        rh = (EditText) view.findViewById(R.id.rh);
        estatura = (EditText) view.findViewById(R.id.estatura);
        peso = (EditText) view.findViewById(R.id.peso);

        btnAgregarRegistro = (Button) view.findViewById(R.id.btn_agregar_registro);
        btnCancelar = (Button) view.findViewById(R.id.btn_cancelar);
    }
}
