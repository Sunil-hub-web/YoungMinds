package com.egk.egk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.egk.activites.Notification_item_atvty;

public class Notification_list extends AppCompatActivity {
    ListView listView;
    TextView textView;
    String[] festivals = {
            "c",
            "cpp",
            "Christmas",
            "Eid",
            "Baisakhi",
            "Halloween"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        listView = (ListView)findViewById(R.id.not_listView);
        textView = (TextView)findViewById(R.id.n_textView);

        final ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.list_item, R.id.textView, festivals);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Notification_item_atvty.class);
                startActivity(i);
                /* appending Happy with festival name */
               // String value = "Happy " + adapter.getItem(position);
                /* Display the Toast */
              //  Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });
    }
}



