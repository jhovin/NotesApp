package pe.bonifacio.notesapp.repositories;

import com.orm.SugarRecord;

import java.util.List;

import pe.bonifacio.notesapp.models.Nota;

public class NotaRepository{

    public static List<Nota> list(){

        List<Nota> notas = SugarRecord.listAll(Nota.class);
        return notas;
    }

    public static void save(Nota nota){
        SugarRecord.save(nota);
    }
    public static void delete(Long id){
        Nota nota = SugarRecord.findById(Nota.class, id);
        SugarRecord.delete(nota);
    }

}
