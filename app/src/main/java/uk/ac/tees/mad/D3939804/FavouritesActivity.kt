package uk.ac.tees.mad.D3939804

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.ac.tees.mad.D3939804.adapter.FavouritesAdapter
import uk.ac.tees.mad.D3939804.adapter.MainCategoryAdapter
import uk.ac.tees.mad.D3939804.adapter.SubCategoryAdapter
import uk.ac.tees.mad.D3939804.database.RecipeDatabase
import uk.ac.tees.mad.D3939804.databinding.ActivityFavouritesBinding
import uk.ac.tees.mad.D3939804.databinding.ActivityHomeBinding
import uk.ac.tees.mad.D3939804.entities.CategoryItems
import uk.ac.tees.mad.D3939804.entities.Favourites
import uk.ac.tees.mad.D3939804.entities.MealsItems
import java.util.Arrays

class FavouritesActivity : BaseActivity() {
    var arrFavs = ArrayList<MealsItems>()
    var favList = ArrayList<Favourites>()
    val mAuth = FirebaseAuth.getInstance();

    var favAdapter = FavouritesAdapter()

    private lateinit var binding: ActivityFavouritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val email = mAuth.currentUser?.email
        if(email!=null)
            getFavDataFromDbByUser(email)
        favAdapter.setClickListener(onCLickedFavItem)
    }

    private val onCLickedFavItem  = object : FavouritesAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@FavouritesActivity,DetailActivity::class.java)
            intent.putExtra("id",id)
            intent.putExtra("isFav",true)
            startActivity(intent)
        }
    }

    private fun getFavDataFromDbByUser(email:String){
        launch {
            withContext(Dispatchers.IO){
                var favs = RecipeDatabase.getDatabase(this@FavouritesActivity).recipeDao().getFavourites(email)
                favList = favs as ArrayList<Favourites>
                val mealIdList = favList.map{extractIntegersFromString(it.mealId)}
                val ml = listOf<Int>(1171,1172)
                var cat = RecipeDatabase.getDatabase(this@FavouritesActivity).recipeDao().getMealListById(ml)
                arrFavs = cat as ArrayList<MealsItems>
                favAdapter.setData(arrFavs)
            }
            withContext(Dispatchers.Main){
                binding.rvFavs.layoutManager = LinearLayoutManager(this@FavouritesActivity,LinearLayoutManager.HORIZONTAL,false)
                binding.rvFavs.adapter = favAdapter
            }
        }
    }

    fun extractIntegersFromString(input: String): Int {
        val regex = Regex("\\d+")
        val matchResult = regex.find(input)
        return matchResult?.value?.toIntOrNull() ?: 0
    }

}