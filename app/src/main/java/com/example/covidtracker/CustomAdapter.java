package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<state> localDataSet;
    private Context context;
    private TextView textView,textView2,textView3,textView4,textView5,textView6,textView7,textView8;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(ArrayList<state> dataSet, Context context) throws JSONException {
        localDataSet = dataSet;
        this.context = context;
    }



    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.customactivity, viewGroup, false);
        textView = view.findViewById(R.id.textView);
        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);
        textView4 = view.findViewById(R.id.textView4);
        textView5 = view.findViewById(R.id.textView5);
        textView6 = view.findViewById(R.id.textView6);
        textView7 = view.findViewById(R.id.textView7);
        textView8 = view.findViewById(R.id.textView8);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        textView.setText(localDataSet.get(position).region);
        textView2.setText("New Case: "+localDataSet.get(position).newInfected);
        textView3.setText("Active Case: "+localDataSet.get(position).activeCases);
        textView4.setText("New Death: "+localDataSet.get(position).newDeceased);
        textView5.setText("Total Death: "+localDataSet.get(position).deceased);
        textView6.setText("New Recovery: "+localDataSet.get(position).newRecovered);
        textView7.setText("Recovered: "+localDataSet.get(position).recovered);
        textView8.setText("Total Infected: "+localDataSet.get(position).totalInfected);



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}