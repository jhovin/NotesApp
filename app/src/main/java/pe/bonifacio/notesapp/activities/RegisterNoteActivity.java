package pe.bonifacio.notesapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.bonifacio.notesapp.R;
import pe.bonifacio.notesapp.models.Nota;
import pe.bonifacio.notesapp.repositories.NotaRepository;

public class RegisterNoteActivity extends AppCompatActivity {

    private EditText titulonameInput;
    private EditText contenidoInput;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_note);

        titulonameInput=findViewById(R.id.tituloname_input);
        contenidoInput=findViewById(R.id.contenido_input);
        saveButton =findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }
    private void save(){
        String tit=titulonameInput.getText().toString() ;
        String cont=contenidoInput.getText().toString() ;

        Nota nota= new Nota();
        nota.setTitulo(tit);
        nota.setContenido(cont);

        NotaRepository.save(nota);
        Toast.makeText(this, "Nota registrada", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
    }
}
