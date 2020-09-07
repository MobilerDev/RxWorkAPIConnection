package com.mustafasuleymankinik.androidworkmanagerretrofitrxjava

import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso

class PicassoImage {
    fun imageDownload(imageView:ImageView,path:String?)
    {
        Picasso.get()
            .load(path.toString())
            .resize(150,150)
            .into(imageView)

    }


}