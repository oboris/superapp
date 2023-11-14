package info.goodlift.superapp.model

data class AuthorsHorizontalList (
    val authorsList: List<Author>
): ItemTypeInterface {
    override fun getItemType(): Int {
        return ItemTypeInterface.authorsListType
    }
}
