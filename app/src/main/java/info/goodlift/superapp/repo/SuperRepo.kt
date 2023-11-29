package info.goodlift.superapp.repo

import info.goodlift.superapp.dao.MyDao
import info.goodlift.superapp.model.Author
import info.goodlift.superapp.model.Book

class SuperRepo(private val myDao: MyDao) {

    suspend fun clearBooks(){
        myDao.deleteAllBooks()
    }

    suspend fun clearAuthors(){
        myDao.deleteAllAuthors()
    }

    suspend fun insertAuthors(authors: List<Author>){
        myDao.insertAuthors(authors)
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
            books.add(book)
        }
        return books
    }

    suspend fun delAuthor(author: Author){
        myDao.deleteAuthor(author)
    }
}