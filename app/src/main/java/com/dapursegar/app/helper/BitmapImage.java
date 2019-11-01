package com.dapursegar.app.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.ExifInterface;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BitmapImage {

    private static final String TAG = BitmapImage.class.getSimpleName();

    public static Bitmap resizeBitmap(File file, Bitmap image, int size) {
        // resize photo if photo size > 500kb
        resetBitmap(image);
        if (file.length() > Constants.SMALL_SIZE) {
            return BitmapImage.resizePhotoQuality(file, size);
        }
        return image;
    }

    private static void resetBitmap(Bitmap image) {
        if (image != null) {
            image.isRecycled();
        }
    }

    private static void convert(byte[] raw, int[] pixels, Double exposureCompensation) {
        if (exposureCompensation != null) {
            double ec = exposureCompensation;

            for (int i = 0; i < raw.length; ++i) {
                int grey = Math.min((int) ((0xFF & raw[i]) * ec), 255);
                pixels[i] = 0xFF000000 | 0x010101 * grey;
            }
        } else {
            for (int i = 0; i < raw.length; ++i) {
                int grey = 0xFF & raw[i];
                pixels[i] = 0xFF000000 | 0x010101 * grey;
            }
        }
    }

    public static Bitmap toBitmap(Bitmap bitmap, int[] pixels, byte[] raw, Double exposureCompensation) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        convert(raw, pixels, exposureCompensation);

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        return bitmap;
    }

    public static Bitmap toBitmap(int width, int height, byte[] raw, Double exposureCompensation) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] pixels = new int[raw.length];

        return toBitmap(bitmap, pixels, raw, exposureCompensation);
    }

    public static Bitmap getBGR(YuvImage yuvImage) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, yuvImage.getWidth(), yuvImage.getHeight()), 100, outStream); // make JPG

        return BitmapFactory.decodeByteArray(outStream.toByteArray(), 0, outStream.size());
    }

    public static Bitmap rotate(Bitmap bmpImage, int degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        matrix.postScale(-1f, 1f);

        return Bitmap.createBitmap(bmpImage, 0, 0, bmpImage.getWidth(), bmpImage.getHeight(), matrix, true);
    }

    public static Bitmap crop(Bitmap source, int aspectWidth, int aspectHeight) {
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();

        int width = sourceWidth;
        int height = width * aspectHeight / aspectWidth;
        int x = 0;
        int y = (sourceHeight - height) / 2;

        if (height > sourceHeight) {
            height = sourceHeight;
            width = height * aspectWidth / aspectHeight;
            x = (sourceWidth - width) / 2;
            y = 0;
        }

        Bitmap bitmap;
        if (x != 0 || y != 0 || source.getWidth() != width || source.getHeight() != height) {
            bitmap = Bitmap.createBitmap(source, x, y, width, height);
            source.recycle();
        } else return source;

        return bitmap;
    }

    public static Bitmap crop(byte[] jpeg, int aspectWidth, int aspectHeight) {
        return crop(toBitmap(jpeg), aspectWidth, aspectHeight);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap toBitmap(byte[] jpeg, int width, int height) {
        if (jpeg != null) {
            BitmapFactory.Options options = null;

            if (width > 0 && height > 0) {
                options = new BitmapFactory.Options();

                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length, options);

                options.inJustDecodeBounds = false;
                options.inSampleSize = calculateInSampleSize(options, width, height);
            }

            Bitmap srcBitmap = BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length, options);
            int orientation = getOrientation(jpeg);

            if (srcBitmap != null && orientation != 0) {
                Matrix matrix = new Matrix();
                matrix.postRotate(orientation);
                Bitmap bitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);
                srcBitmap.recycle();
                return bitmap;
            }

            return srcBitmap;
        }

        return null;
    }

    public static Bitmap toBitmap(byte[] jpeg) {
        return toBitmap(jpeg, 0, 0);
    }

    public static int getOrientation(byte[] jpeg) {
        if (jpeg == null) {
            return 0;
        }
        int offset = 0;
        int length = 0;
        // ISO/IEC 10918-1:1993(E)
        while (offset + 3 < jpeg.length && (jpeg[offset++] & 0xFF) == 0xFF) {
            int marker = jpeg[offset] & 0xFF;
            // Check if the marker is a padding.
            if (marker == 0xFF) {
                continue;
            }
            offset++;
            // Check if the marker is SOI or TEM.
            if (marker == 0xD8 || marker == 0x01) {
                continue;
            }
            // Check if the marker is EOI or SOS.
            if (marker == 0xD9 || marker == 0xDA) {
                break;
            }
            // Get the length and check if it is reasonable.
            length = pack(jpeg, offset, 2, false);
            if (length < 2 || offset + length > jpeg.length) {
                Log.e(TAG, "Invalid length");
                return 0;
            }
            // Break if the marker is EXIF in APP1.
            if (marker == 0xE1 && length >= 8 &&
                    pack(jpeg, offset + 2, 4, false) == 0x45786966 &&
                    pack(jpeg, offset + 6, 2, false) == 0) {
                offset += 8;
                length -= 8;
                break;
            }
            // Skip other markers.
            offset += length;
            length = 0;
        }
        // JEITA CP-3451 Exif Version 2.2
        if (length > 8) {
            // Identify the byte order.
            int tag = pack(jpeg, offset, 4, false);
            if (tag != 0x49492A00 && tag != 0x4D4D002A) {
                Log.e(TAG, "Invalid byte order");
                return 0;
            }
            boolean littleEndian = (tag == 0x49492A00);
            // Get the offset and check if it is reasonable.
            int count = pack(jpeg, offset + 4, 4, littleEndian) + 2;
            if (count < 10 || count > length) {
                Log.e(TAG, "Invalid offset");
                return 0;
            }
            offset += count;
            length -= count;
            // Get the count and go through all the elements.
            count = pack(jpeg, offset - 2, 2, littleEndian);
            while (count-- > 0 && length >= 12) {
                // Get the tag and check if it is orientation.
                tag = pack(jpeg, offset, 2, littleEndian);
                if (tag == 0x0112) {
                    // We do not really care about type and count, do we?
                    int orientation = pack(jpeg, offset + 8, 2, littleEndian);
                    switch (orientation) {
                        case 1:
                            return 0;
                        case 3:
                            return 180;
                        case 6:
                            return 90;
                        case 8:
                            return 270;
                    }
                    Log.i(TAG, "Unsupported orientation");
                    return 0;
                }
                offset += 12;
                length -= 12;
            }
        }
        return 0;
    }

    private static int pack(byte[] bytes, int offset, int length, boolean littleEndian) {
        int step = 1;
        if (littleEndian) {
            offset += length - 1;
            step = -1;
        }
        int value = 0;
        while (length-- > 0) {
            value = (value << 8) | (bytes[offset] & 0xFF);
            offset += step;
        }
        return value;
    }

    public static Bitmap resizePhotoQuality(File f, int size) {
        try {
            Bitmap bm;
//            BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
//            btmapOptions.inSampleSize = 5;
            bm = BitmapFactory.decodeFile(f.getAbsolutePath());
//            if(mode==0 || mode ==2)bm = cropImage(bm);
//            bm = toGrayscale(bm);
            bm = imageOreintationValidator(bm, f.getAbsolutePath());
            bm = resizeBitmap(bm);
//            bm = BitmapImage.createScaledBitmap(bm, outWidth, outHeight, true);
            String path = f.getAbsolutePath();
            f.delete();
            OutputStream fOut = null;
            File file = new File(path);
            try {
                fOut = new FileOutputStream(file);
                bm.compress(Bitmap.CompressFormat.JPEG, size, fOut);
                fOut.flush();
                fOut.close();
                return bm;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Bitmap imageOreintationValidator(Bitmap bitmap, String path) {

        ExifInterface ei;
        try {
            ei = new ExifInterface(path);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    bitmap = rotateImage(bitmap, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    bitmap = rotateImage(bitmap, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    bitmap = rotateImage(bitmap, 270);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private static Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap bitmap = null;
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        try {
            bitmap = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                    matrix, true);
        } catch (OutOfMemoryError err) {
            err.printStackTrace();
        }
        return bitmap;
    }

    private static Bitmap resizeBitmap(Bitmap bm) {
        Bitmap result;
        final int maxSize = 1024;
        int outWidth;
        int outHeight;
        int inWidth = bm.getWidth();
        int inHeight = bm.getHeight();
        if (inWidth > inHeight) {
            outWidth = maxSize;
            outHeight = (inHeight * maxSize) / inWidth;
        } else {
            outHeight = maxSize;
            outWidth = (inWidth * maxSize) / inHeight;
        }
        result = Bitmap.createScaledBitmap(bm, outWidth, outHeight, true);
//        bm.recycle();
        return result;
    }

    public static int inverse(int degrees) {
        return 360 - (degrees % 360);
    }

    public static byte[] BitmapToByteArray(Bitmap bm) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static byte[] BitmapToLowQualityByteArray(Bitmap bm) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 50, stream);
        return stream.toByteArray();
    }

    public static Bitmap ByteArrayToBitmap(byte[] byteArray) {
        try {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static Bitmap Base64toBitmap(String base64) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
