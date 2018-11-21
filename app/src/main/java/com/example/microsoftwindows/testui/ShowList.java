package com.example.microsoftwindows.testui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import com.example.microsoftwindows.testui.Customer;
import utility.Util;

import static android.content.ContentValues.TAG;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ShowList extends Activity {
    ListView customerList;
    ArrayList<String> listItems=new ArrayList<String>();

    ArrayAdapter<String> adapter;
    Button btnAddCustomer;
    ArrayList<String> nameArrayList = new ArrayList<String>();
    ArrayList<String> infoArrayList = new ArrayList<String>();
    ArrayList<Integer> imageArrayList = new ArrayList<Integer>();
    String[] nameArray;
    String[] infoArray;
    Integer[] imageArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        nameArrayList.add("Bùi Thanh Minh");
//        nameArrayList.add("Tạ Minh Ngọc");
//        infoArrayList.add("Khối dự án Hộ cá thể ");
//        infoArrayList.add("Khối dự án Hộ cá thể ");
//        imageArrayList.add(R.drawable.gender_male2_512);
//        imageArrayList.add(R.drawable.gender_male2_512);

//        DatabaseReference myRef = database.getReference("Customers").push();
//        Customer customer = new Customer("Tạ Minh Chí", "012345678", "Ban Công Nghệ");
//
//        myRef.setValue(customer);
        // Read from the database
        super.onCreate(savedInstanceState);
        try {
            //        say hi to username
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                if (extras.getString(EXTRA_MESSAGE).equals("login")) {
                    String username = extras.getString("username");
                    Context context = getApplicationContext();
                    CharSequence text = "Hello " + username.toString();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    //The key argument here must match that used in the other activity
                } else if (extras.getString(EXTRA_MESSAGE).equals("addCustomer")) {
                    String customerName = extras.getString("customerName");
                    String customerPhoneNumber = extras.getString("customerPhoneNumber");
                    String customerPosition = extras.getString("customerPosition");
//                    add customer info to list
                    nameArrayList.add(customerName);
                    infoArrayList.add(customerPosition);
                    imageArrayList.add(R.drawable.gender_male2_512);
                    Context context = getApplicationContext();
                    CharSequence text = customerName + " , " + customerPhoneNumber + " , " + customerPosition;
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        }catch (Exception ex) {
            Log.e("TestUI",ex.toString());
        }
        setContentView(R.layout.activity_show_list);
//        set List adapter
//        try{
//            if(nameArray.length > 0 ){
//                CustomerListAdapter customerListAdapter = new CustomerListAdapter(ShowList.this, nameArray,infoArray,imageArray);
//                customerList.setAdapter(customerListAdapter);}}
//        catch (NullPointerException e){
//            Log.e(TAG, "CustomerList is null", e);
//        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Customers");

//        myRef.setValue("Hello, World!");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int count = 0;
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Customer customer1 = postSnapshot.getValue(Customer.class);
                    nameArrayList.add(customer1.username);
                    infoArrayList.add(customer1.position);
                    imageArrayList.add(R.drawable.gender_male2_512);
                    count++;
                }
//                Log.d(TAG, "-tmchi-Value is: " + value);
                Context context = getApplicationContext();
                CharSequence text ="Getted: " + Integer.toString(count) + " customer";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();



                nameArray = nameArrayList.toArray(new String[0]);
                infoArray = infoArrayList.toArray(new String[0]);
                imageArray = imageArrayList.toArray(new Integer[0]);
                CustomerListAdapter customerListAdapter = new CustomerListAdapter(ShowList.this, nameArray,infoArray,imageArray);
                customerList.setAdapter(customerListAdapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




        customerList = (ListView) findViewById(R.id.customer_list);
        btnAddCustomer = (Button) findViewById(R.id.button_add_customer);
        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addCustomer(v);

//                    listItems.add("New Item");
//                    adapter.notifyDataSetChanged();
                }catch (Exception ex) {
                Util.handleException(ex);
                }
            }
        });

//        customerList = (ListView) findViewById(R.id.list);
//        customerList.setAdapter(customerListAdapter);
//        adapter=new ArrayAdapter<String>(this,
//                R.layout.list_customer,
//                listItems);
//        setListAdapter(adapter);
////        listItems.setAdapter(adapter);
//        listItems.add("New Item");
//        adapter.notifyDataSetChanged();

//        customerNameList = new ArrayList<String>();
//        adapter = new ArrayAdapter<String>(ShowList.this, R.layout.activity_show_list, customerNameList);
//        customerList.setAdapter(adapter);





    }
    /** Called when the user taps the Send button */
    public void addCustomer(View view) {
            Intent intent = new Intent(this, AddCustomer.class);
            startActivity(intent);
    }

}
