package info.goodlift.superapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
data class Author(
    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

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
