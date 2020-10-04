package com.example.linky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewActivity extends AppCompatActivity {


    private String str="";
    private DatabaseReference userRef;
    private RecyclerView requestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        userRef= FirebaseDatabase.getInstance().getReference().child("Requests");
        requestList = findViewById(R.id.req_helps_recyclerView);
        requestList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Requests> options = null;
        if (str.equals("")) {
            options = new FirebaseRecyclerOptions.Builder<Requests>().setQuery(userRef, Requests.class).build();
        } else {
            options = new FirebaseRecyclerOptions.Builder<Requests>().setQuery(userRef.
                    startAt(str).endAt(str = "\uf8ff"), Requests.class).build();
        }


        FirebaseRecyclerAdapter<Requests, viewRequestsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Requests, viewRequestsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull viewRequestsViewHolder holder, int i, @NonNull Requests model) {

                holder.nameView.setText(model.getNameOfApplicant());
                holder.contactView.setText(model.getPhone());
                holder.descriptionView.setText(model.getDescription());
                holder.locationView.setText(model.getLocation());
                holder.noOfPeopleView.setText(model.getPeoples());

            }

            @NonNull
            @Override
            public viewRequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
                viewRequestsViewHolder viewHolder = new viewRequestsViewHolder(view);
                return viewHolder;

            }
        };

        requestList.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();

    }



    public static class viewRequestsViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameView, contactView, locationView,descriptionView,noOfPeopleView;
        CardView cardView;

        public viewRequestsViewHolder(@NonNull View itemView){
            super(itemView);
            noOfPeopleView = itemView.findViewById(R.id.display_noOfPeople);
            nameView= itemView.findViewById(R.id.display_name);
            contactView= itemView.findViewById(R.id.display_contact);
            locationView= itemView.findViewById(R.id.display_location);
            descriptionView= itemView.findViewById(R.id.display_description);
            cardView= itemView.findViewById(R.id.card_view);

        }
    }

}