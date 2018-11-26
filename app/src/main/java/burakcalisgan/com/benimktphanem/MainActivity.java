package burakcalisgan.com.benimktphanem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listViewBooks;
    BookAdapter bookAdapter;
    ArrayList<Book> bookArrayList;

    ArrayList<String> arrayBookName = new ArrayList<String>();
    ArrayList<String> arrayAuthor = new ArrayList<String>();
    ArrayList<String> arrayPageNumber = new ArrayList<String>();


    FirebaseDatabase database;
    DatabaseReference tableRef;

    public void arrayClear(){
        arrayBookName.clear();
        arrayAuthor.clear();
        arrayPageNumber.clear();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getApplicationContext(),addBookActivity.class);
                startActivity(intent);
            }
        });

        bookArrayList = new ArrayList<Book>();

        //bookArrayList.add(new Book("Melekler ve Şeytanlar","Dan Brown","350"));
        //bookArrayList.add(new Book("Melekler ve Şeytanlar2","Dan Brown","350"));
        //bookArrayList.add(new Book("Melekler ve Şeytanlar3","Dan Brown","350"));

        listViewBooks = findViewById(R.id.lvBookList);

        //Veri Okuma İşlemi

        database = FirebaseDatabase.getInstance();
        tableRef = database.getReference("tableBook");

        tableRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arrayClear();
                bookArrayList.clear();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){

                    //System.out.println(postSnapshot.getValue().toString());

                    Map<String,String> map = (Map<String,String>) postSnapshot.getValue();

                    //System.out.println(map.get("bookName"));
                    //System.out.println(map.get("author"));
                    //System.out.println(String.valueOf(map.get("pageNumber")));

                     if (map.get("bookName") != null && map.get("author") != null && map.get("pageNumber") != null ){

                         arrayBookName.add(map.get("bookName"));
                         arrayAuthor.add(map.get("author"));
                         arrayPageNumber.add(String.valueOf(map.get("pageNumber")));

                     }

                }

                for ( int i = 0 ; i<arrayBookName.size() ; i++  )
                {
                    bookArrayList.add(new Book(arrayBookName.get(i),arrayAuthor.get(i),arrayPageNumber.get(i)));
                }
                listViewBooks.invalidateViews();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Veri Okuma Hatası :" + databaseError.toException().toString(),Toast.LENGTH_SHORT).show();
            }
        });




        bookAdapter = new BookAdapter(getApplicationContext(),bookArrayList);
        listViewBooks.setAdapter(bookAdapter);


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
