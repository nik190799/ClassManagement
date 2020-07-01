package com.creedeon.creed003;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StudentAttendance extends AppCompatActivity {

    String sid;

    public static int count=1,P=1,A=1;
    float average= (float) 0.0;
    TextView t;
    String avg,p1,p2,p3,p4,p5,p6,p7,p8;
    String student_id;
    ArrayList dates = new ArrayList<>();;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbAttendance;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);

        t=(TextView) findViewById(R.id.textView3);
        listView = (ListView) findViewById(R.id.list);

        //here...
        //sid = getString("id")



    }
}
