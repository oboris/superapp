package info.goodlift.superapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import info.goodlift.superapp.model.Author
import info.goodlift.superapp.model.Book
import info.goodlift.superapp.model.BookWithAuthor

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<Book>)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("Delete From books")
    suspend fun deleteAllBooks()

    @Query("Select * From books")
    suspend fun getAllBooks(): List<Book>

    @Query("Select * From books Join authors On books.id_author = authors.id_author")
    suspend fun getAllFullBooks(): List<BookWithAuthor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthor(author: Author)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthors(authors: List<Author>)

    @Delete
    suspend fun deleteAuthor(author: Author)

    @Query("Delete From authors")
    suspend fun deleteAllAuthors()

    @Query("Select * From authors")
    suspend fun getAllAuthors(): List<Author>

    @Query("Select * From authors Where first_name = :name")
    suspend fun getAuthorByName(name: String): Author
}