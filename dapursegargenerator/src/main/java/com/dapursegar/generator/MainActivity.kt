package com.dapursegar.generator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tozny.crypto.android.AesCbcWithIntegrity
import java.io.UnsupportedEncodingException
import java.security.GeneralSecurityException

class MainActivity : AppCompatActivity() {

    private var key: AesCbcWithIntegrity.SecretKeys? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        aesEncrypt()
    }

    private fun aesEncrypt() {
        try {
            val superKey: AesCbcWithIntegrity.SecretKeys =
                AesCbcWithIntegrity.generateKeyFromPassword(
                    getString(R.string.key),
                    getString(R.string.salt),
                    100
                )
            val keyStr = AesCbcWithIntegrity.keyString(superKey)
            key = AesCbcWithIntegrity.keys(keyStr)

            Log.e("TAG", encryptedResult())
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }

    private fun encryptedResult(): String {
        val domain = AesCbcWithIntegrity.encrypt("http://dapursegar.id", key)

        var encryptedResult = "\n\nEncrypted String: "
        encryptedResult += "\n domain: $domain"
        return encryptedResult
    }
}