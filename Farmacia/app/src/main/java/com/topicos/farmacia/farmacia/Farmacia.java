package com.topicos.farmacia.farmacia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Farmacia extends AppCompatActivity implements View.OnClickListener{


    Button med;
    Button emp;
    Button vent;
    Button clasi;
    Button estan;
    Button sal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmacia);
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


        med = (Button) findViewById(R.id.btn_medicamento);
        emp = (Button) findViewById(R.id.btn_empleados);
        vent = (Button) findViewById(R.id.btn_ventas);
        clasi = (Button) findViewById(R.id.btn_clasificacion);
        estan = (Button) findViewById(R.id.btn_estantes);
        sal = (Button) findViewById(R.id.btn_salir);

        med.setOnClickListener(this);
        emp.setOnClickListener(this);
        vent.setOnClickListener(this);
        clasi.setOnClickListener(this);
        estan.setOnClickListener(this);
        sal.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_farmacia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.sobDes:
                Intent mov1 = new Intent(this, SobreDesarrolladores.class);
                startActivity(mov1);
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_medicamento:
                Intent mov1 = new Intent(this, Medicamentos.class);
                startActivity(mov1);
                break;

            case R.id.btn_empleados:
                Toast.makeText(getApplicationContext(),"disponible en version 2", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_ventas:
                Toast.makeText(getApplicationContext(),"disponible en version 2", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_clasificacion:
                Toast.makeText(getApplicationContext(),"disponible en version 2", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_estantes:
                Toast.makeText(getApplicationContext(),"disponible en version 2", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_salir:
                //Toast.makeText(getApplicationContext(),"disponible en version 2", Toast.LENGTH_SHORT).show();

                System.exit(0);
                break;
        }

    }
}
