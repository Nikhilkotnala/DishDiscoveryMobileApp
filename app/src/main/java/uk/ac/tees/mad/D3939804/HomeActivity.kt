package uk.ac.tees.mad.D3939804

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.ac.tees.mad.D3939804.adapter.FavouritesAdapter
import uk.ac.tees.mad.D3939804.adapter.MainCategoryAdapter
import uk.ac.tees.mad.D3939804.adapter.SubCategoryAdapter
import uk.ac.tees.mad.D3939804.database.RecipeDatabase
import uk.ac.tees.mad.D3939804.databinding.ActivityHomeBinding
import uk.ac.tees.mad.D3939804.entities.CategoryItems
import uk.ac.tees.mad.D3939804.entities.MealsItems

class HomeActivity : BaseActivity() {
    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<MealsItems>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()
    var favAdapter = FavouritesAdapter()

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataFromDb()
        mainCategoryAdapter.setClickListener(onCLicked)
        subCategoryAdapter.setClickListener(onCLickedSubItem)
        favAdapter.setClickListener(onCLickedFavItem)
        binding.btnFavs.setOnClickListener({
            val intent = Intent(this@HomeActivity,FavouritesActivity::class.java)
            startActivity(intent)
        })
    }

    private val onCLicked  = object : MainCategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getMealDataFromDb(categoryName)
        }
    }

    private val onCLickedSubItem  = object : SubCategoryAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@HomeActivity,DetailActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }

    private val onCLickedFavItem  = object : FavouritesAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@HomeActivity,DetailActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }

    private fun getDataFromDb(){
        launch {
            this.let {
                withContext(Dispatchers.IO){
                    var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory()
                    arrMainCategory = cat as ArrayList<CategoryItems>
                    arrMainCategory.reverse()
                    getMealDataFromDb(arrMainCategory[0].strcategory)
                    mainCategoryAdapter.setData(arrMainCategory)
                    binding.rvMainCategory.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                    binding.rvMainCategory.adapter = mainCategoryAdapter
                }
            }
        }
    }

    private fun getMealDataFromDb(categoryName:String){
        binding.tvCategory.text = "$categoryName Category"
        launch {
            withContext(Dispatchers.IO){
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getSpecificMealList(categoryName)
                arrSubCategory = cat as ArrayList<MealsItems>
                subCategoryAdapter.setData(arrSubCategory)
            }
            withContext(Dispatchers.Main){
                binding.rvSubCategory.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                binding.rvSubCategory.adapter = subCategoryAdapter
            }
        }
    }

    fun extractIntegersFromString(input: String): Int {
        val regex = Regex("\\d+")
        val matchResult = regex.find(input)
        return matchResult?.value?.toIntOrNull() ?: 0
    }

}