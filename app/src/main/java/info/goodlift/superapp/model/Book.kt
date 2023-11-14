package info.goodlift.superapp.model

data class Book(
    var name: String,
    val author: Author,
    val pageNumbers: Int
): ItemTypeInterface {
    override fun getItemType(): Int {
        return ItemTypeInterface.bookType
    }
}
