package nsbm.plymouth.distributed_app.DATA_ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import nsbm.plymouth.distributed_app.DTO.Purchase_Orders;
import nsbm.plymouth.distributed_app.R;

public class Purchase_Data_Adapter extends ArrayAdapter<Purchase_Orders> {

    private List<Purchase_Orders> items;
    private Context context;

    public Purchase_Data_Adapter(List<Purchase_Orders> items, Context context) {
        super(context, R.layout.purchase_items,items);
        this.items = items;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View ListStock=inflater.inflate(R.layout.purchase_items,null,true);

        TextView orderid = ListStock.findViewById(R.id.textorder_id);
        TextView customerid = ListStock.findViewById(R.id.textcustomer_id);
        TextView itemid = ListStock.findViewById(R.id.textitem_id);
        TextView unitprice = ListStock.findViewById(R.id.textunit_price);
        TextView qty = ListStock.findViewById(R.id.textViewqty);

        Purchase_Orders item=items.get(position);

        double price=item.getUnit_price();
        String pri= String.valueOf(price);
        int q=item.getQty();
        String tqty= String.valueOf(q);

        orderid.setText(item.getOrder_id());
        customerid.setText(item.getCustomer_id());
        itemid.setText(item.getItem_id());
        unitprice.setText(pri);
        qty.setText(tqty);

        return ListStock;
    }

}
