package com.example.farhaapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farhaapplication.Acticites.Products_Activity;
import com.example.farhaapplication.Acticites.UpDateProduct_Activity;
import com.example.farhaapplication.Models.category;
import com.example.farhaapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Category  extends RecyclerView.Adapter<Adapter_Category.ViewHolder>{


    private Context context;
    private List<category> categories;

    private Button up_date_btn,delete_btn,add_btn;

    public Adapter_Category(Context context, List<category> categories) {
        this.context = context;
        this.categories = categories;
    }


    @NonNull
    @Override
    public Adapter_Category.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v= (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.categoey_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Category.ViewHolder holder, int position) {
        category cat = categories.get(position);
        CardView cardView=holder.cardView;
        ImageView image = cardView.findViewById(R.id.imageView);
        Picasso.get().load(cat.getImage_name()).into(image);

        TextView txt = cardView.findViewById(R.id.text);
        txt.setText(cat.getName_cat());
        up_date_btn=cardView.findViewById(R.id.add_btn);
        delete_btn=cardView.findViewById(R.id.delete_btn);


        up_date_btn.setOnClickListener(new View.OnClickListener()
                {

                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(context, UpDateProduct_Activity.class);
                        intent.putExtra("cat",cat.getName_cat());
                        context.startActivity(intent);
                    }
                }
        );


//        delete_btn.setOnClickListener(new View.OnClickListener()
//                                      {
//
//                                           @Override
//                                           public void onClick(View v) {
//                                               Intent intent =new Intent(context, UpDateProduct_Activity.class);
//                                               intent.putExtra("cat",cat.getName_cat());
//                                               context.startActivity(intent);
//                                           }
//                                       }
//        );


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, Products_Activity.class);
                intent.putExtra("cat",cat.getName_cat());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        LinearLayout mainLayout;
        public ViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView= cardView;
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
