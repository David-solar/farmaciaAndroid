package com.topicos.farmacia.farmacia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AltasEmpleados extends AppCompatActivity implements View.OnClickListener{

    Button guar;
    Button canc;

    EditText nom, fna, fin, su, pu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas_empleados);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        guar = (Button) findViewById(R.id.btn_guardarE);
        canc = (Button) findViewById(R.id.btn_cancelarE);

        nom = (EditText) findViewById(R.id.et_nombreE);
        fna = (EditText) findViewById(R.id.et_fnE);
        fin = (EditText) findViewById(R.id.et_fiE);
        su = (EditText) findViewById(R.id.et_sueldoE);
        pu = (EditText) findViewById(R.id.et_puestoE);

        guar.setOnClickListener(this);
        canc.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.btn_guardarE:
            case R.id.btn_guardarV:

                if(!nom.getText().toString().isEmpty())
                {
                    if(!fna.getText().toString().isEmpty())
                    {
                        if(!fin.getText().toString().isEmpty())
                        {

                            if(!su.getText().toString().isEmpty())
                            {

                                if(!pu.getText().toString().isEmpty())
                                {

                                    insertar();

                                    Toast.makeText(getApplicationContext(),"Datos agregados correctamente", Toast.LENGTH_SHORT).show();

                                    nom.setText(null);
                                    fna.setText(null);
                                    fin.setText(null);
                                    su.setText(null);
                                    pu.setText(null);

                                }
                                else
                                {
                                    ventanaDialogo("pueso");
                                }

                            }
                            else
                            {
                                ventanaDialogo("sueldo");
                            }

                        }
                        else
                        {
                            ventanaDialogo("fecha de ingreso");
                        }
                    }
                    else
                    {
                        ventanaDialogo("valor de nacimiento");
                    }
                }
                else
                {
                    ventanaDialogo("nombre");
                }



                break;

            case R.id.btn_cancelar:

                nom.setText(null);
                fna.setText(null);
                fin.setText(null);
                su.setText(null);
                pu.setText(null);

                Toast.makeText(getApplicationContext(),"Cancelaste el guardado", Toast.LENGTH_SHORT).show();
                System.exit(0);

                break;
        }

    }

    public void ventanaDialogo(String campo)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alerta");
        builder.setMessage("Ingresa un valor en " + campo );
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //et.setText(null);
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    public void insertar()
    {
        BaseHelper bh = new BaseHelper(this, "farmacia", null, 1);
        SQLiteDatabase db = bh.getWritableDatabase();
        if(db != null)
        {
            ContentValues registro = new ContentValues();

            registro.put("Nombre", nom.getText().toString());
            registro.put("FechaNac", fna.getText().toString());
            registro.put("FechaIngreso", fin.getText().toString());
            registro.put("Sueldo", su.getText().toString());
            registro.put("Puesto", pu.getText().toString());

            db.insert("Empleados", null, registro);

        }

        db.close();
    }
}
