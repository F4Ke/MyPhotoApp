package com.photoapp.epi.myphotoapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by Matthieu on 19/03/2016.
 */
public class CustomFilterService {

    // not fast
    public static Bitmap toSephia(Bitmap bmpOriginal)
    {
        int width, height, r,g, b, c, gry;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();
        int depth = 20;

        Bitmap bmpSephia = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bmpSephia);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setScale(.3f, .3f, .3f, 1.0f);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        canvas.drawBitmap(bmpOriginal, 0, 0, paint);
        for(int x=0; x < width; x++) {

            for(int y=0; y < height; y++) {
                c = bmpOriginal.getPixel(x, y);

                r = Color.red(c);
                g = Color.green(c);
                b = Color.blue(c);

                gry = (r + g + b) / 3;
                r = g = b = gry;

                r = r + (depth * 2);
                g = g + depth;

                if(r > 255) {
                    r = 255;
                }
                if(g > 255) {
                    g = 255;
                }
                bmpSephia.setPixel(x, y, Color.rgb(r, g, b));
            }
        }

        return bmpSephia;
    }

    // faster
    public Bitmap ConvertToSepia(Bitmap sampleBitmap){
        float[] sepMat = {
                0.3930000066757202f,
                0.7689999938011169f,
                0.1889999955892563f,
                0,
                0,
                0.3490000069141388f,
                0.6859999895095825f,
                0.1679999977350235f,
                0,
                0,
                0.2720000147819519f,
                0.5339999794960022f,
                0.1309999972581863f,
                0,
                0,
                0,
                0,
                0,
                1,
                0,
                0,
                0,
                0,
                0,
                1};

        ColorMatrix sepiaMatrix = new ColorMatrix();
        sepiaMatrix.set(sepMat);

        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(sepiaMatrix);
        Bitmap rBitmap = sampleBitmap.copy(Bitmap.Config.ARGB_8888, true);

        Paint paint = new Paint();
        paint.setColorFilter(colorFilter);

        Canvas myCanvas = new Canvas(rBitmap);
        myCanvas.drawBitmap(rBitmap, 0, 0, paint);
        return rBitmap;
    }
}
