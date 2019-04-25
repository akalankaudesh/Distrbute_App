package nsbm.plymouth.distributed_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView employee,services,profit,stock,purchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employee=findViewById(R.id.imgemployees);
        services=findViewById(R.id.imgservices);
        profit=findViewById(R.id.imgprofit);
        stock=findViewById(R.id.imgstock);
        purchase=findViewById(R.id.imgpurchases);

        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emp_page=new Intent(MainActivity.this,Employees.class);
                startActivity(emp_page);
            }
        });

        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service_page=new Intent(MainActivity.this,Services.class);
                startActivity(service_page);
            }
        });

        profit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profit_page=new Intent(MainActivity.this,Profit.class);
                startActivity(profit_page);
            }
        });

        stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stock_page=new Intent(MainActivity.this,Stock.class);
                startActivity(stock_page);
            }
        });

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent purchase_page=new Intent(MainActivity.this,Purchase.class);
                startActivity(purchase_page);
            }
        });
    }
}
