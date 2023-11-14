package info.goodlift.superapp.model

data class Author(
    val firstName: String,
    val lastName: String,
    val birthDate: String
): ItemTypeInterface {
    override fun getItemType(): Int {
        return ItemTypeInterface.authorType
    }
}
