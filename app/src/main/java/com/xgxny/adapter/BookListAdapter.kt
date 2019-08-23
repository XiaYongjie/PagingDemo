package com.xgxny.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xgxny.GlideApp
import com.xgxny.R
import com.xgxny.bean.Books

class BookListAdapter : PagedListAdapter<Books.BooksBean, BookListAdapter.BookViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Books.BooksBean>() {
            override fun areItemsTheSame(
                oldItem: Books.BooksBean,
                newItem: Books.BooksBean
            ): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: Books.BooksBean,
                newItem: Books.BooksBean
            ): Boolean = oldItem == newItem
        }
    }

    class BookViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_main_list, parent, false
        )
    ) {
        private var ivIcon = itemView.findViewById<ImageView>(R.id.iv_icon)
        private var tvIntroduction = itemView.findViewById<TextView>(R.id.tv_introduction)
        @SuppressLint("CheckResult")
        fun bindTo(book: Books.BooksBean) {
            GlideApp.with(itemView.context)
                .load(
                    String.format(
                        "https://japari.linovel.net/v1/image/jump?X-DEVICE=1&path=%s!min200",
                        book.cover
                    )
                )
                .into(ivIcon)
            tvIntroduction.text = book.name
        }
    }

}