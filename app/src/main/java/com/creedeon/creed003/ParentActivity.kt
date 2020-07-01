package com.creedeon.creed003


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_parent.*


class ParentActivity : AppCompatActivity() {


    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Student")


    override fun onBackPressed() {

    }

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    var s_id:String= String()
    //var classes:String = String()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent)
        s_id = intent.getStringExtra("id")
        //classes = intent.getStringExtra("classes")
        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))




    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_parent, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        s_id = intent.getStringExtra("id")
        when(item.itemId){
            R.id.action_profile-> {
                val intent = Intent(this,Profile::class.java)
                intent.putExtra("id",s_id)
                startActivity(intent)
            }
        }

        when(item.itemId){
            R.id.action_about_us-> {
                val intent = Intent(this,AboutUs::class.java)
                startActivity(intent)
            }
        }
        when(item.itemId){
            R.id.action_change_password->{
                val intent = Intent(this,ChangePassword::class.java)
                intent.putExtra("id",s_id)
                startActivity(intent)
            }
        }



        var shared: SharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        when(item.itemId){
            R.id.action_logout-> {
                shared.edit().clear().apply()
                var intent = Intent(this,MainActivity::class.java)

                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }





    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {

        private var pTab1 =ParentTab1()
        private var pTab3 =ParentTab3()

        override fun getItem(position: Int): androidx.fragment.app.Fragment {

            val bundle = Bundle()
            bundle.putString("id",s_id)

            pTab1.arguments = bundle
            pTab3.arguments = bundle
            return when (position){
                0 -> pTab1
                1 -> pTab3
               // 2 -> pTab3
                else -> null!!
            }

        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 2
        }
    }


}
