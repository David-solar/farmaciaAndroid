package com.topicos.farmacia.farmacia;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Medicamentos extends AppCompatActivity {
    String[] info;
    ListView lis;

    protected android.view.ActionMode mActionMode;
    protected int selectedIt = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mostrar();
        lis = (ListView) findViewById(R.id.lve_e);
        lis.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        lis.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(mActionMode != null)
                {
                    return false;
                }
                selectedIt = position;

                mActionMode = Medicamentos.this.startActionMode(amc);
                view.setSelected(true);
                return true;

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    public void mostrar()
    {
        BaseHelper bh = new BaseHelper(this, "farmacia",null,1);
        SQLiteDatabase db = bh.getWritableDatabase();

        if(db != null)
        {

            Cursor c = db.rawQuery("select * from Medicamentos", null);
            int cantidad = c.getCount();

            info = new String[cantidad];
            int i = 0;

            if(c.moveToFirst())
            {
                do
                {
                    String linea =
                            "Id: " + c.getInt(0) + "\n" +
                                    "Nombre: " + c.getString(1) + "\n" +
                                    "Valor de Compra: " + c.getDouble(2) + "\n" +
                                    "Valor de Venta: " + c.getDouble(3) + "\n" +
                                    "Cantidad: " + c.getInt(4) + "\n" +
                                    "Descripcion: " + c.getString(5);
                    info[i] = linea;
                    i++;

                }while(c.moveToNext());
            }

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, info);
            ListView lista = (ListView) findViewById(R.id.lve_e);

            lista.setAdapter(adaptador);

        }

    }


    private android.view.ActionMode.Callback amc = new android.view.ActionMode.Callback()
    {

        @Override
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu)
        {

            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_m2, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item)
        {

            switch (item.getItemId())
            {
                case R.id.action_borrar:
                    Borrar();
                    mode.finish();
                    return true;


                default:
                    return false;

            }

        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode mode)
        {

            mActionMode = null;
            selectedIt = -1;

        }
    };


    private void Borrar()
    {
        BaseHelper bh = new BaseHelper(this, "farmacia",null,1);
        SQLiteDatabase db = bh.getWritableDatabase();

        Cursor c1 = db.rawQuery("select id from Medicamentos", null);

        int id = 0;
        if(c1.moveToFirst())
        {
            for(int x = 0; x < (selectedIt+1); x++)
            {
                id = c1.getInt(0);
                c1.moveToNext();
            }
        }


        /*if (c1.moveToFirst())
        {
            //Recorremos el cursor hasta que no haya más registros
            do
            {
                id = c1.getInt(0);
            } while(c1.moveToNext());
        }*/



        if(db != null)
        {
            long res = db.delete("Medicamentos", "id=" + id, null);

            if(res > 0)
            {
                Toast.makeText(this, "Registro Eliminado", Toast.LENGTH_SHORT).show();
                mostrar();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medicamento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id)
        {
            case R.id.nuevo:
                Intent in_mod = new Intent(this, AltasMedicamento.class);
                Toast.makeText(getApplicationContext(), "Item Altas Medicamamento", Toast.LENGTH_SHORT).show();
                startActivity(in_mod);
                break;

            case R.id.modificar:
                //Intent in_mod1 = new Intent(this, AltasMedicamento.class);
                Toast.makeText(getApplicationContext(), "Disponible en version 2", Toast.LENGTH_SHORT).show();
                //startActivity(in_mod1);
                break;

            case R.id.inicio:
                Intent in_mod3 = new Intent(this, Farmacia.class);
                Toast.makeText(getApplicationContext(), "Item Inicio", Toast.LENGTH_SHORT).show();
                startActivity(in_mod3);
                break;

            case R.id.actualizar:
                mostrar();
                break;



        }

        return super.onOptionsItemSelected(item);
    }



}
