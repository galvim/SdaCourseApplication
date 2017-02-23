package com.example.rent.sdacourseapplication.todo_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.rent.sdacourseapplication.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RENT on 2017-02-22.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.MyViewHolder>{
   private List<ToDoListItem> items = new ArrayList<>();
    private OnItemCheckStateChanged checkListener;

    public void setCheckListener(OnItemCheckStateChanged checkListener) {
        this.checkListener = checkListener;
    }






    public class MyViewHolder extends RecyclerView.ViewHolder {

     TextView textView;
        CheckBox checkBox;

       public MyViewHolder(View itemView)
       {
           super(itemView);
           textView = (TextView) itemView.findViewById(R.id.item_text);
           checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
       }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       View view = inflater.inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ToDoListItem listItem = items.get(position);
        holder.textView.setText(listItem.getText());
        holder.checkBox.setChecked(listItem.isChecked());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                listItem.setChecked(isChecked);
                if(checkListener != null){
                    checkListener.OnItemCheckStateChanged(getCheckItemCount());
                }
            }
        });

    }
    private int getCheckItemCount() {
        int count = 0;

        for (ToDoListItem item : items) {
            if (item.isChecked()) {
                count++;
            }

        }
        return count;

    }



    public void addItem(String item) {
        items.add(new ToDoListItem(item));
         notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return items.size();
    }
    public void deleteAllCheckedItems() {
         List<ToDoListItem> newItems = new ArrayList<>();
        for (int i=0; i<items.size(); i++){
            if(!items.get(i).isChecked()){
                newItems.add(items.get(i));
            }
        } items = newItems;
        notifyDataSetChanged();

        if(checkListener != null){
            checkListener.OnItemCheckStateChanged(getCheckItemCount());
        }

    }

}



