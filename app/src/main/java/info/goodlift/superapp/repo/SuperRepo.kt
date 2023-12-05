package info.goodlift.superapp.repo

import info.goodlift.superapp.api.MyApi
import info.goodlift.superapp.api.MyRetrofitClient
import info.goodlift.superapp.dao.MyDao
import info.goodlift.superapp.model.Author
import info.goodlift.superapp.model.Book

class SuperRepo(private val myDao: MyDao) {

    private val myRetrofitClient = MyRetrofitClient.getClient()
    private val myApi = myRetrofitClient.create(MyApi::class.java)

    suspend fun loadAuthors(num: Int): List<Author>? {
        val response = myApi.getAuthors(num)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun loadBooks(numAuthors: Int, numBooks: Int): List<Book>? {
        val response = myApi.getBooksWithAuthors(numAuthors, numBooks)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

//    suspend fun loadBooks(num: Int): List<Book>? {
//        val response = myApi.getBooks(num)
//        return if (response.isSuccessful) {
//            response.body()
//        } else {
//            null
//        }
//    }

    suspend fun clearBooks(){
        myDao.deleteAllBooks()
    }

    suspend fun clearAuthors(){
        myDao.deleteAllAuthors()
    }

    suspend fun insertAuthors(authors: List<Author>){
        myDao.insertAuthors(authors)
    }

    suspend fun updateBook(book: Book){
        myDao.updateBook(book)
    }

    suspend fun getAllAuthors(): List<Author>{
        return myDao.getAllAuthors()
    }

    suspend fun getAuthor(name: String): Author{
        return myDao.getAuthorByName(name)
    }

    suspend fun insertBook(book: Book){
        myDao.insertBook(book)
    }

    suspend fun getAllFullBooks(): List<Book>{
        val books: ArrayList<Book> = ArrayList()
        val bookWithAuthor = myDao.getAllFullBooks()

        bookWithAuthor.forEach {
            val book = Book(it.name, it.pageNumbers)
            book.author = it.author
            book.idBook = it.idBook
            book.idAuthor = it.author.idAuthor
            books.add(book)
        }
        return books
    }

    suspend fun delAuthor(author: Author){
        myDao.deleteAuthor(author)
    }
}