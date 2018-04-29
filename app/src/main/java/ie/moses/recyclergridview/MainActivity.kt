package ie.moses.recyclergridview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = listOf(
                Pair("Bertie Ahern", "https://imgix.ranker.com/node_img/29/572787/original/bertie-ahern-politicians-photo-1?w=280&h=280&fit=crop&crop=faces&q=50&fmt=jpg"),
                Pair("Blindboy Boatclub", "https://thumbnailer.mixcloud.com/unsafe/300x300/extaudio/c/6/3/a/fcc8-72e7-432c-98a4-ef05bad2d5f7"),
                Pair("Jordan Peterson", "https://i0.wp.com/www.nationalreview.com/wp-content/uploads/2017/06/peterson-jordan-2-1.jpg?resize=300%2C300&ssl=1"),
                Pair("Hunter S. Thompson", "https://aaforringer.files.wordpress.com/2018/01/huntersthompson3.jpg?w=300&h=300"),
                Pair("David Choe", "https://vz.cnwimg.com/thumbc-300x300/wp-content/uploads/2014/10/GettyImages-450993059.jpg"),
                Pair("Batman", "https://pbs.twimg.com/profile_images/874661809139073025/X8yzIhNy_400x400.jpg"),
                Pair("Donald Trump", "https://www.usmagazine.com/wp-content/uploads/2017/12/donald-trump-michael-flynn.jpg?w=800&h=800&crop=1")
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = GridRecyclerViewAdapter(this, data, object : OnItemClickListener {
            override fun onItemClick(index: Int) {
                toast("Selected " + data[index].first)
            }
        })
        adapter.rowSize = 2
        recyclerView.adapter = adapter
    }

}
