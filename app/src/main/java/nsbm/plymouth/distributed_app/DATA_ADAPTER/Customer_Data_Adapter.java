package nsbm.plymouth.distributed_app.DATA_ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import nsbm.plymouth.distributed_app.DTO.Customer;
import nsbm.plymouth.distributed_app.R;

public class Customer_Data_Adapter extends ArrayAdapter<Customer> {

    private List<Customer> customerdata;
    private Context context;

    public Customer_Data_Adapter(List<Customer> customerdata, Context context) {
        super(context, R.layout.list_items, customerdata);
        this.customerdata = customerdata;
        this.context = context;
    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        TextView textViewId = listViewItem.findViewById(R.id.textViewId);
        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewAddress = listViewItem.findViewById(R.id.textViewAddress);

        Customer customer=customerdata.get(position);

        textViewId.setText(customer.getCusid());
        textViewName.setText(customer.getCusname());
        textViewAddress.setText(customer.getAddress());

        return listViewItem;
    }


}
