package com.yhostin.overtactics;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBAdapter db = new DBAdapter(this);
        //---add a contact---
        db.open();
        //long id = db.insertContact("Jennifer","Choquehuanca Carrasco","jenniferann@jfdimarzio.com");
        //id = db.insertContact("Oscar Diggs", "oscar@oscardiggs.com");
        //id = db.insertMessage("Este mensaje dice hola",3 );
        //id = db.insertMessage("este es el mensaje 2",4);
        //db.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        createdMenu(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return menuChoices(item);
    }

    private void createdMenu(Menu menu) {
        MenuItem mnu1 = menu.add(0, 0, 0, "Agregar Contacto");
        {
            mnu1.setAlphabeticShortcut('a');
        }
        MenuItem mnu2 = menu.add(0, 1, 1, "Eliminar Contacto");
        {
            mnu2.setAlphabeticShortcut('b');
        }
    }
    private boolean menuChoices(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                startActivity(new Intent("com.yhostin.overtactics.AddContact"));
                return true;
            case 1:
                Toast.makeText(this, "Seleccione un contacto de la lista",
                        Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
    }

    public void Contact(View view) {
        Intent i=new Intent("A");
        startActivity(i);
    }
}