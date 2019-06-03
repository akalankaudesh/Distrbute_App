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
import nsbm.plymouth.distributed_app.DATA_ADAPTER.Purchase_Data_Adapter;
import nsbm.plymouth.distributed_app.DTO.Item;
import nsbm.plymouth.distributed_app.DTO.Purchase_Orders;

public class Profit extends AppCompatActivity {

    List<Purchase_Orders> itemList;
    ListView stockview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit);

        stockview=(ListView)findViewById(R.id.lstpurchases);
        itemList=new ArrayList<>();

        fetchdata();
    }

    public void fetchdata(){
        System.out.println("workin");
        String lightApi = "http://10.0.2.2:3000/api/v1/orders";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, lightApi, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Purchase_Orders> temp=new ArrayList<>();


                try {
                    for (int i = 0; i <response.length() ; i++) {

                        JSONObject Itemdetails = (JSONObject) response.get(i);

                        String order_id = Itemdetails.getString("order_id");
                        String customer_id = Itemdetails.getString("customer_id");
                        String item_id = Itemdetails.getString("item_id");
                        Double unit_price = Itemdetails.getDouble("unit_price");
                        int qty = Itemdetails.getInt("qty");

                        Purchase_Orders newitem=new Purchase_Orders(order_id,customer_id,item_id,unit_price,qty);
                        temp.add(newitem);
                    }

                    itemList=temp;

                    Purchase_Data_Adapter item_data_adapter=new Purchase_Data_Adapter(itemList,getApplicationContext());
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
        String lightApi = "http://10.0.2.2:4000/api/v1/orders";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, lightApi, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Purchase_Orders> temp=new ArrayList<>();


                try {
                    for (int i = 0; i <response.length() ; i++) {

                        JSONObject Itemdetails = (JSONObject) response.get(i);

                        String order_id = Itemdetails.getString("order_id");
                        String customer_id = Itemdetails.getString("customer_id");
                        String item_id = Itemdetails.getString("item_id");
                        Double unit_price = Itemdetails.getDouble("unit_price");
                        int qty = Itemdetails.getInt("qty");

                        Purchase_Orders newitem=new Purchase_Orders(order_id,customer_id,item_id,unit_price,qty);
                        temp.add(newitem);
                    }

                    itemList=temp;

                    Purchase_Data_Adapter item_data_adapter=new Purchase_Data_Adapter(itemList,getApplicationContext());
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
