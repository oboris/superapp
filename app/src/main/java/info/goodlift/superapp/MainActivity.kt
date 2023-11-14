package info.goodlift.superapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import info.goodlift.superapp.adapter.MyAdapter

import info.goodlift.superapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var vm: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this)[MyViewModel::class.java]
//        vm.str.observe(this){
//            binding.tvMyText.text = it
//        }

        val rv = binding.rvMyList
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = MyAdapter(this)
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}