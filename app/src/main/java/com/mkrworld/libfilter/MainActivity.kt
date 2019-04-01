package com.mkrworld.libfilter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mkrworld.libfilter.enums.ColorFilter
import com.mkrworld.libfilter.enums.FilterCategory
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
                imageView2.setImageBitmap(FilterCreator.Builder(FilterCategory.COLOR).setSrcBitmap(srcImage).setFilterMatrixArrayList(ColorFilter.YELLOW.filterMatrixArrayList).build()?.applyEffect())
                imageView3.setImageBitmap(FilterCreator.Builder(FilterCategory.COLOR).setSrcBitmap(srcImage).setFilterMatrixArrayList(ColorFilter.SEPIA.filterMatrixArrayList).build()?.applyEffect())
                //imageView3.setImageBitmap(AndroidFilterCreator.Builder().setFilter(Filter.CONVENTIONAL_SKETCH).setSrcImage(srcImage).build().createFilteredBitmap())
                imageView4.setImageBitmap(FilterCreator.Builder(FilterCategory.COLOR).setSrcBitmap(srcImage).setFilterMatrixArrayList(ColorFilter.GRAY_SCALE.filterMatrixArrayList).build()?.applyEffect())
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
        return BitmapFactory.decodeResource(resources, R.drawable.mf, options)
    }

    fun getOverlayImage(): Bitmap? {
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.RGB_565
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
