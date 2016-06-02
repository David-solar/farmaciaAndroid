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

public class AltasVentas extends AppCompatActivity implements View.OnClickListener{

    Button guar;
    Button canc;

    EditText cli, fec, tot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas_ventas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        guar = (Button) findViewById(R.id.btn_guardarV);
        canc = (Button) findViewById(R.id.btn_cancelarV);

        cli = (EditText) findViewById(R.id.et_cliente);
        fec = (EditText) findViewById(R.id.et_fecha);
        tot = (EditText) findViewById(R.id.et_total);

        guar.setOnClickListener(this);
        canc.setOnClickListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.btn_guardarV:

                if(!cli.getText().toString().isEmpty())
                {
                    if(!fec.getText().toString().isEmpty())
                    {
                        if(!tot.getText().toString().isEmpty())
                        {

                            insertar();

                            Toast.makeText(getApplicationContext(),"Datos agregados correctamente", Toast.LENGTH_SHORT).show();
                            cli.setText(null);
                            fec.setText(null);
                            tot.setText(null);
                            //System.exit(0);


                        }
                        else
                        {
                            ventanaDialogo("total");
                        }
                    }
                    else
                    {
                        ventanaDialogo("valor de fecha");
                    }
                }
                else
                {
                    ventanaDialogo("cliente");
                }



                break;

            case R.id.btn_cancelar:

                cli.setText(null);
                fec.setText(null);
                tot.setText(null);

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

            registro.put("Cliente", cli.getText().toString());
            registro.put("Fecha", fec.getText().toString());
            registro.put("Total", tot.getText().toString());

            db.insert("Ventas", null, registro);

        }

        db.close();
    }
}
