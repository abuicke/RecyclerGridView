package ie.moses.recyclergridview.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import ie.moses.recyclergridview.R
import ie.moses.recyclergridview.core.OnItemClickListener
import ie.moses.recyclergridview.util.Cantor
import ie.moses.recyclergridview.util.cantorFunction
import ie.moses.recyclergridview.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

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
                Pair("Donald Trump", "https://www.usmagazine.com/wp-content/uploads/2017/12/donald-trump-michael-flynn.jpg?w=800&h=800&crop=1"),
                Pair("Albert Einstein", "https://pbs.twimg.com/profile_images/3603569381/41aca9d1b837d559a5f9ad3680893ce1_400x400.jpeg"),
                Pair("Elon Musk", "https://static.dezeen.com/uploads/2018/01/elon-musk-public-transit-dezeen-2364-sq.jpg"),
                Pair("Edward Sharpe", "https://i1.wp.com/octopusmediaink.com/wp-content/uploads/2012/05/edward-sharpe-and-the-magnetic-zeros-200x200.jpg?resize=200%2C200"),
                Pair("Buddha", "https://omtimes.com/wp-content/uploads/2012/02/Buddha-explains-true-love_OM-Times.jpg"),
                Pair("Peter Griffin", "https://yt3.ggpht.com/a-/AJLlDp2kMWCG5Vd3jhQYFCUXwpeMkEL8dmP4QTd30g=s900-mo-c-c0xffffffff-rj-k-no"),
                Pair("Carl Jung", "https://dougdillon.com/wp-content/uploads/2012/05/carl-jung1.jpg"),
                Pair("Quentin Tarantino", "https://pmcdeadline2.files.wordpress.com/2012/12/quentin-tarantino__121228180602-200x200.jpg?w=240"),
                Pair("Rick Sanchez", "https://t2.genius.com/unsafe/300x300/https%3A%2F%2Fimages.genius.com%2F2d39515c9311de7bb2a4154adb1f68e6.400x400x1.jpg")
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = FriendsListAdapter(this, recyclerView, data, object : OnItemClickListener {
            override fun onItemClick(index: Int) {
                toast("Selected " + data[index].first)
            }
        })
        recyclerView.adapter = adapter

        Thread(Runnable {
            for (i in 1..6) {
                Thread.sleep(2000)
                runOnUiThread({ adapter.rowSize = i })
            }
        }).start()
    }

}
