package jssvc.edu.cn.recyclerview2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Person> personList;

    public MyAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.nameText.setText(person.getName());
        holder.phoneText.setText(person.getPhone());
        holder.head.setImageResource(person.getHeadId());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView phoneText;
        ImageView head;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.nameText = itemView.findViewById(R.id.textView);
            this.phoneText = itemView.findViewById(R.id.textView2);
            this.head = itemView.findViewById(R.id.imageView);
        }
    }
}
