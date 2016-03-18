package com.photoapp.epi.myphotoapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Image saver service - forel_m and baussa_b
 */
public class ImageSaverService {

    public static Bitmap ByteArrayToBitmap(byte[] byteArray)
    {
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bmp;
    }

    /**
     * @param bitmap
     * Bitmap object from which you want to get bytes
     * @return byte[] array of bytes by compressing the bitmap to PNG format <br/>
     * null if bitmap passed is null (or) failed to get bytes from the
     * bitmap
     */
    public static byte[] convertBitmapToByteArray(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        } else {
            byte[] b = null;
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 0, byteArrayOutputStream);
                b = byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return b;
        }
    }


}
