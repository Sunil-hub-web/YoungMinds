package com.egk.egk;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.egk.activites.Transaction_item_atvty;

public class Transaction extends AppCompatActivity {
    ListView listView;
    TextView textView;
    String[] festivals = {
            "Diwali",
            "Holi",
            "Christmas",
            "Eid",
            "Baisakhi",
            "Halloween"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

       listView = (ListView)findViewById(R.id.transaction_listView);
        textView = (TextView)findViewById(R.id.t_textView);

        final ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.transaction_list_item, R.id.t_textView, festivals);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(), Transaction_item_atvty.class);
                startActivity(i);
                /* appending Happy w
                /* appending Happy with festival name */
                //String value = "Happy " + adapter.getItem(position);
                /* Display the Toast */
               // Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });
    }



}
