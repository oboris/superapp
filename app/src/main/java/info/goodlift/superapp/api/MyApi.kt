package info.goodlift.superapp.api

import info.goodlift.superapp.model.Author
import info.goodlift.superapp.model.Book
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    @GET("books")
    suspend fun getBooks(): Response<List<Book>>

    @GET("authors")
    suspend fun getAuthors(): Response<List<Author>>

    @GET("bookswithauthor")
    suspend fun getBooksWithAuthors(): Response<List<Book>>
}