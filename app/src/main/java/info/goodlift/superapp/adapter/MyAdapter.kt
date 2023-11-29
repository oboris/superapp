package info.goodlift.superapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import info.goodlift.superapp.MainActivity
import info.goodlift.superapp.databinding.AuthorItemBinding
import info.goodlift.superapp.databinding.AuthorsListItemBinding
import info.goodlift.superapp.databinding.BookItemBinding
import info.goodlift.superapp.model.Author
import info.goodlift.superapp.model.AuthorsHorizontalList
import info.goodlift.superapp.model.Book
import info.goodlift.superapp.model.ItemTypeInterface

class MyAdapter(val mainActivity: MainActivity) : RecyclerView.Adapter<MyAdapter.MyHolder>() {
    private var myList: List<ItemTypeInterface> = emptyList()

//    private var orderViewModel: OrderViewModel = ViewModelProvider(orderFragment as ViewModelStoreOwner)[OrderViewModel::class.java]
    init {
        mainActivity.vm.myList.observe(mainActivity){
            myList = it
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return when (viewType) {
            ItemTypeInterface.bookType -> BookHolder(BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            ItemTypeInterface.authorsListType -> AuthorsListHolder(AuthorsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> AuthorHolder(AuthorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(myList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return myList[position].getItemType()
    }

    abstract class MyHolder(itemBinding: ViewBinding) : RecyclerView.ViewHolder(itemBinding.root){
        abstract fun bind(item: ItemTypeInterface)
    }

    inner class BookHolder(private val itemBinding: BookItemBinding) : MyHolder(itemBinding) {
        override fun bind(item: ItemTypeInterface){
            item as Book
            itemBinding.tvBookName.text = item.name
            itemBinding.inclBookAuthor.tvFirstName.text = item.author?.firstName ?: "empty"
            itemBinding.inclBookAuthor.tvLastName.text = item.author?.lastName ?: "empty"
            itemBinding.inclBookAuthor.tvBirthDate.text = item.author?.birthDate ?: "empty"
            itemBinding.tvPageNumbers.text = item.pageNumbers.toString()
        }
    }

    inner class AuthorHolder(private val itemBinding: AuthorItemBinding) : MyHolder(itemBinding){
        override fun bind(item: ItemTypeInterface){
            item as Author
            itemBinding.tvFirstName.text = item.firstName
            itemBinding.tvLastName.text = item.lastName
            itemBinding.tvBirthDate.text = item.birthDate
            itemBinding.btnAuthorDel.setOnClickListener { mainActivity.vm.delAuthor(item) }
        }
    }

    inner class AuthorsListHolder(private val itemBinding: AuthorsListItemBinding) : MyHolder(itemBinding){
        override fun bind(item: ItemTypeInterface){
            item as AuthorsHorizontalList
            val rv: RecyclerView = itemBinding.rvAuthorList
            rv.layoutManager = LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false)
            rv.addItemDecoration(DividerItemDecoration(mainActivity, DividerItemDecoration.HORIZONTAL))
            rv.adapter = AuthorsListHorizontalAdapter(item.authorsList)
        }
    }
}
