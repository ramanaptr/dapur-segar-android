package com.dapursegar.app.network.helper

import android.util.Log
import com.dapursegar.app.BuildConfig.SECRET_KEY
import com.dapursegar.app.BuildConfig.SECRET_SALT
import com.tozny.crypto.android.AesCbcWithIntegrity
import java.io.UnsupportedEncodingException
import java.security.GeneralSecurityException
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by Ramana on 05-Oct-19.
 */

@Throws(UnsupportedEncodingException::class, GeneralSecurityException::class)
private fun decrypt(crypt: String): String {
    return AesCbcWithIntegrity.decryptString(
        AesCbcWithIntegrity.CipherTextIvMac(crypt),
        AesCbcWithIntegrity.generateKeyFromPassword(SECRET_KEY, SECRET_SALT, 100)
    )
}

fun getData(encryptedStr: String): String {
    try {
        return decrypt(encryptedStr)
    } catch (e: GeneralSecurityException) {
        e.printStackTrace()
        Log.e("SecureService", "Could not getData decryptString $encryptedStr")
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
        Log.e("SecureService", "Could not getData decryptString $encryptedStr")
    }
    return ""
}

val executor = ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, LinkedBlockingQueue())