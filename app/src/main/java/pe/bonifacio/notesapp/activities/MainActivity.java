package pe.bonifacio.notesapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.bonifacio.notesapp.R;
import pe.bonifacio.notesapp.adapters.NotaAdapter;
import pe.bonifacio.notesapp.fragments.HomeFragment;
import pe.bonifacio.notesapp.fragments.NotaFragment;
import pe.bonifacio.notesapp.models.Nota;
import pe.bonifacio.notesapp.models.User;
import pe.bonifacio.notesapp.repositories.NotaRepository;
import pe.bonifacio.notesapp.repositories.UserRepository;

public class MainActivity extends AppCompatActivity {

    private static final int REGISTER_REQUEST_CODE = 100;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sp.getString("username", null);
        User user = UserRepository.findByUsername(username);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NotaAdapter());

        cargarLista();

        FloatingActionButton fab = findViewById(R.id.add_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegister();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_item:
                callLogout();
                break;
            case R.id.logout_item:
                callLogout();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarLista() {
        NotaAdapter adapter = (NotaAdapter) recyclerView.getAdapter();
        List<Nota> notas =NotaRepository.list();
        adapter.setNotas(notas);
        adapter.notifyDataSetChanged();
    }

    private void callRegister() {
        startActivityForResult(new Intent(this, RegisterNoteActivity.class), REGISTER_REQUEST_CODE);
    }
    private void callLogout() {
        // Eliminar el estado islogged de la SP
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().remove("islogged").commit();

        // Finalizamos
        finish();

        // y si se desea redireccionamos al LoginActivity
        ///startActivity(...);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REGISTER_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                cargarLista();
            }
        }
    }

}
