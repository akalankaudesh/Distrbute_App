package nsbm.plymouth.distributed_app.DATA_ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import nsbm.plymouth.distributed_app.DTO.Item;
import nsbm.plymouth.distributed_app.R;

public class Item_Data_Adapter extends ArrayAdapter<Item> {

    private List<Item> items;
    private Context context;

    public Item_Data_Adapter(List<Item> items, Context context) {
        super(context, R.layout.list_stock,items);
        this.items = items;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View ListStock=inflater.inflate(R.layout.list_stock,null,true);

        TextView ItemCode = ListStock.findViewById(R.id.textViewItemCode);
        TextView description = ListStock.findViewById(R.id.textViewdescription);
        TextView unit_price = ListStock.findViewById(R.id.textViewunit_price);
        TextView qty = ListStock.findViewById(R.id.textViewqty);

        Item item=items.get(position);

        double price=item.getUnit_price();
        String pri= String.valueOf(price);
        int q=item.getQty();
        String tqty= String.valueOf(q);

        ItemCode.setText(item.getItem_code());
        description.setText(item.getDescription());
        unit_price.setText(pri);
        qty.setText(tqty);

        return ListStock;
    }
}
