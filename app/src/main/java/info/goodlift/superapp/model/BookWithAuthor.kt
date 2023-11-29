package info.goodlift.superapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class BookWithAuthor (
    var name: String,
    @ColumnInfo(name = "page_number")
    var pageNumbers: Int,
    var comment: String? = null,

    @Embedded
    val author: Author
)