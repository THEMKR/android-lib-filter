package com.mkrworld.libfilter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.mkrworld.libfilter.effect.coloreffect.GrayScale
import com.mkrworld.libfilter.enums.PixelFormat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Example of a call to a native method
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val srcImage = getSrcImage() ?: return
                Log.e("MKR", "SRC  ${srcImage.config.name}")
                var intArray: IntArray = IntArray(srcImage.width * srcImage.height)
                srcImage.getPixels(intArray, 0, srcImage.width, 0, 0, srcImage.width, srcImage.height);
                Log.e("MKR", "SRC srcImage.width:${srcImage.width}   srcImage.height:${srcImage.height}   srcImage.byteCount:${srcImage.byteCount}  srcImage.rowBytes:${srcImage.rowBytes}  " + intArray.size)
                val applyEffect = GrayScale(intArray, PixelFormat.ARGB_8888).applyEffect()
                val newEffectedBitmap = Bitmap.createBitmap(srcImage.width, srcImage.height, srcImage.config)
                newEffectedBitmap.setPixels(applyEffect, 0, srcImage.width, 0, 0, srcImage.width, srcImage.height)
                imageView1.setImageBitmap(newEffectedBitmap)
                imageView2.setImageBitmap(srcImage)
            }
        })
    }

    fun getSrcImage(): Bitmap? {
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        return BitmapFactory.decodeResource(resources, R.drawable.mf, options)
    }

    fun getOverlayImage(): Bitmap? {
        val options = BitmapFactory.Options()
        //options.inPreferredConfig = Bitmap.Config.ARGB_8888
        return BitmapFactory.decodeResource(resources, R.drawable.o, options)
    }


    fun getDestImage(width: Int, height: Int): Bitmap? {
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            //System.loadLibrary("native-lib")
        }
    }
}
