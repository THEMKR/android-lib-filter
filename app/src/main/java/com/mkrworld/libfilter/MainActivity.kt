package com.mkrworld.libfilter

import android.graphics.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mkrworld.libfilter.enums.ColorFilter
import com.mkrworld.libfilter.enums.ConventionalFilter
import com.mkrworld.libfilter.enums.FilterCategory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Example of a call to a native method
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val bitmapSketch = FilterCreator.Builder(FilterCategory.CONVENTIONAL).setSrcBitmap(getSrcImage()!!).setFilterMatrixArrayList(ConventionalFilter.SKETCH.filterMatrixArrayList).build()?.applyEffect()
                val bitmapGrayScale = FilterCreator.Builder(FilterCategory.COLOR).setSrcBitmap(getSrcImage()!!).setFilterMatrixArrayList(ColorFilter.GRAY_SCALE.filterMatrixArrayList).build()?.applyEffect()
                val bitmap1 = FilterCreator.Builder(FilterCategory.CONVENTIONAL).setSrcBitmap(getSrcImage()!!).setFilterMatrixArrayList(ConventionalFilter.SKETCH.filterMatrixArrayList).build()?.applyEffect()
                val bitmap2 = FilterCreator.Builder(FilterCategory.CONVENTIONAL).setSrcBitmap(getSrcImage()!!).setFilterMatrixArrayList(ConventionalFilter.EMBOSS.filterMatrixArrayList).build()?.applyEffect()
                val bitmap3 = FilterCreator.Builder(FilterCategory.CONVENTIONAL).setSrcBitmap(getSrcImage()!!).setFilterMatrixArrayList(ConventionalFilter.LIGHTEN.filterMatrixArrayList).build()?.applyEffect()
                val bitmap4 = FilterCreator.Builder(FilterCategory.CONVENTIONAL).setSrcBitmap(getSrcImage()!!).setFilterMatrixArrayList(ConventionalFilter.MIKE_FAVORITE.filterMatrixArrayList).build()?.applyEffect()
                val bitmap5 = FilterCreator.Builder(FilterCategory.CONVENTIONAL).setSrcBitmap(getSrcImage()!!).setFilterMatrixArrayList(ConventionalFilter.SHARP.filterMatrixArrayList).build()?.applyEffect()
                val bitmap6 = FilterCreator.Builder(FilterCategory.CONVENTIONAL).setSrcBitmap(getSrcImage()!!).setFilterMatrixArrayList(ConventionalFilter.SOLID.filterMatrixArrayList).build()?.applyEffect()
                val bitmap7 = FilterCreator.Builder(FilterCategory.MERGE).setSrcBitmap(bitmapGrayScale!!).setOverlayBitmap(bitmapSketch!!).setMultiplier(0.8F).build()?.applyEffect()
                val bitmap8 = FilterCreator.Builder(FilterCategory.MERGE).setSrcBitmap(bitmapGrayScale!!).setOverlayBitmap(bitmapSketch!!).setMultiplier(0F).build()?.applyEffect()

//                imageView1.setImageBitmap(bitmap1)
//                imageView2.setImageBitmap(bitmap2)
//                imageView3.setImageBitmap(bitmap3)
//                imageView4.setImageBitmap(bitmap4)
//                imageView5.setImageBitmap(bitmap5)
//                imageView6.setImageBitmap(bitmap6)
//                imageView7.setImageBitmap(bitmap7)
//                imageView8.setImageBitmap(bitmap8)

                imageView1.setImageBitmap(bitmap7)
                imageView2.setImageBitmap(bitmap8)
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
        return BitmapFactory.decodeResource(resources, R.drawable.gh, options)
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
     * Method to convert Bitmap into ARGB_8888 format.
     *
     * @param bitmap
     * @param destWidth   New dest width of the Bitmap
     * @param destHeighgt New dest height of the Bitmap
     */
    private fun getARGB888Image(bitmap: Bitmap, destWidth: Int, destHeighgt: Int): Bitmap {
        if (bitmap.config == Bitmap.Config.ARGB_8888 && bitmap.width == destWidth && bitmap.height == destHeighgt) {
            return bitmap
        }
        val newARGBBitmap = Bitmap.createBitmap(destWidth, destHeighgt, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(newARGBBitmap)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.isFilterBitmap = true
        canvas.drawBitmap(bitmap, null, RectF(0f, 0f, destWidth.toFloat(), destHeighgt.toFloat()), paint)
        return newARGBBitmap
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
