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
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class AltasMedicamento extends AppCompatActivity implements View.OnClickListener{

    Button guar;
    Button canc;

    EditText nom, vc, vv, cant, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas_medicamento);
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

        nom = (EditText) findViewById(R.id.et_nombre);
        vc = (EditText) findViewById(R.id.et_valorCompra);
        vv = (EditText) findViewById(R.id.et_valorVenta);
        cant = (EditText) findViewById(R.id.et_cantidad);
        desc = (EditText) findViewById(R.id.et_descripcion);


        guar = (Button) findViewById(R.id.btn_guardar);
        canc = (Button) findViewById(R.id.btn_cancelar);

        guar.setOnClickListener(this);
        canc.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.btn_guardar:

                if(!nom.getText().toString().isEmpty())
                {
                    if(!vc.getText().toString().isEmpty())
                    {
                        if(!vv.getText().toString().isEmpty())
                        {
                            if(!cant.getText().toString().isEmpty())
                            {
                                if(!desc.getText().toString().isEmpty())
                                {

                                    insertar();

                                    Toast.makeText(getApplicationContext(),"Datos agregados correctamente", Toast.LENGTH_SHORT).show();
                                    nom.setText(null);
                                    vc.setText(null);
                                    vv.setText(null);
                                    cant.setText(null);
                                    desc.setText(null);
                                    //System.exit(0);
                                }
                                else
                                {
                                    ventanaDialogo("descripcion");
                                }
                            }
                            else
                            {
                                ventanaDialogo("cantidad");
                            }
                        }
                        else
                        {
                            ventanaDialogo("valor de venta");
                        }
                    }
                    else
                    {
                        ventanaDialogo("valor de compra");
                    }
                }
                else
                {
                    ventanaDialogo("nombre");
                }



                break;

            case R.id.btn_cancelar:

                nom.setText(null);
                vc.setText(null);
                vv.setText(null);
                cant.setText(null);
                desc.setText(null);

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

            registro.put("nombre", nom.getText().toString());
            registro.put("ValorCompra", vc.getText().toString());
            registro.put("ValorVenta", vv.getText().toString());
            registro.put("Cantidad", cant.getText().toString());
            registro.put("Descripcion", desc.getText().toString());

            db.insert("Medicamentos", null, registro);

        }

        db.close();
    }

}
