package cn.edu.sicnu.cs.lyx.bookmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BookAdapter(
    private val context: android.content.Context,
    private val books: List<Book>
) : BaseAdapter() {

    override fun getCount(): Int = books.size

    override fun getItem(position: Int): Book = books[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        val book = books[position]
        holder.tvIndex.text = (position + 1).toString()
        holder.tvTitle.text = book.title
        holder.tvAuthor.text = "作者：" + book.author
        holder.tvPublisher.text = "出版社：" + book.publisher
        holder.tvPrice.text = book.priceText
        return view
    }

    private class ViewHolder(view: View) {
        val tvIndex: TextView = view.findViewById(R.id.tvIndex)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvAuthor: TextView = view.findViewById(R.id.tvAuthor)
        val tvPublisher: TextView = view.findViewById(R.id.tvPublisher)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
    }
}
