package com.example.shoppingcart;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shoppingcart.Model.cart;
import com.example.shoppingcart.viewHolder.cartViewHolder;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cart_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button placeOrder;
    private TextView totalAmount;

    private int completeTotalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_);

        recyclerView = (RecyclerView) findViewById(R.id.cartActivity_recyclerView);
        placeOrder = (Button) findViewById(R.id.cartActivity_placeOrder);
        totalAmount = (TextView) findViewById(R.id.cartActivity_totalPrice);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cart_Activity.this, "Order Placed Successfully!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(cart_Activity.this, finalpage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<cart> options = new FirebaseRecyclerOptions.Builder<cart>()
                .setQuery(cartListRef.child("Products"), cart.class).build();

        FirebaseRecyclerAdapter<cart, cartViewHolder> adapter = new FirebaseRecyclerAdapter<cart, cartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull cartViewHolder holder, final int position, @NonNull final cart model) {
                holder.txtProductName.setText(model.getPname());
                holder.txtProductPrice.setText("Price : " + model.getPrice());
                holder.txtProductQuantity.setText("Qty : " + model.getQuantity());

                int oneProductTotalPrice = ((Integer.valueOf(model.getPrice()))) * Integer.valueOf(model.getQuantity());
                completeTotalPrice = completeTotalPrice + oneProductTotalPrice;
                totalAmount.setText("Total Price = Rs." + String.valueOf(completeTotalPrice));

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence options[] = new CharSequence[] {
                                "Delete"
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(cart_Activity.this);
                        builder.setTitle("Cart Options:");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if (i == 0){
                                    cartListRef.child("Products").child(model.getPid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(cart_Activity.this, "Item deleted successfully", Toast.LENGTH_SHORT).show();
                                                int oneProductTotalPrice = ((Integer.valueOf(model.getPrice()))) * Integer.valueOf(model.getQuantity());
                                                completeTotalPrice = completeTotalPrice - oneProductTotalPrice;
                                                totalAmount.setText("Total Price = $" + String.valueOf(completeTotalPrice));
                                            }
                                        }
                                    });
                                }
                            }
                        });
                        builder.show();
                    }
                });
            }

            @NonNull
            @Override
            public cartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_items, viewGroup, false);
                cartViewHolder holder = new cartViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
