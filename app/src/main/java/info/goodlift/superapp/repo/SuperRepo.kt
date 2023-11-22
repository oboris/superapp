package info.goodlift.superapp.repo

import info.goodlift.superapp.dao.MyDao
import info.goodlift.superapp.model.Author

class SuperRepo(val myDao: MyDao) {
    suspend fun insertAuthors(authors: List<Author>){
        myDao.insertAuthors(authors)
    }

    suspend fun getAllAuthors(): List<Author>{
        return myDao.getAllAuthors()
    }
}