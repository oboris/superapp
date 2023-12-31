package info.goodlift.superapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

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
    @field:Json(name = "title")
    var name: String,

    @field:Json(name = "page_number")
    @ColumnInfo(name = "page_number")
    var pageNumbers: Int,

    val comment: String? = null
) : ItemTypeInterface {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_book")
    var idBook: Long = 0

    @Ignore
    var author: Author? = null

    @ColumnInfo(name = "id_author")
    var idAuthor: Long = author?.idAuthor ?: 0

    override fun getItemType(): Int {
        return ItemTypeInterface.bookType
    }
}
