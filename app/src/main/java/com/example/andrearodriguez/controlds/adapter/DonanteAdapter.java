package com.example.andrearodriguez.controlds.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.andrearodriguez.controlds.R;
import com.example.andrearodriguez.controlds.model.Donante;

import java.util.List;

/**
 * Created by andrearodriguez on 2/3/18.
 */

public class DonanteAdapter extends RecyclerView.Adapter<DonanteAdapter.DonanteViewHolder> {


    private List<Donante> donantes;

    private OnEventDonanteListener onEventDonanteListener;


    public interface OnEventDonanteListener {
        void editDonante(int position);
        void deleteDonante(int position);
    }


    public class DonanteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombre, apellido, id, edad, tipo, rh, estatura, peso;
        ImageButton eliminar, editar;

        public DonanteViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.txtNombre);
            apellido = (TextView) itemView.findViewById(R.id.txtNombreDonante);
            id = (TextView) itemView.findViewById(R.id.txtIdUsuario);
            edad = (TextView) itemView.findViewById(R.id.txtEdadUsuario);
            tipo = (TextView) itemView.findViewById(R.id.txtTipoSangreUsuario);
            rh = (TextView) itemView.findViewById(R.id.txtRhUsuario);
            estatura = (TextView) itemView.findViewById(R.id.txtEstaturaUsuario);
            peso = (TextView) itemView.findViewById(R.id.txtPesoUsuario);

            eliminar = (ImageButton) itemView.findViewById(R.id.imgEliminar);
            editar = (ImageButton) itemView.findViewById(R.id.imgEditar);

            eliminar.setOnClickListener(this);
            editar.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.imgEliminar:
                    if (onEventDonanteListener != null) {
                        onEventDonanteListener.deleteDonante(getAdapterPosition());
                    }
                    break;

                case R.id.imgEditar:
                    if (onEventDonanteListener != null) {
                        onEventDonanteListener.editDonante(getAdapterPosition());
                    }
                    break;
            }

        }
    }

    public DonanteAdapter(List<Donante> donantes) {
        this.donantes = donantes;
    }


    @Override
    public DonanteAdapter.DonanteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_donante, parent, false);
        return new DonanteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DonanteAdapter.DonanteViewHolder holder, int position) {

        Donante donante = donantes.get(position);
        holder.nombre.setText(donante.getName());
        holder.apellido.setText(donante.getLastname());
        holder.edad.setText(donante.getAge() + " Años");
        holder.tipo.setText(donante.getTipoSangre());
        holder.rh.setText(donante.getRh());
        holder.estatura.setText(donante.getEstatura() + " cm");
        holder.peso.setText(donante.getPeso() + " Kg");
        holder.id.setText(donante.getId());

    }

    @Override
    public int getItemCount() {
        return donantes.size();
    }


    public OnEventDonanteListener getOnEventDonanteListener() {
        return onEventDonanteListener;
    }

    public void setOnEventDonanteListener(OnEventDonanteListener onEventDonanteListener) {
        this.onEventDonanteListener = onEventDonanteListener;
    }

}
