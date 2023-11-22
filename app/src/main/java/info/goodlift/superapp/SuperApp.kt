package info.goodlift.superapp

import android.app.Application
import info.goodlift.superapp.database.SuperDataBase
import info.goodlift.superapp.repo.SuperRepo

class SuperApp : Application() {
    val superDataBase by lazy { SuperDataBase.getDatabase(this) }
    val superRepository by lazy { SuperRepo(superDataBase.myDao()) }
}