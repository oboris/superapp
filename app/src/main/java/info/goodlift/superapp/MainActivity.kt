package info.goodlift.superapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import info.goodlift.superapp.adapter.MyAdapter

import info.goodlift.superapp.databinding.ActivityMainBinding
import info.goodlift.superapp.model.Author
import info.goodlift.superapp.model.Book
import info.goodlift.superapp.model.ItemTypeInterface


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var vm: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this)[MyViewModel::class.java]

        val rv = binding.rvMyList
        rv.layoutManager = LinearLayoutManager(this)

        val onClc = object : MyAdapter.OnClickListener {
            override fun onClick(item: ItemTypeInterface, num: Int) {
                when (item) {
                    is Author -> {
                        vm.delAuthor(item)
                    }
                    is Book -> {
                        if (num == 1) {
                            vm.plusPage(item)
                        } else {
                            vm.minusPage(item)
                        }
                    }
                }
            }
        }
        val myAdapter =  MyAdapter(vm.myList, onClc)

        rv.adapter = myAdapter
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        vm.myList.observe(this){
            myAdapter.notifyDataSetChanged()
        }
    }
}