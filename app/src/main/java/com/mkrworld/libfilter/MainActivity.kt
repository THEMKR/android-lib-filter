package com.mkrworld.libfilter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.mkrworld.libfilter.enums.Filter
import com.mkrworld.libfilter.utils.AndroidFilterCreator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Example of a call to a native method
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val srcImage = getSrcImage() ?: return
                Log.e("MKR", "SRC IMAGE : " + srcImage.config);

                val srcPixelArray = convertBitmapIntoPixelArray(srcImage)
                Log.e("MKR", "SRC IMAGE PIX : " + srcPixelArray.size)

                val overlayImage = getOverlayImage() ?: return
                Log.e("MKR", "DEST IMAGE : " + overlayImage.config)

                val destPixelArray = convertBitmapIntoPixelArray(srcImage)
                Log.e("MKR", "DEST IMAGE PIX : " + destPixelArray.size);

                for (i in 0 until srcPixelArray.size) {
                    if(srcPixelArray[i]!=destPixelArray[i]) {
                        Log.e("MKR", "SRC PIX[$i] : ${srcPixelArray[i]}   DES PIX[$i] : ${destPixelArray[i]}");
                    }
                }

                imageView1.setImageBitmap(srcImage)
                //imageView2.setImageBitmap(AndroidFilterCreator.Builder().setFilter(Filter.COLOR_GRAY_SCALE).setSrcImage(srcImage).build().createFilteredBitmap())
                //imageView3.setImageBitmap(AndroidFilterCreator.Builder().setFilter(Filter.COLOR_GREEN).setSrcImage(srcImage).build().createFilteredBitmap())
                //imageView4.setImageBitmap(AndroidFilterCreator.Builder().setFilter(Filter.COLOR_INVERT).setSrcImage(srcImage).build().createFilteredBitmap())
            }
        })
    }

    /**
     * Method ot convert the Bitmap into INT[] pixel Array
     *
     * @param bitmap
     * @return
     */
    private fun convertBitmapIntoPixelArray(bitmap: Bitmap): IntArray {
        val bitmapPixelArray = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(bitmapPixelArray, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        return bitmapPixelArray
    }

    fun getSrcImage(): Bitmap? {
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        return BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher, options)
    }

    fun getOverlayImage(): Bitmap? {
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.RGB_565
        return BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher, options)
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
