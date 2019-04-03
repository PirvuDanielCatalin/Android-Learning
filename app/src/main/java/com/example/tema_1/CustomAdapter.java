package com.example.tema_1;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_RED = 0;
    private final int TYPE_BLUE = 1;

    //List of elements to load in your list
    private ArrayList<String> items;
    //The context where adapter is used
    private Context context;

    //Init the list of elements in constructor
    public CustomAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0){
            return TYPE_RED;
        }

        return TYPE_BLUE;
    }

    // Inflate a layout from XML and returning the holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {

            case TYPE_RED:
                View redView = inflater.inflate(R.layout.itemrecyclerviewred,parent, false);

                // Return a new holder instance
                RecyclerView.ViewHolder viewHolderRed = new CustomAdapter.ViewHolderRed(redView);
                return viewHolderRed;

            case TYPE_BLUE:
                View blueView = inflater.inflate(R.layout.itemrecyclerviewblue, parent, false);

                // Return a new holder instance
                RecyclerView.ViewHolder viewHolderBlue = new CustomAdapter.ViewHolderBlue(blueView);
                return viewHolderBlue;

        }

        return null;
    }

    // Populate data into the item through holder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        String item = items.get(position);

        // Set item views based on your views and data model

        switch (getItemViewType(position)) {
            case TYPE_RED:
                TextView textViewRed = ((ViewHolderRed)viewHolder).nameTextView;
                textViewRed.setText(item);
                break;

            case TYPE_BLUE:
                TextView textViewBlue = ((ViewHolderBlue)viewHolder).nameTextView;
                textViewBlue.setText(item);
                break;
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolderRed extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolderRed(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.recycle_item);
        }
    }

    public class ViewHolderBlue extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolderBlue(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.recycle_item);
        }
    }

}
