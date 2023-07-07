package com.example.rrssapp.DataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.rrssapp.DAO.EmpleadoDao;
import com.example.rrssapp.Entities.Empleado;
import com.example.rrssapp.Entities.Empleado;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, exportSchema = false, entities = {Empleado.class})
public abstract class RHDataBase extends RoomDatabase {
    public abstract EmpleadoDao empleadoDao();

    private static volatile RHDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //Generando una sola instancia con el patron singleton
    public static RHDataBase getDataBase(final Context context){
        if(INSTANCE == null){
            synchronized (RHDataBase.class){
                if (INSTANCE == null){
                    Callback callback = new Callback(){
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db){
                            super.onCreate(db);
                            databaseWriteExecutor.execute(() -> {
                                EmpleadoDao dao = INSTANCE.empleadoDao();
                                dao.deleteAll();

                                //Creacion de datos por defecto en mi base de datos
                                dao.insert(new Empleado("0411200017821","Fernando Adalberto Zúniga Herrera",1,"Masculino",20000.00,"Activo"));
                                dao.insert(new Empleado("0411199802365","Miriam Elizabeth Miraflores Castro",1,"Femenino",20000.00,"Activo"));

                            });
                        }
                    };
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),RHDataBase.class,"db_recursos_humanos").addCallback(callback).build();

                }
            }
        }
        return INSTANCE;
    }

}
