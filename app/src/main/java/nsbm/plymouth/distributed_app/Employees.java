package nsbm.plymouth.distributed_app;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nsbm.plymouth.distributed_app.DATA_ADAPTER.Customer_Data_Adapter;
import nsbm.plymouth.distributed_app.DTO.Customer;


public class Employees extends AppCompatActivity {


    ListView customerID;
    SwipeRefreshLayout mySwipeRefreshLayout;
    private List<Customer> customers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        customerID=(ListView)findViewById(R.id.lstcusid);
        fetchdata();


    }


    public void fetchdata(){

        String lightApi = "http://10.0.2.2:3000/api/v1/customers";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, lightApi, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                ArrayList <Customer> temp=new ArrayList<>();


                    try {
                        for (int i = 0; i <response.length() ; i++) {

                            JSONObject customerdetails = (JSONObject) response.get(i);
                            String cusid = customerdetails.getString("cusid");
                            String cusname = customerdetails.getString("cusname");
                            String address = customerdetails.getString("address");
                        Customer customer=new Customer(cusid,cusname,address);
                            temp.add(customer);
                        }

                        customers=temp;

                        Customer_Data_Adapter customer_data_adapter=new Customer_Data_Adapter(customers,getApplicationContext());
                        customerID.setAdapter(customer_data_adapter);

                    } catch (JSONException e) {


                        e.printStackTrace();

                    }
                //System.out.println(customers);
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                backup();
               // Log.i("Error",error.toString());
            }

        });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
//        for (Customer customer:customers) {
//            ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, Integer.parseInt(customer.getCusid()));
//
//        }
//    System.out.println(customers);

    }

public void backup(){
    String lightApi = "http://10.0.2.2:4000/api/v1/customers";

    JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, lightApi, null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            ArrayList <Customer> temp=new ArrayList<>();


            try {
                for (int i = 0; i <response.length() ; i++) {

                    JSONObject customerdetails = (JSONObject) response.get(i);
                    String cusid = customerdetails.getString("cusid");
                    String cusname = customerdetails.getString("cusname");
                    String address = customerdetails.getString("address");
                    Customer customer=new Customer(cusid,cusname,address);
                    temp.add(customer);
                }

                customers=temp;
                System.out.println(customers);
                Customer_Data_Adapter customer_data_adapter=new Customer_Data_Adapter(customers,getApplicationContext());
                customerID.setAdapter(customer_data_adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //System.out.println(customers);
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
