package info.goodlift.superapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import info.goodlift.superapp.databinding.AuthorHorizontalItemBinding
import info.goodlift.superapp.model.Author

class AuthorsListHorizontalAdapter(private val list: List<Author>) :
    RecyclerView.Adapter<AuthorsListHorizontalAdapter.HorizontalAuthorsListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalAuthorsListHolder {
        return HorizontalAuthorsListHolder(
            AuthorHorizontalItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HorizontalAuthorsListHolder, position: Int) {
        holder.bind(list[position])
    }

    class HorizontalAuthorsListHolder (private val itemBinding: AuthorHorizontalItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item: Author) {
            itemBinding.tvFirstName.text = item.firstName
            itemBinding.tvLastName.text = item.lastName
            itemBinding.tvBirthDate.text = item.birthDate
        }
    }
}
