package com.creedeon.creed003

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_teacher.*

class TeacherActivity : AppCompatActivity() {
    override fun onBackPressed() {
    }
    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    var t_id:String= String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)

        t_id = intent.getStringExtra("id")
        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        //fab.setOnClickListener { view ->
        //     Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //         .setAction("Action", null).show()
        // }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_teacher, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       // when(item.itemId){
        //    R.id.action_profile-> {
           //     val intent = Intent(this,Profile::class.java)
        //        startActivity(intent)
         //   }
        //}

        when(item.itemId){
            R.id.action_about_us-> {
                val intent = Intent(this,AboutUs::class.java)
                startActivity(intent)
            }
        }

        when(item.itemId){
            R.id.action_change_password-> {
                val intent = Intent(this,ChangePasswordT::class.java)
                intent.putExtra("id",t_id)
                startActivity(intent)
            }
        }
        val Teachershared = getSharedPreferences("Teachershared", Context.MODE_PRIVATE)
        when(item.itemId){
            R.id.action_logout-> {
                Teachershared.edit().clear().apply()
                val intent = Intent(this,MainActivity::class.java)
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

        private val tTab1 = TeacherTab1()
        private val tTab3 = TeacherTab3()
        override fun getItem(position: Int): androidx.fragment.app.Fragment {

            val bundle = Bundle()
            bundle.putString("id",t_id)

            tTab1.arguments = bundle
            tTab3.arguments = bundle

            return when (position) {
                0 -> tTab1
                1 -> tTab3
                else -> null!!
            }
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 2
        }
    }

}
