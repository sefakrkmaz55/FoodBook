package com.korkmaz.yemektarifi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.korkmaz.yemektarifi.databinding.CategoryItemBinding
import com.korkmaz.yemektarifi.pojo.Category

class CategoriesAdapter() : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    // List to store categories / Kategorileri depolamak için liste
    private var categoriesList = ArrayList<Category>()

    // Click listener for category item / Kategori öğesi için tıklama dinleyici
    var onItemClick: ((Category) -> Unit)? = null

    // Update the list of categories / Kategori listesini güncelle
    fun setCategoryList(categoriesList: List<Category>) {
        this.categoriesList = categoriesList as ArrayList<Category>
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Create a view holder / Bir görünüm tutucu oluştur
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    // Get the number of categories / Kategori sayısını al
    override fun getItemCount(): Int {
        return categoriesList.size
    }

    // Bind data to the view holder / Veriyi görünüm tutucusuna bağla
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        // Load category image using Glide / Glide kullanarak kategori resmini yükle
        Glide.with(holder.itemView).load(categoriesList[position].strCategoryThumb)
            .into(holder.binding.imgCategory)

        // Set category name / Kategori adını ayarla
        holder.binding.tvCategoryName.text = categoriesList[position].strCategory

        // Set click listener for the item / Öğe için tıklama dinleyicisini ayarla
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(categoriesList[position])
        }
    }
}
