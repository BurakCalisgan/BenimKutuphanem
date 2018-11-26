package burakcalisgan.com.benimktphanem;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addBookActivity extends AppCompatActivity {

    EditText etBookName, etAuthor, etPageNumber;
    Button btnSave;
    FirebaseDatabase database;
    DatabaseReference mRef;

    Integer count;
    String bookName, author, pageNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        etBookName = findViewById(R.id.etBookName);
        etAuthor = findViewById(R.id.etAuthor);
        etPageNumber = findViewById(R.id.etPageNumber);

        btnSave = findViewById(R.id.btnAdd);

        count = 0;

        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("lastIndex");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookName = etBookName.getText().toString();
                author = etAuthor.getText().toString();
                pageNumber = etPageNumber.getText().toString();

                count +=1;
                DatabaseReference mainRef = database.getReference("tableBook");
                DatabaseReference indexRef = mainRef.child(count.toString());
                DatabaseReference valueBook = indexRef.child("bookName") ;
                DatabaseReference valueAuthor = indexRef.child("author") ;
                DatabaseReference valuePageNumber = indexRef.child("pageNumber") ;

                valueBook.setValue(bookName);
                valueAuthor.setValue(author);
                valuePageNumber.setValue(pageNumber);

                DatabaseReference countRef = mRef.child("lindex");
                countRef.setValue(count.toString());

                Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });



        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value;
                for (DataSnapshot postDataSnapshot: dataSnapshot.getChildren()){
                    value = postDataSnapshot.getValue().toString();
                    count = Integer.parseInt(value);

                    System.out.println(value);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Veri Okuma Hatası :" + databaseError.toException().toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
