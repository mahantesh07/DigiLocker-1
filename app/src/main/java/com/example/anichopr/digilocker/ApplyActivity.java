package com.example.anichopr.digilocker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ApplyActivity extends AppCompatActivity {

    String[] itemname ={
            "Apply",
            "Renew",
            "Update Details",
    };
    private void savePreferences(String key, String value) {

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();

    }

    private String loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

       return sharedPreferences.getString("name", "YourName");
    }
    public void HideActionBarLogo() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent in=getIntent();
        String name=in.getStringExtra("name");

        if (name==null)
            name=loadSavedPreferences();
        else
            savePreferences("name",name);

        getSupportActionBar().setTitle( name);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.blue));
        getSupportActionBar().setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        HideActionBarLogo();
        ListView listview = (ListView) findViewById(R.id.apply_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.how_to_item_layout, R.id.textView, itemname);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent searchIntent = new Intent(ApplyActivity.this, DocRequirementActivity.class)
                        .putExtra("name", loadSavedPreferences());
                startActivity(searchIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_apply, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
