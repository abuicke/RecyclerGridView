package ie.moses.recyclergridview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.friend_view.view.*

class FriendView(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.friend_view, this)
    }

    fun setName(name: CharSequence) {
        nameTextView.text = name
    }

    fun setProfilePic(profilePicUrl: String) {
        Glide.with(context)
                .load(profilePicUrl)
                .into(profilePicImageView)
    }

}