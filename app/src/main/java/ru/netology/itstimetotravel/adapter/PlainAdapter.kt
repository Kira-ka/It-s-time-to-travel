package ru.netology.itstimetotravel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.itstimetotravel.R
import ru.netology.itstimetotravel.databinding.CardFlightBinding
import ru.netology.itstimetotravel.dto.Plain

interface OnInteractionListener {
    fun onLike(plain: Plain) {}
    fun onSpecificPlain(plain: Plain) {}
}

class PlainAdapter(private val onInteractionListener: OnInteractionListener) :
    ListAdapter<Plain, PlainViewHolder>(PlainDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlainViewHolder {
        val binding = CardFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlainViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PlainViewHolder, position: Int) {
        val plain = getItem(position)
        holder.bind(plain)
    }
}

class PlainViewHolder(
    private val binding: CardFlightBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(plain: Plain) {
        binding.apply {
            from.text = plain.from
            to.text = plain.to
            departureDate.text = plain.departureDate.toString()
            returnDate.text = plain.returnDate.toString()
            price.text = plain.price.toString()
            like.isChecked = plain.likedByMe
            like.text = R.string.ruble.toString()
            like.setOnClickListener {
                onInteractionListener.onLike(plain)
            }
            root.setOnClickListener {
                onInteractionListener.onSpecificPlain(plain)
            }

        }
    }

}

class PlainDiffCallback : DiffUtil.ItemCallback<Plain>() {
    override fun areItemsTheSame(oldItem: Plain, newItem: Plain): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Plain, newItem: Plain): Boolean {
        return oldItem == newItem
    }

}
