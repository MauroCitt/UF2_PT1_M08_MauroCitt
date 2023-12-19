package com.example.uf2_pt1_m08_maurocitt;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uf2_pt1_m08_maurocitt.model.Coche;

import java.util.List;

public class RecyclerViewApp extends RecyclerView.Adapter<RecyclerViewApp.ViewHolder> {

    private List<Coche> elements;

    public RecyclerViewApp(List<Coche> elements) {
        this.elements = elements;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewElement = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);
        return new ViewHolder(viewElement);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.getTxtElement().setText("Nombre: "+elements.get(position).getNombre()+
                "\nApellido: "+elements.get(position).getApellido()+"\nTelefono: "+elements.get(position).getTelefono()
                +"\nMarca: "+elements.get(position).getMarca()+"\nModelo: "+elements.get(position).getModelo()
                +"\nMatricula: "+elements.get(position).getMatricula());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtElement;

        public ViewHolder(View itemView) {
            super(itemView);
            //Quan fem click a la llista mostrem l'element
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewHolder.this.eliminaElement(v);
                }
            });

            txtElement = itemView.findViewById(R.id.linea);
        }

        private void eliminaElement(View v) {
            //Al clicar l'element podem modificar o eliminar el registre
            DataManager dm = new DataManager(v.getContext());
            Coche coche = elements.get(getAdapterPosition());
            AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
            alert.setMessage(R.string.borrar);
            alert.setPositiveButton(R.string.si,
                    new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            dm.deleteCoche(coche);
                            elements.remove(coche);
                            // No acaba de funcionar
                            notifyItemRemoved(getAdapterPosition());
                            Toast.makeText(v.getContext(),"Registro eliminado",Toast.LENGTH_SHORT).show();
                        }
                    });
            alert.setNegativeButton(R.string.no,new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(v.getContext(),"Registre no eliminado",Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        }

        public TextView getTxtElement() {
            return txtElement;
        }
    }

}
