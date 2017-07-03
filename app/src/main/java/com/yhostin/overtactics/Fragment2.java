package com.yhostin.overtactics;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment2 extends ListFragment {
    ArrayList<String> list_messages;
    private DBAdapter db;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment2, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle=getActivity().getIntent().getExtras();
        final String id_contact=(bundle.getString("names_contacts","0. NoName"));
        super.onCreate(savedInstanceState);
        db=new DBAdapter(getActivity());;
        //Parametro de comparacion de id de mensaje
        //fin de parametro
        list_messages=new ArrayList<String>();
        db.open();
        Cursor cursor=db.getAllMessages();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Log.d("Funciona please",cursor.getString(3)+"esta en el otro->"+
                        id_contact.substring(0,id_contact.indexOf(".")));
                if(cursor.getString(3).equals(id_contact.substring(0,id_contact.indexOf(".")))){
                    list_messages.add(cursor.getString(0)+"."+cursor.getString(1));}
                cursor.moveToNext();
            }
        }
        db.close();
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, list_messages));
    }

    public void onListItemClick(ListView parent, View v,
                                int position, long id)
    {
        db=new DBAdapter(getActivity());
        db.open();
        String id_position=list_messages.get(position);
        Cursor cursor=db.getMessage(Integer.parseInt(id_position.substring(0,id_position.indexOf("."))));
        Toast.makeText(getActivity(),
                "Enviado el " + cursor.getString(2)+", codigo de Mensaje: "+cursor.getString(0),
                Toast.LENGTH_SHORT).show();
    }
}