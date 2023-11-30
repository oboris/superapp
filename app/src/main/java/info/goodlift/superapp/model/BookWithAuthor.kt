package info.goodlift.superapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class BookWithAuthor (
    var name: String,

    @ColumnInfo(name = "page_number")
    var pageNumbers: Int,

    var comment: String? = null,

    @ColumnInfo(name = "id_book")
    var idBook: Long,

    @Embedded
    val author: Author
)