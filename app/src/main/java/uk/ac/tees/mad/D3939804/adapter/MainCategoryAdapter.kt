package uk.ac.tees.mad.D3939804.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uk.ac.tees.mad.D3939804.databinding.ItemRvSubCategoryBinding
import uk.ac.tees.mad.D3939804.entities.CategoryItems

class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.RecipeViewHolder>() {

    var listener: OnItemClickListener? = null
    var ctx: Context? = null
    var arrMainCategory = ArrayList<CategoryItems>()
    class RecipeViewHolder(val binding: ItemRvSubCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(arrData : List<CategoryItems>){
        arrMainCategory = arrData as ArrayList<CategoryItems>
    }

    fun setClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context
        val binding = ItemRvSubCategoryBinding.inflate(LayoutInflater.from(ctx), parent, false)
        return MainCategoryAdapter.RecipeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrMainCategory.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentItem = arrMainCategory[position]

        holder.binding.tvDishName.text = currentItem.strcategory

        Glide.with(ctx!!)
            .load(currentItem.strcategorythumb)
            .into(holder.binding.imgDish)

        holder.binding.root.setOnClickListener {
            listener?.onClicked(currentItem.strcategory)
        }
    }

    interface OnItemClickListener{
        fun onClicked(categoryName:String)
    }
}