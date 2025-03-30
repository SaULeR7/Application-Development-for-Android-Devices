package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.project.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout


private lateinit var viewPager: ViewPager
private lateinit var tabLayout: TabLayout
private lateinit var  btnLogin: Button
private lateinit var fragmentPagerAdapter: FragmentPagerAdapter
private lateinit var binding: ActivityMainBinding


var loged = false
var Username: String? = ""

class MainActivity : AppCompatActivity(), CardFragment.OnReloadTabListener {
    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager= binding.viewPager
        tabLayout = binding.tabLayout
        btnLogin = binding.loginBtn


        btnLogin.setOnClickListener {
            if(!loged) {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            else {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        fragmentPagerAdapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> ListFragment()
                    else -> CardFragment()
                }
            }

            override fun getCount(): Int {
                return 2
            }

            override fun getPageTitle(position: Int): CharSequence {
                return when (position) {
                    0 -> "Shop"
                    else -> {
                        val sharedViewModel = provideSharedViewModel()
                        if (sharedViewModel.i == 0)
                            "Cart"
                        else
                            "Cart(${sharedViewModel.i})"
                    }
                }
            }
        }

        viewPager.adapter = fragmentPagerAdapter
        tabLayout.setupWithViewPager(viewPager)


        val extras = intent.extras ?: return
        sharedViewModel.username = extras.getString("Username").toString()
        sharedViewModel.logedin = extras.getBoolean("Loged")
        if(sharedViewModel.logedin) {
            btnLogin.setText("Logout")
        }
        else {
            btnLogin.setText("Login")
        }
    }

    override fun reloadTab() {
        fragmentPagerAdapter.notifyDataSetChanged()
    }

    fun provideSharedViewModel(): SharedViewModel {
        return sharedViewModel
    }

}
