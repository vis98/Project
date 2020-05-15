package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Posts extends Fragment {
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    List<Post> postList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.posts, container, false);
        postList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        postList.add(new Post(R.drawable.gsb, "GSB-Ganpati", "It was founded in 1928, this ganesh mandal is located at a walking distance from lalbaughcha Raja mandal. It’s the oldest one in the town"));
        postList.add(new Post(R.drawable.ketwari, "Khetwadi-Ganpati", "Founded in 1959 Khetwadi Ganraj mandal is famous for the astonishing and beautiful design of Ganesh idol. In 2000 this mandal made the highest Ganesh Statue of 40 feet and also won an award for that. This place receives huge crowd during Ganesh Chaturthi as every lane in this area there is one Ganesh murti."));
        postList.add(new Post(R.drawable.ganesh, "Ganesh-Gali", "It was founded in 1954 by a community from Karnataka, known as Gowd saraswat bramhin community. GSB Seva Kings Circle Ganesh Statue is famous as the richest Ganesh Idol of the town. It is so because more than 60 kilogram of gold ornaments are used to decorate the Ganesh Murti. Ganesh Idol used here is eco friendly and made out of clay."));
        postAdapter = new PostAdapter(getContext(), postList);
        recyclerView.setAdapter(postAdapter);

        return view;
    }
}
