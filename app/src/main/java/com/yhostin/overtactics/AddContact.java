package com.yhostin.overtactics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
public class AddContact extends AppCompatActivity {
    String tag="Prueba";
    EditText name_contact;
    EditText lastnames_contact;
    EditText email_contact;
    Button ingresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        //Llenar campos
        name_contact=(EditText)findViewById(R.id.text_name);
        lastnames_contact=(EditText)findViewById(R.id.text_lastname);
        email_contact=(EditText)findViewById(R.id.text_email);
        ingresar=(Button)findViewById(R.id.boton_add);
        // fin campos
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ingresar usuario
                DBAdapter db=new DBAdapter(getApplicationContext());
                db.open();
                db.insertContact(name_contact.getText().toString().trim(),
                        lastnames_contact.getText().toString().trim(),
                        email_contact.getText().toString().trim());
                db.close();
                //Fin de ingreso
                finish();
                }
        });
    }

}

