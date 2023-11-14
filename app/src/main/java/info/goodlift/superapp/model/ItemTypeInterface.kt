package info.goodlift.superapp.model

interface ItemTypeInterface {
    fun getItemType(): Int

    companion object{
        const val bookType: Int = 1
        const val authorType: Int = 2
        const val authorsListType: Int = 3
    }
}