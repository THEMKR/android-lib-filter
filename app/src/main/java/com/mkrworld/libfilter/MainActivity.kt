package com.mkrworld.libfilter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mkrworld.libfilter.effect.BaseEffect
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Example of a call to a native method
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                Log.e("MKR", "PROCESS 1")
                val srcImage = getSrcImage() ?: return
                Log.e("MKR", "PROCESS 2")
                val overlayImage = getOverlayImage() ?: return
                Log.e("MKR", "PROCESS 3")
                var destImage = getDestImage(srcImage.width, srcImage.height) ?: return
                Log.e("MKR", "PROCESS 4")
                var currentTimeMillis = System.currentTimeMillis()
                Log.e("MKR", "START TIME : $currentTimeMillis")
                //BaseEffect().getMultiplyBitmap(srcImage, overlayImage, destImage, 1F)
                Log.e("MKR", "PROCESS 5")
                Log.e("MKR", "END TIME : ${System.currentTimeMillis() - currentTimeMillis}")
                imageView1.setImageBitmap(destImage)
                Log.e("MKR", "PROCESS 6")

                destImage = getDestImage(srcImage.width, srcImage.height) ?: return
                Log.e("MKR", "PROCESS 7")
                currentTimeMillis = System.currentTimeMillis()
                Log.e("MKR", "START TIME : $currentTimeMillis")
                //BaseEffect().getOverLayBitmap(srcImage, overlayImage, destImage, 1F)
                Log.e("MKR", "PROCESS 8")
                Log.e("MKR", "END TIME : ${System.currentTimeMillis() - currentTimeMillis}")
                imageView2.setImageBitmap(destImage)
                Log.e("MKR", "PROCESS 9")
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
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
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
            System.loadLibrary("native-lib")
        }
    }
}
