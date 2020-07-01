package com.creedeon.creed003

//import android.support.test.orchestrator.junit.BundleJUnitUtils.getDescription
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onBackPressed() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var shared: SharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        var Teachershared: SharedPreferences = getSharedPreferences("Teachershared", Context.MODE_PRIVATE)
        var Adminshared: SharedPreferences = getSharedPreferences("Adminshared", Context.MODE_PRIVATE)
        if(shared.contains("stuid")){
            val intent = Intent(this@MainActivity, ParentActivity::class.java)
            intent.putExtra("id",shared.getString("stuid",""))
            startActivity(intent)
        }
        if(Teachershared.contains("Teacherid")){
            val intent = Intent(this@MainActivity, TeacherActivity::class.java)
            intent.putExtra("id",Teachershared.getString("Teacherid",""))
            startActivity(intent)
        }
        if(Adminshared.contains("Adminid")){
            val intent = Intent(this@MainActivity, AdminActivity::class.java)
            intent.putExtra("id",Adminshared.getString("Adminid",""))
            startActivity(intent)
        }

        admin_button.setOnClickListener {
            startActivity(Intent(this,AdminLogin::class.java))
        }

        teacher_button.setOnClickListener {
            startActivity( Intent(this,TeacherLogin::class.java))
        }

        parent_button.setOnClickListener {
            startActivity(Intent(this,StudentLogin::class.java))
        }

        public_button.setOnClickListener {
            startActivity(Intent(this,PublicLogin::class.java))
        }


    }
}
