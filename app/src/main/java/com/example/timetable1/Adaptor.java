package com.example.timetable1;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptor extends RecyclerView.Adapter<Adaptor.ChatListHolder> {
    private ArrayList<TimetableModal> courseModalArrayList;
    MainActivity mainActivity=new MainActivity();
    spinSelect obj=new spinSelect();
    private Context context;

    // constructor
    public Adaptor(ArrayList<TimetableModal> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @Override
    public Adaptor.ChatListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout, parent, false);
        return new ChatListHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(Adaptor.ChatListHolder holder, int position) {
        TimetableModal modal = courseModalArrayList.get(position);
        holder.Sub.setText(modal.getSubject());
        holder.time.setText(modal.getTime());
        holder.i.setImageResource(R.drawable.calender_1);
        holder.faculty.setText(modal.getFaculty());
        holder.type.setText(modal.getType());
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbConnect db=new DbConnect(context);
                db.deleteCourse(modal.getSubject());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseModalArrayList.size();

    }


    public class ChatListHolder extends RecyclerView.ViewHolder{
        TextView Sub,date,time,type,faculty;
        Button b;
        ImageView i,del;
        public ChatListHolder( View itemView) {
            super(itemView);
           Sub=itemView.findViewById(R.id.Subject);
           i=itemView.findViewById(R.id.imageView);
          // tv1=itemView.findViewById(R.id.textView1);
           time=itemView.findViewById(R.id.Time);
           del=itemView.findViewById(R.id.delete_rec);
           type=itemView.findViewById(R.id.Type);
           faculty=itemView.findViewById(R.id.Faculty);

        }


    }
}
