package com.example.shoppingcart.viewHolder;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingcart.Interface.ItemClickListener;
import com.example.shoppingcart.R;

public class productViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtProductName, txtProductPrice, txtProductID;
    public ImageView productImage;
    public ItemClickListener listner;

    public productViewHolder(View itemView) {
        super(itemView);

        txtProductName = (TextView) itemView.findViewById(R.id.product_name);
        txtProductPrice = (TextView) itemView.findViewById(R.id.product_price);
        txtProductID = (TextView) itemView.findViewById(R.id.product_id);
        productImage = (ImageView) itemView.findViewById(R.id.product_image);
    }

    public void setItemClickListner(ItemClickListener listner) {
        this.listner = listner;
    }


    @Override
    public void onClick(View view) {
        listner.onClick(view, getAdapterPosition(), false);
    }
}