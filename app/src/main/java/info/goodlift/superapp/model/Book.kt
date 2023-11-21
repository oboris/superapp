package info.goodlift.superapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    tableName = "books",
    foreignKeys = [
        ForeignKey(
            entity = Author::class,
            parentColumns = ["id_author"],
            childColumns = ["id_author"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Book(
    var name: String,
    @Ignore
    val author: Author,
    @ColumnInfo(name = "page_number")
    val pageNumbers: Int,
    val comment: String? = null
) : ItemTypeInterface {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_book")
    val idBook: Int = 0

    @ColumnInfo(name = "id_author")
    val idAuthor: Int = author.idAuthor
    override fun getItemType(): Int {
        return ItemTypeInterface.bookType
    }
}
