package id.dapursegar.app.helper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    private static final String TAG = FileUtils.class.getCanonicalName();

    public static File saveBitmapToFile(String fileName, Bitmap bm) {
        try {
            File file = getOutputMediaFile(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fos); //ok for JPEG and PNG
            return file;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveFile(String fileName, byte[] pData) {
        File file = getOutputMediaFile(fileName);
        if (file.exists())
            file.delete();

        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            if (pData != null) {
                fos.write(pData);
            }
            fos.flush();
            fos.close();
        } catch (FileNotFoundException fileNotFound) {
            fileNotFound.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static byte[] fullyReadFileToBytes(File f) {
        int size = (int) f.length();
        byte bytes[] = new byte[size];
        byte tmpBuff[] = new byte[size];
        try {
            FileInputStream fis = new FileInputStream(f);
            int read = fis.read(bytes, 0, size);
            if (read < size) {
                int remain = size - read;
                while (remain > 0) {
                    read = fis.read(tmpBuff, 0, remain);
                    System.arraycopy(tmpBuff, 0, bytes, size - remain, read);
                    remain -= read;
                }
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static Bitmap FileToBitmap(File file) {
        Bitmap iris = null;
        if (file.exists()) {
            iris = BitmapFactory.decodeFile(file.getPath());
        }
        return iris;
    }

    public static boolean create(String fileName, String jsonString) {
        File jsonFile = new File(getOutputMediaDirectory() + File.separator + fileName);

        try {
            jsonFile.createNewFile();
        } catch (IOException e) {
            Log.e(TAG, "create FileUtils failed");
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(jsonFile);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.flush();
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }
    }

    public static byte[] readFileResource(Class<?> resourceClass, String resourcePath) throws
            IOException {
        InputStream is = resourceClass.getClassLoader().getResourceAsStream(resourcePath);
        if (is == null)
            throw new IOException("cannot find resource: " + resourcePath);
        return getBytesFromInputStream(is);
    }


    public static byte[] getBytesFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        for (int len; (len = is.read(buffer)) != -1; ) {
            os.write(buffer, 0, len);
        }
        os.flush();
        return os.toByteArray();
    }

    public static File getOutputMediaFile(String filename) {
        File file = new File(getOutputMediaDirectory() + File.separator + filename);
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "FileUtils Exists: " + file.exists() + " isFile: " + file.isFile());
        return file;
    }

    public static File getTempMediaFile(String filename) {
        File file = new File(getTempMediaDirectory() + File.separator + filename);
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "FileUtils Exists: " + file.exists() + " isFile: " + file.isFile());
        return file;
    }

    private static String getTempMediaDirectory() {
        File mDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), Constants.TEMP);
        if (!mDirectory.exists()) {
            if (!mDirectory.mkdirs()) {
                Log.e(TAG, "getTempMediaFile: Failed to create directory");
                return null;
            }
        }
        return mDirectory.getPath();
    }

    public static String getOutputMediaDirectory() {
        File mDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), Constants.FOLDER);
        if (!mDirectory.exists()) {
            if (!mDirectory.mkdirs()) {
                Log.e(TAG, "getOutputMediaFile: Failed to create directory");
                return null;
            }
        }
        return mDirectory.getPath();
    }

    public static Uri getOutputUri() {
        File file = getOutputMediaFile(Constants.URI);
        return Uri.fromFile(file);
    }

    public static Uri getOutputUri(Activity act, String lastNameFile) {
        File file = null;
        try {
            File storageDir = act.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            file = File.createTempFile("InafisHandheld01_", lastNameFile + ".jpg", storageDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Uri.fromFile(file);
    }

    public static File uriToFile(Activity act, Uri selectedUri) {
        Bitmap bitmap = parseUriToBitmap(act, selectedUri);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        return new File(saveByteArrayToFile(byteArray));
    }

    private static Bitmap parseUriToBitmap(Activity act, Uri imageUri) {
        InputStream inputStream = null;
        try {
            inputStream = act.getContentResolver().openInputStream(imageUri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(inputStream); // convert to bitmap
    }

    private static String saveByteArrayToFile(byte[] byteArray) {
        String output = getOutputUri().getPath();
        FileOutputStream fos;
        try {
            if (output != null) {
                fos = new FileOutputStream(output);
                fos.write(byteArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}