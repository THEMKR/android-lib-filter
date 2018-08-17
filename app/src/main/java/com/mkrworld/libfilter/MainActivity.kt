package com.mkrworld.libfilter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.mkrworld.libfilter.effect.coloreffect.GrayScale
import com.mkrworld.libfilter.effect.coloreffect.Invert
import com.mkrworld.libfilter.effect.conventionaleffect.Sketch
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
                imageView1.setImageBitmap(srcImage)
                val currentTimeMillis = System.currentTimeMillis()
                Log.e("MKR", "START  ${currentTimeMillis}")
                Log.e("MKR", "SRC  ${srcImage.config.name}")
                var intArray: IntArray = IntArray(srcImage.width * srcImage.height)
                srcImage.getPixels(intArray, 0, srcImage.width, 0, 0, srcImage.width, srcImage.height);

                val colorIntArray = GrayScale(intArray, PixelFormat.ARGB_8888).applyEffect()
                val colorEffectedBitmap = Bitmap.createBitmap(srcImage.width, srcImage.height, srcImage.config)
                colorEffectedBitmap.setPixels(colorIntArray, 0, srcImage.width, 0, 0, srcImage.width, srcImage.height)
                imageView2.setImageBitmap(colorEffectedBitmap)

                val conventionalIntArray = Sketch(intArray, PixelFormat.ARGB_8888, srcImage.width).applyEffect()
                val conventionalEffectedBitmap = Bitmap.createBitmap(srcImage.width, srcImage.height, srcImage.config)
                conventionalEffectedBitmap.setPixels(conventionalIntArray, 0, srcImage.width, 0, 0, srcImage.width, srcImage.height)
                imageView3.setImageBitmap(conventionalEffectedBitmap)

                val invertColorEffect = Invert(intArray, PixelFormat.ARGB_8888).applyEffect()
                val invertEffectedBitmap = Bitmap.createBitmap(srcImage.width, srcImage.height, srcImage.config)
                invertEffectedBitmap.setPixels(invertColorEffect, 0, srcImage.width, 0, 0, srcImage.width, srcImage.height)
                imageView4.setImageBitmap(invertEffectedBitmap)

                Log.e("MKR", "END  ${System.currentTimeMillis() - currentTimeMillis}")
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
