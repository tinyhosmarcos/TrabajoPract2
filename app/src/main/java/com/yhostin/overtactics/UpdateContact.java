package com.yhostin.overtactics;

import android.database.Cursor;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
public class UpdateContact extends AppCompatActivity {
    String tag="Prueba";
    EditText name_contact;
    EditText lastnames_contact;
    EditText email_contact;
    Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_update_contact);
        name_contact=(EditText)findViewById(R.id.text_name);
        lastnames_contact=(EditText)findViewById(R.id.text_lastname);
        email_contact=(EditText)findViewById(R.id.text_email);
        ingresar=(Button)findViewById(R.id.boton_add);

        //DB inicial
        final String key_id=(bundle.getString("position","0"));
        DBAdapter db=new DBAdapter(this);
        db.open();
        Log.d("key_id",key_id);
        Cursor cursor=db.getContact(Integer.parseInt(key_id));
        name_contact.setText(cursor.getString(1));
        lastnames_contact.setText(cursor.getString(2));
        email_contact.setText(cursor.getString(3));

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter db=new DBAdapter(getApplicationContext());
                db.open();
                db.updateContact(
                        Integer.parseInt(key_id),
                        name_contact.getText().toString().trim(),
                        lastnames_contact.getText().toString().trim(),
                        email_contact.getText().toString().trim());
                db.close();
                //Fin de ingreso
                finish();
            }
        });

        db.close();
        //end DB
    }
}
