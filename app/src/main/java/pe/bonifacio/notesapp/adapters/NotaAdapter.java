package pe.bonifacio.notesapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.bonifacio.notesapp.R;
import pe.bonifacio.notesapp.models.Nota;
import pe.bonifacio.notesapp.repositories.NotaRepository;

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.ViewHolder> {

    private List<Nota> notas;

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public NotaAdapter() {
        notas = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int postition) {
        final Nota nota = this.notas.get(postition);

        viewHolder.titulonameText.setText(nota.getTitulo());
        viewHolder.contenidoText.setText(nota.getContenido());

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotaRepository.delete(nota.getId());
                notas.remove(postition);
                notifyItemRemoved(postition);
                notifyItemRangeChanged(postition, getItemCount());

            }
        });



    }

    @Override
    public int getItemCount() {
        return this.notas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titulonameText;

        TextView contenidoText;

        ImageButton button;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulonameText = itemView.findViewById(R.id.tituloname_text);
            contenidoText = itemView.findViewById(R.id.contenido_text);
            button = itemView.findViewById(R.id.delete_button);

        }
    }

}
