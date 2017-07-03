package com.yhostin.overtactics;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.ArrayList;

public class Fragment1 extends ListFragment {
    ArrayList<String>list_contacts;
    private DBAdapter db;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment1, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new DBAdapter(getActivity());
        db.open();
        // creacion de lista
        list_contacts=new ArrayList<String>();
        Cursor cursor=db.getAllContacts();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                list_contacts.add(cursor.getString(0)+". "+cursor.getString(1));
                cursor.moveToNext();
            }
        }
        db.close();

        //Fin de creacion
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,list_contacts));
        //List
        //end list

    }
    @Override
    public void onResume(){
        super.onResume();
        this.onCreate(null);
    }
    public void onListItemClick(ListView parent, View v, int position, long id)
    {
        Intent i = new Intent("com.yhostin.overtactics.AcercaDe");
        i.putExtra("names_contacts",(list_contacts.get(position)));
        startActivity(i);
    }
}