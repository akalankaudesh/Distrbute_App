package nsbm.plymouth.distributed_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nsbm.plymouth.distributed_app.DATA_ADAPTER.Item_Data_Adapter;
import nsbm.plymouth.distributed_app.DTO.Item;

public class Stock extends AppCompatActivity {

    List <Item> itemList;
    ListView stockview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        stockview=(ListView)findViewById(R.id.lstitems);
        itemList=new ArrayList<>();

        fetchdata();
    }

    public void fetchdata(){

        String lightApi = "http://10.0.2.2:3000/api/v1/items";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, lightApi, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Item> temp=new ArrayList<>();


                try {
                    for (int i = 0; i <response.length() ; i++) {

                        JSONObject Itemdetails = (JSONObject) response.get(i);
                        String item_code = Itemdetails.getString("item_code");
                        String description = Itemdetails.getString("description");
                        Double unit_price = Itemdetails.getDouble("unit_price");
                        int qty = Itemdetails.getInt("qty");
                        Item newitem=new Item(item_code,description,unit_price,qty);
                        temp.add(newitem);
                    }

                    itemList=temp;

                    Item_Data_Adapter item_data_adapter=new Item_Data_Adapter(itemList,getApplicationContext());
                    stockview.setAdapter(item_data_adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                backupstock();
                Log.i("Error",error.toString());
            }

        });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

   public void backupstock(){
       String lightApi = "http://10.0.2.2:4000/api/v1/items";

       JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, lightApi, null, new Response.Listener<JSONArray>() {
           @Override
           public void onResponse(JSONArray response) {
               ArrayList<Item> temp=new ArrayList<>();


               try {
                   for (int i = 0; i <response.length() ; i++) {

                       JSONObject Itemdetails = (JSONObject) response.get(i);
                       String item_code = Itemdetails.getString("item_code");
                       String description = Itemdetails.getString("description");
                       Double unit_price = Itemdetails.getDouble("unit_price");
                       int qty = Itemdetails.getInt("qty");
                       Item newitem=new Item(item_code,description,unit_price,qty);
                       temp.add(newitem);
                   }

                   itemList=temp;

                   Item_Data_Adapter item_data_adapter=new Item_Data_Adapter(itemList,getApplicationContext());
                   stockview.setAdapter(item_data_adapter);

               } catch (JSONException e) {
                   e.printStackTrace();
               }

           }

       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Log.i("Error",error.toString());
           }

       });

       Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

}
