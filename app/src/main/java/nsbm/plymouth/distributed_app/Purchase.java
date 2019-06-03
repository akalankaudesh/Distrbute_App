package nsbm.plymouth.distributed_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class Purchase extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        final TextView itemcode=(TextView)findViewById(R.id.txtitemcode);
        final TextView itemdescription=(TextView)findViewById(R.id.txtdescription);
        final TextView itemunitprice=(TextView)findViewById(R.id.txtunitprice);
        final TextView itemqty=(TextView)findViewById(R.id.txtqty);
        Button btnpurchase=(Button)findViewById(R.id.btnpurchase);

        btnpurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code= String.valueOf(itemcode.getText());
                String description=String.valueOf(itemdescription.getText());
                String unitprice=String.valueOf(itemunitprice.getText());
                String qty=String.valueOf(itemqty.getText());

                double price=Double.parseDouble(unitprice);
                int itqty= Integer.parseInt(qty);

                HashMap data = new HashMap();
                data.put("item_code",code);
                data.put("description",description);
                data.put("unit_price",price);
                data.put("qty",itqty);


                System.out.println(data);


                 purchaseorder(data);
            }
        });


    }


    public void purchaseorder(final HashMap itemdata){

        String url="http://10.0.2.2:3000/api/v1/items";

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(itemdata),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                       // System.out.println(response);
                        Toast.makeText(Purchase.this,"Item Added",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Purchase.this,Stock.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        backup(itemdata);
                        System.out.println(error);
                    }
                }
        ){
        };
       Volley.newRequestQueue(this).add(jsonobj);

    }

    public void backup(HashMap itemdata){
        String url="http://10.0.2.2:4000/api/v1/items";

        JsonObjectRequest jsonobj2 = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(itemdata),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                     //   System.out.println(response);
                        Toast.makeText(Purchase.this,"Item Added",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Purchase.this,Stock.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                }
        ){
        };
        Volley.newRequestQueue(this).add(jsonobj2);
    }
}
