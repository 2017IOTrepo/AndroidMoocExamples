package org.nuc.stockpre

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.nuc.stockpre.databinding.ItemBinding
import org.w3c.dom.Text

data class StockData(
    val name: String,
    val id: String,
    val bj: String,
    val zf: Float,
    val zd: Float
)

class StockAdapter(
    private val myDataset: MutableList<StockData>,
    private val context: Context
) :
    RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    class StockViewHolder : RecyclerView.ViewHolder {
        var binding: ItemBinding

        constructor(iView: ItemBinding) : super(iView.root) {
            binding = iView
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StockViewHolder {
        return StockViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    fun addData(data: StockData) {
        myDataset.add(data)
        notifyDataSetChanged()
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val data = myDataset[position]
        holder.binding.nameText.text = data.name
        holder.binding.idText.text = data.id
        holder.binding.bjText.text = data.bj
        holder.binding.zfText.text = data.zf.toString()
        holder.binding.zdText.text = data.zd.toString()
        if (data.zd < 0) {
            setColor(
                context.getColor(R.color.green),
                holder.binding.nameText,
                holder.binding.idText,
                holder.binding.bjText,
                holder.binding.zfText,
                holder.binding.zdText
            )
        } else if (data.zd.equals(0)) {
            setColor(
                context.getColor(R.color.nomarl),
                holder.binding.nameText,
                holder.binding.idText,
                holder.binding.bjText,
                holder.binding.zfText,
                holder.binding.zdText
            )
        } else {
            setColor(
                context.getColor(R.color.red),
                holder.binding.nameText,
                holder.binding.idText,
                holder.binding.bjText,
                holder.binding.zfText,
                holder.binding.zdText
            )
        }

        Log.d("zhazha", data.toString())
    }

    fun setColor(resid: Int, vararg views: TextView) {
        for (v in views) {
            v.setTextColor(resid)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}