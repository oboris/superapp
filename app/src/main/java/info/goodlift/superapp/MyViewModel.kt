package info.goodlift.superapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.goodlift.superapp.model.Author
import info.goodlift.superapp.model.AuthorsHorizontalList
import info.goodlift.superapp.model.Book
import info.goodlift.superapp.model.ItemTypeInterface
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {

    val repo = (application as SuperApp).superRepository
    private var _str = MutableLiveData("12345")
    val str: LiveData<String> = _str

    //var myList: List<ItemTypeInterface> = emptyList()

    private var _myList: MutableLiveData<List<ItemTypeInterface>> = MutableLiveData<List<ItemTypeInterface>>().apply {
        value = emptyList()
    }
    val myList: LiveData<List<ItemTypeInterface>> = _myList

    val authorsList =
        listOf(
            Author("First name 1", "Second name 1", "jjjj"),
            Author("First name 2", "Second name 2", "jdfgidfgfd"),
            Author("First name 3", "Second name 3", "dfkjfd"),
            Author("First name 4", "Second name 1", "jjjj"),
            Author("First name 5", "Second name 2", "jdfgidfgfd"),
            Author("First name 6", "Second name 3", "dfkjfd"),
        )

    init {
        viewModelScope.launch {
            repo.insertAuthors(authorsList)
            _myList.value = repo.getAllAuthors()
        }
//        myList = listOf(
//            Book("book 1", authorsList[1], 300),
//            Book("book 2", authorsList[1], 150),
//            AuthorsHorizontalList(authorsList),
//            Book("book 3", authorsList[2], 200),
//            Author("First name 1", "Second name 1", "jjjj"),
//            Book("book 4", authorsList[1], 100),
//            Book("book 5", authorsList[2], 30),
//            AuthorsHorizontalList(authorsList.subList(2, 6)),
//            Author("First name 2", "Second name 2", "jdfgidfgfd"),
//            Author("First name 3", "Second name 3", "dfkjfd"),
//            Book("book 6", authorsList[3], 100),
//            AuthorsHorizontalList(authorsList.subList(3, 6)),
//            Book("book 7", authorsList[4], 30),
//        )
    }

    fun textChanger(s: String) {
        _str.value = s
    }
}