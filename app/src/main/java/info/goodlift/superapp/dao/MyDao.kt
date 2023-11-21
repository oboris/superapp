package info.goodlift.superapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import info.goodlift.superapp.model.Book

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<Book>)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("Delete From books")
    suspend fun deleteAllBooks()

    @Query("Select * From books")
    suspend fun getAllBooks(): List<Book>

}