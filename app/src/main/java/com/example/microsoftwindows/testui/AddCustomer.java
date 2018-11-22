package com.example.microsoftwindows.testui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class AddCustomer extends AppCompatActivity {
    LinearLayout btnBack;
    LinearLayout btnSaveTop;
    Button btnSaveBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        btnBack = (LinearLayout)findViewById(R.id.btn_add_customer_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });
        btnSaveTop = (LinearLayout)findViewById(R.id.btn_add_customer_save_top);
        btnSaveTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomer(v);
            }
        });
        btnSaveBottom = (Button)findViewById(R.id.btn_add_customer_save_bottom);
        btnSaveBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomer(v);
            }
        });
    }
    public void back(View view){
        Intent intent = new Intent(this, ShowList.class);
        startActivity(intent);
    }
    public void addCustomer(View view){

        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });



        Intent intent = new Intent(this, ShowList.class);
        EditText edTxtCustomerName = (EditText) findViewById(R.id.add_customer_username);
        String strCustomerName = edTxtCustomerName.getText().toString();
        EditText edTxtCustomerPhoneNumber = (EditText) findViewById(R.id.add_customer_phone_number);
        String strCustomerPhonenumber = edTxtCustomerPhoneNumber.getText().toString();
        EditText edTxtCustomerPosition = (EditText) findViewById(R.id.add_customer_position);
        String strCustomerPosition = edTxtCustomerPosition.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, "addCustomer");

        intent.putExtra("customerName", strCustomerName);
        intent.putExtra("customerPhoneNumber", strCustomerPhonenumber);
        intent.putExtra("customerPosition", strCustomerPosition);
//        Add info to database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Customers").push();
        Customer customer = new Customer(strCustomerName, strCustomerPhonenumber, strCustomerPosition);
        myRef.setValue(customer);

        startActivity(intent);
    }
}
