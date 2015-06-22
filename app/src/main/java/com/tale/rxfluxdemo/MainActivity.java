package com.tale.rxfluxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.tale.rxflux.Dispatcher;
import com.tale.rxfluxdemo.action.AddToDoAction;
import com.tale.rxfluxdemo.adapter.ToDoAdapter;
import com.tale.rxfluxdemo.store.ToDoStore;

import java.util.List;

import rx.functions.Action1;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rvToDos;
    private EditText etToDo;
    private ToDoAdapter adapter;
    private Dispatcher dispatcher;
    private ToDoStore toDoStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ToDoAdapter();
        dispatcher = new Dispatcher();
        toDoStore = new ToDoStore();
        toDoStore.registerWithDispatcher(dispatcher);
        toDoStore.changeObservable()
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> todos) {
                        adapter.changeDataSet(todos);
                    }
                });
        rvToDos = (RecyclerView) findViewById(R.id.rvToDos);
        rvToDos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvToDos.setHasFixedSize(true);
        rvToDos.setAdapter(adapter);
        etToDo = (EditText) findViewById(R.id.etToDo);
        findViewById(R.id.btAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String toDo = etToDo.getText().toString();
                if (TextUtils.isEmpty(toDo)) {
                    etToDo.setError("Field is emptied");
                } else {
                    final AddToDoAction addToDoAction = new AddToDoAction(toDo);
                    dispatcher.dispatchAction(addToDoAction);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
