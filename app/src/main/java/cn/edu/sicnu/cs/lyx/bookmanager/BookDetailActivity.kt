package cn.edu.sicnu.cs.lyx.bookmanager

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import java.util.Locale

class BookDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_AUTHOR = "extra_author"
        const val EXTRA_PUBLISHER = "extra_publisher"
        const val EXTRA_PRICE = "extra_price"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_detail)

        val root = findViewById<View>(R.id.root)
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.detail_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val title = intent.getStringExtra(EXTRA_TITLE).orEmpty()
        val author = intent.getStringExtra(EXTRA_AUTHOR).orEmpty()
        val publisher = intent.getStringExtra(EXTRA_PUBLISHER).orEmpty()
        val price = intent.getDoubleExtra(EXTRA_PRICE, 0.0)

        findViewById<TextView>(R.id.tvCover).text = title.take(1)
        findViewById<TextView>(R.id.tvTitle).text = title
        findViewById<TextView>(R.id.tvAuthor).text = author
        findViewById<TextView>(R.id.tvPublisher).text = publisher
        findViewById<TextView>(R.id.tvPrice).text =
            String.format(Locale.CHINA, "%.2f 元", price)
    }
}
