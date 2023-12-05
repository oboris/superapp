package info.goodlift.superapp.api

import info.goodlift.superapp.model.Author
import info.goodlift.superapp.model.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("books.php")
    suspend fun getBooks(@Query("number") num: Int): Response<List<Book>>

    @GET("authors.php")
    suspend fun getAuthors(@Query("number") num: Int): Response<List<Author>>

    @GET("bookswithauthors.php")
    suspend fun getBooksWithAuthors(
        @Query("numauthors") numAuthors: Int,
        @Query("numbooks") numBooks: Int
    ): Response<List<Book>>
}