package com.egk.egk;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.egk.activites.Offer_item_atvty;

public class Offer_list extends AppCompatActivity {


    ListView listView;
    TextView textView;
    String[] festivals = {
            "po",
            "go",
            "Christmas",
            "Eid",
            "Baisakhi",
            "Halloween"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_list);
        listView = (ListView)findViewById(R.id.offer_listView);
        textView = (TextView)findViewById(R.id.otextView);

        final ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.offer_list_item, R.id.otextView, festivals);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(), Offer_item_atvty.class);
                startActivity(i);
                /* appending Happy w
                /* appending Happy with festival name */
              //  String value = "Happy " + adapter.getItem(position);
                /* Display the Toast */
               // Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });
    }
}



