package com.example.rent.sdacourseapplication.todo_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rent.sdacourseapplication.R;


public class todoList_activity extends AppCompatActivity implements OnItemCheckStateChanged {

    private ToDoListAdapter toDoListAdapter;
    private RecyclerView recyclerView;
    private String activityTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_activity);

        activityTitle = getSupportActionBar().getTitle().toString();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        toDoListAdapter = new ToDoListAdapter();
        recyclerView.setAdapter(toDoListAdapter);
        toDoListAdapter.setCheckListener(this);
        final EditText editText = (EditText) findViewById(R.id.todo_editText);
        Button addButton = (Button) findViewById(R.id.add_Button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoListAdapter.addItem(editText.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_item_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_list) {
            toDoListAdapter.deleteAllCheckedItems();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnItemCheckStateChanged(int checkedItemsCount) {
        if (checkedItemsCount > 0) {
            getSupportActionBar().setTitle("Checked item : " + checkedItemsCount);
        } else {
            getSupportActionBar().setTitle(activityTitle);
        }
    }
}
