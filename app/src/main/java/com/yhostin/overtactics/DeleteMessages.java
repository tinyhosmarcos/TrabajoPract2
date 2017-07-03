package com.yhostin.overtactics;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class DeleteMessages extends AppCompatActivity {
    Button delete_button;
    EditText id_delete;
    private DBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_messages);
        delete_button=(Button)findViewById(R.id.delete_button);
        id_delete=(EditText)findViewById(R.id.id_delete);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=new DBAdapter(getApplicationContext());
                db.open();
                db.deleteMessage(
                        Integer.parseInt(
                                id_delete.getText().toString().trim()
                        )
                );
                db.close();
                Toast.makeText(getApplicationContext(), "Mensaje Eliminado",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
