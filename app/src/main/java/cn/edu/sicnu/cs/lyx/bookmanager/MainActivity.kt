package cn.edu.sicnu.cs.lyx.bookmanager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private val bookList = ArrayList<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val root = findViewById<View>(R.id.root)
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.activity_title)

        createBooks()
        bookList.sortBy { it.price }

        findViewById<TextView>(R.id.tvSummary).text =
            getString(R.string.list_summary, bookList.size)

        val listView = findViewById<ListView>(R.id.lvBooks)
        listView.adapter = BookAdapter(this, bookList)
        listView.setOnItemClickListener { _, _, position, _ ->
            val book = bookList[position]
            val intent = Intent(this, BookDetailActivity::class.java)
            intent.putExtra(BookDetailActivity.EXTRA_TITLE, book.title)
            intent.putExtra(BookDetailActivity.EXTRA_AUTHOR, book.author)
            intent.putExtra(BookDetailActivity.EXTRA_PUBLISHER, book.publisher)
            intent.putExtra(BookDetailActivity.EXTRA_PRICE, book.price)
            startActivity(intent)
        }
    }

    private fun createBooks() {
        bookList.add(Book("第一行代码（第3版）", "郭霖", "人民邮电出版社", 79.00))
        bookList.add(Book("Java编程思想（第4版）", "Bruce Eckel", "机械工业出版社", 108.00))
        bookList.add(Book("算法导论（第3版）", "Thomas H. Cormen", "机械工业出版社", 128.00))
        bookList.add(Book("Android开发艺术探索", "任玉刚", "电子工业出版社", 69.00))
        bookList.add(Book("计算机网络（第7版）", "谢希仁", "电子工业出版社", 45.00))
        bookList.add(Book("Kotlin实战", "Dmitry Jemerov", "电子工业出版社", 89.00))
    }
}
