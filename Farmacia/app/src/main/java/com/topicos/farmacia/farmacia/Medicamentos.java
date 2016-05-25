package com.topicos.farmacia.farmacia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Medicamentos extends AppCompatActivity implements View.OnClickListener {

    Button alt;
    Button baj;
    Button cons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);
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

        alt = (Button) findViewById(R.id.btn_altas);
        baj = (Button) findViewById(R.id.btn_bajas);
        cons = (Button) findViewById(R.id.btn_consultas);

        alt.setOnClickListener(this);
        baj.setOnClickListener(this);
        cons.setOnClickListener(this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medicamento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.btn_altas:
                Intent mov1 = new Intent(this, AltasMedicamento.class);
                startActivity(mov1);
                break;

            case R.id.btn_bajas:

                break;

            case R.id.btn_consultas:

                break;
        }

    }
}
