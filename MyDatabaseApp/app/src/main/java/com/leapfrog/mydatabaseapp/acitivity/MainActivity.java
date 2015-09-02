package com.leapfrog.mydatabaseapp.acitivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.leapfrog.mydatabaseapp.R;
import com.leapfrog.mydatabaseapp.dao.GreetingDAO;
import com.leapfrog.mydatabaseapp.database.GreetingDatabaseAdapter;
import com.leapfrog.mydatabaseapp.entity.Greeting;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView tvKeyId, tvId, tvContent;
    private Context context;
    private RequestQueue requestQueue;
    private ProgressDialog progressDialog;
    private GreetingDAO greetingDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        tvKeyId = (TextView)findViewById(R.id.tvKeyId);
        tvId = (TextView)findViewById(R.id.tvId);
        tvContent = (TextView)findViewById(R.id.tvContent);
        requestQueue = new Volley().newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "http://rest-service.guides.spring.io/greeting", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                greetingDAO = new GreetingDatabaseAdapter(context);

                Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                try {
                    int id = response.getInt("id");
                    String content = response.getString("content");
                    Greeting greeting = new Greeting(id, content);
                    if(greetingDAO.insert(greeting)>=1){
                        Toast.makeText(context, "data inserted", Toast.LENGTH_LONG).show();

                        Greeting g = greetingDAO.getById(1);
                        tvKeyId.setText(""+g.getKeyId());
                        tvId.setText(""+g.getId());
                        tvContent.setText(g.getContent());
                    }else{
                        Toast.makeText(context, "Not inserted", Toast.LENGTH_LONG).show();
                    }

                    progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

        requestQueue.add(jsonObjectRequest);
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Loading..");
        progressDialog.setMessage("Fetching content...");
        progressDialog.setCancelable(false);
        progressDialog.show();




    }

    private void save(String id, String content){
        SharedPreferences sharedPreferences = context.getSharedPreferences("myDatabaseApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", id);
        editor.putString("content", content);
        editor.commit();
    }

    public String read(String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("myDatabaseApp", Context.MODE_PRIVATE);
       return sharedPreferences.getString(value, "");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
