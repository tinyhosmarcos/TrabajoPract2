package com.yhostin.overtactics;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

public class AcercaDe extends AppCompatActivity {
    String tag="prueba";
    EditText content_message;
    public String prueba="asd";
    private DBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
        Bundle bundle = getIntent().getExtras();
        //create DB

        //finish create
        final String contacts=(bundle.getString("names_contacts","0. NoName"));
        db=new DBAdapter(this);
        db.open();
        Cursor c=db.getContact(Integer.parseInt(contacts.substring(0,contacts.indexOf("."))));
        setTitle(c.getString(1));
        db.close();
        db=null;
        //Update Contacts
        Button btnUpdate=(Button)findViewById(R.id.update_contact);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.yhostin.overtactics.UpdateContact");
                i.putExtra("position",contacts.substring(0,contacts.indexOf(".")));
                Log.d("Position_start ",contacts.substring(0,contacts.indexOf(".")));
                startActivity(i);
            }
        });

        //End Update

        Button btnDelete = (Button) findViewById(R.id.delete_contact);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=new DBAdapter(getApplicationContext());
                db.open();
                db.deleteContact(Integer.parseInt(contacts.substring(0,contacts.indexOf("."))));
                Log.d(tag,contacts.substring(0,1));
                db.close();
                salir();
            }
        });
        Button btnEnviar=(Button)findViewById(R.id.button_enviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content_message=(EditText)findViewById(R.id.text_message);
                db=new DBAdapter(getApplicationContext());
                db.open();
                db.insertMessage(
                        content_message.getText().toString().trim(),
                        Integer.parseInt(contacts.substring(0,contacts.indexOf(".")))
                );
            }
        });

        Button btnReload=(Button)findViewById(R.id.reload_boot);
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
        Button btnDeleteMessage=(Button)findViewById(R.id.delete_message);
        btnDeleteMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.yhostin.overtactics.DeleteMessages"));
            }
        });
    }

    public void salir() {
        finish();
    }
}