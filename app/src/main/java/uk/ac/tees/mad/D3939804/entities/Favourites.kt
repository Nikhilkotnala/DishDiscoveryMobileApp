package uk.ac.tees.mad.D3939804.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Favourites", indices = [Index(value = ["mealId", "email"], unique = true)])
class Favourites(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var mealId:String,
    var email:String?
)
