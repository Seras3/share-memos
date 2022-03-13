import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.getBitmap
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.sharememos.R
import com.example.sharememos.models.ItemsViewModel
import java.util.*
import kotlin.collections.ArrayList

class GalleryAdapter(private val mList: List<ItemsViewModel>, private val context: Context) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>(),
    Filterable {

    var filterModelList = ArrayList<ItemsViewModel>()

    init {
        filterModelList = mList as ArrayList<ItemsViewModel>
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterModelList = mList as ArrayList<ItemsViewModel>
                } else {
                    val resultList = ArrayList<ItemsViewModel>()
                    for (row in mList) {
                        if (row.name.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    filterModelList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterModelList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterModelList = results?.values as ArrayList<ItemsViewModel>
                notifyDataSetChanged()
            }

        }
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_card_view, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        val thumbnail: Bitmap = context.contentResolver.loadThumbnail(
            itemsViewModel.contentUri, Size(640, 480), null)

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageBitmap(thumbnail)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = itemsViewModel.name

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return filterModelList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}
