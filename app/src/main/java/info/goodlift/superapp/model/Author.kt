package info.goodlift.superapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "authors")
data class Author(

    @field:Json(name = "first_name")
    @ColumnInfo(name = "first_name")
    val firstName: String,

    @field:Json(name = "last_name")
    @ColumnInfo(name = "last_name")
    val lastName: String,

    @field:Json(name = "birth_date")
    @ColumnInfo(name = "birth_date")
    val birthDate: String,
) : ItemTypeInterface {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_author")
    var idAuthor: Long = 0

    override fun getItemType(): Int {
        return ItemTypeInterface.authorType
    }
}
