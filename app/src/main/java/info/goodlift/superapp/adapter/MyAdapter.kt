package info.goodlift.superapp.adapter

import android.content.Context
import android.view.LayoutInflater
//import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.lifecycle.LiveData
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

class MyAdapter(
    private val myList: LiveData<List<ItemTypeInterface>>,
    private var onClickListener: OnClickListener
) : RecyclerView.Adapter<MyAdapter.MyHolder>() {
    interface OnClickListener {
        fun onClick(item: ItemTypeInterface, num: Int = 0)
    }

//    var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return when (viewType) {
            ItemTypeInterface.bookType -> BookHolder(
                BookItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )

            ItemTypeInterface.authorsListType -> AuthorsListHolder(
                AuthorsListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), parent.context
            )

            else -> AuthorHolder(
                AuthorItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return myList.value!!.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(myList.value!![position], onClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return myList.value!![position].getItemType()
    }

    abstract class MyHolder(itemBinding: ViewBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        abstract fun bind(item: ItemTypeInterface, clickListener: OnClickListener)
    }

    class BookHolder(private val itemBinding: BookItemBinding) : MyHolder(itemBinding) {
        override fun bind(item: ItemTypeInterface, clickListener: OnClickListener) {
            item as Book
            itemBinding.tvBookName.text = item.name
            itemBinding.inclBookAuthor.tvFirstName.text = item.author?.firstName ?: "empty"
            itemBinding.inclBookAuthor.tvLastName.text = item.author?.lastName ?: "empty"
            itemBinding.inclBookAuthor.tvBirthDate.text = item.author?.birthDate ?: "empty"
            itemBinding.tvPageNumbers.text = item.pageNumbers.toString()
            itemBinding.btnPagePlus.setOnClickListener { clickListener.onClick(item, 1) }
            itemBinding.btnPageMinus.setOnClickListener { clickListener.onClick(item, 2) }
        }
    }

    class AuthorHolder(private val itemBinding: AuthorItemBinding) : MyHolder(itemBinding) {
        override fun bind(item: ItemTypeInterface, clickListener: OnClickListener) {
            item as Author
            itemBinding.tvFirstName.text = item.firstName
            itemBinding.tvLastName.text = item.lastName
            itemBinding.tvBirthDate.text = item.birthDate
            itemBinding.btnAuthorDel.setOnClickListener { clickListener.onClick(item) }
        }
    }

    class AuthorsListHolder(
        private val itemBinding: AuthorsListItemBinding,
        private val context: Context
    ) : MyHolder(itemBinding) {
        override fun bind(item: ItemTypeInterface, clickListener: OnClickListener) {
            item as AuthorsHorizontalList
            val rv: RecyclerView = itemBinding.rvAuthorList
            rv.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rv.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.HORIZONTAL
                )
            )
            rv.adapter = AuthorsListHorizontalAdapter(item.authorsList)
        }
    }
}
