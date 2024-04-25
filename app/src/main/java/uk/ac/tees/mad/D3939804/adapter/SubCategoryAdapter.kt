package uk.ac.tees.mad.D3939804.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uk.ac.tees.mad.D3939804.databinding.ItemRvSubCategoryBinding
import uk.ac.tees.mad.D3939804.entities.MealsItems

class SubCategoryAdapter: RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {

    var listener: SubCategoryAdapter.OnItemClickListener? = null
    var ctx :Context? = null
    var arrSubCategory = ArrayList<MealsItems>()
    class RecipeViewHolder(val binding: ItemRvSubCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(arrData : List<MealsItems>){
        arrSubCategory = arrData as ArrayList<MealsItems>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context
        val binding = ItemRvSubCategoryBinding.inflate(LayoutInflater.from(ctx), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrSubCategory.size
    }

    fun setClickListener(listener1: SubCategoryAdapter.OnItemClickListener){
        listener = listener1
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentItem = arrSubCategory[position]

        holder.binding.tvDishName.text = currentItem.strMeal

        Glide.with(ctx!!)
            .load(currentItem.strMealThumb)
            .into(holder.binding.imgDish)

        holder.binding.root.setOnClickListener {
            listener?.onClicked(currentItem.idMeal)
        }
    }
    interface OnItemClickListener{
        fun onClicked(id:String)
    }
}