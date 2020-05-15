package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewholder> {
    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_post, null);
        return new PostViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewholder holder, int position) {
        Post post = posts.get(position);
        holder.imageView.setImageResource(post.getImage());
        holder.textView.setText(post.getTitle());
        holder.textView1.setText(post.getDesc());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostViewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textView1;

        public PostViewholder(View view) {
            super(view);
            imageView = view.findViewById(R.id.img);
            textView = view.findViewById(R.id.title);
            textView1 = view.findViewById(R.id.desc);
        }
    }
}
