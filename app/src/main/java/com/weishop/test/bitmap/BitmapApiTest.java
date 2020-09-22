package com.weishop.test.bitmap;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.weishop.test.R;
import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitmapApiTest {
    private Context mContext;
    private int imgs[] = {R.drawable.img_shizi, R.drawable.img_xuehu};
    private int numbers = 0;
    private Bitmap mBitmap;


    public BitmapApiTest(Context context) {
        this.mContext = context;
    }

    /**
     * 正常bitmap
     * <p>
     * getByteCount 获取bitmap大小，
     * getAllocationByteCount 获取bitmap大小，api 19以上推荐使用 .当bitmap复用的时候，由于被复用的bitmap
     * 一定要比复用别的bitmap大，getAllocationByteCount始终返回被复用的bitmap的大小，在这种情况下是大于getByteCount的
     * getRowBytes 每一行占用的大小
     * <p>
     * xxhdpi right_answer对应的图片的大小是width=693,height=683,内存占用是size=1.8055687M
     * mdpi right_answer对应的图片的大小是width=2079,height=2049,内存占用是size=16.250118M
     * hdpi right_answer对应的图片的大小是width=1386,height=1366,内存占用是size=7.222275M
     */
    public Bitmap loadNormalBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.testyasuo,
                options);

        printBitmapProperty("normal", bitmap);
        return bitmap;
    }


    /**
     * 放在assets中的图片原封不动的加载，不会进行缩放 加载到内存中的大小 width*height*像素占用的大小
     */
    public void loadAssetBitmap() {
        try {
            AssetManager assetManager = mContext.getAssets();
            InputStream inputStream = assetManager.open("right_answer.png");
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
            printBitmapProperty("asset", bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * inPreferredConfig设置图片的存储格式，降低所占内存的大小
     * R.drawable.testyasuo 图片格式是jpg，如果不指定图片的存储格式，默认使用ARGB8888，占用4个字节。
     * 如果指定RGB_565 占用两个字节，内存减少一半。
     * <p>
     * 如果图片本身是png的图片，指定这个参数是不起作用的。
     */
    public Bitmap loadBitmapSetPreferred() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.testyasuo,
                options);
        printBitmapProperty("Preferred", bitmap);
        return bitmap;
    }

    /**
     * inSampleSize，可以实现 Bitmap 采样压缩，
     * 这个参数的含义是宽高维度上每隔 inSampleSize 个像素进行一次采集
     */
    public Bitmap loadBitmapSetInSampleSize() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.testyasuo,
                options);
        printBitmapProperty("InSampleSize", bitmap);
        return bitmap;
    }

    /**
     * inBitmap 复用之前创建好的bitmap的内存空间
     * 当mBitmap为null的时候先创建一张用来复用的bitmap，
     * 这张被复用的bitmap必须要比别的复用这个bitmap的图像大，否则会抛出异常
     * java.lang.IllegalArgumentException: Problem decoding into existing bitmap
     * 在 Android 4.4 版本之前，只能重用相同大小的 Bitmap 内存区域，
     * 4.4 之后你可以重用任何 Bitmap 的内存区域，只要这块内存比将要分配内存的 bitmap 大就可以。
     * <p>
     * 复用的时候必须 设置Options.inMutable =true
     * 否则不能复用，会报错Unable to reuse an immutable bitmap as an image decoder target
     *
     * @return
     */
    public Bitmap loadBitmapSetInBitmap() {
        if (mBitmap == null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inMutable = true;
            mBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                    R.drawable.bg_teampk_mohu, options);
            printBitmapProperty("reuse", mBitmap);
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inBitmap = mBitmap;
        options.inMutable = true;
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                imgs[numbers++ % 2],
                options);

        printBitmapProperty("InBitmap", mBitmap);
        return mBitmap;
    }


    /**
     * 压缩图片
     * 压缩图片质量并不能改变图片在内存中占用的大小，保存到文件中的时候会变小，主要用来上传图片图片，节省流量
     * 从存储器中加载被压缩过后的图片，加载到内存中的时候，所占用的内存和压缩之前一样，但是图片的画质会发生有损
     *
     * @return
     */
    public Bitmap loadCompressBitmap(Bitmap bitmap) {
        File externalFilesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(externalFilesDir, "compresstest.jpg");
        FileOutputStream baos = null;
        try {
            baos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int quality = 80;
        boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        LogUtils.d("compress=" + compress);

        Bitmap bitmapAfter =
                null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig= Bitmap.Config.RGB_565;
            bitmapAfter = BitmapFactory.decodeStream(new FileInputStream(file),null,options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        printBitmapProperty("压缩后", bitmapAfter);
        return bitmapAfter;
    }

    public void printBitmapProperty(String message, Bitmap bitmap) {
        int allocationByteCount = bitmap.getAllocationByteCount();
        int byteCount = bitmap.getByteCount();
        float allocationByteCountM = TestUtils.caculateMunit(allocationByteCount);
        float byteCountM = TestUtils.caculateMunit(byteCount);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        LogUtils.d(message + " byteCountM=" + byteCountM
                + ", allocationByteCountM=" + allocationByteCountM + ",width=" + width + ",height" +
                "=" + height);
    }


    public void testDrawableResource() {

    }


}
