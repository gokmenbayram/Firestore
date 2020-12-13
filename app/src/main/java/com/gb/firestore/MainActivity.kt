package com.gb.firestore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gb.firestore.service.FirebaseClient
import com.irozon.alertview.AlertActionStyle
import com.irozon.alertview.AlertStyle
import com.irozon.alertview.AlertView
import com.irozon.alertview.objects.AlertAction
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val apiClient by lazy { FirebaseClient() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiClient.getAppVersionFromApi { isSuccess, response, message ->
            if (isSuccess) {

                var newVersion = response?.documents?.first()?.fields?.app_version?.version
                var appVersion = appVersion(this)

                if (appVersion != newVersion)
                    showAlert(this)
            }
        }
    }

    private fun appVersion(context: Context): String {
        var manager = context.packageManager
        var info = manager.getPackageInfo(context.packageName, 0)
        return info.versionName
    }


    private fun showAlert(context: AppCompatActivity) {

        val alert = AlertView("Force Update", "Uygulamanızın yeni versiyonu mevcuttur. Güncellemek için tıklayın.", AlertStyle.DIALOG)
        alert.addAction(AlertAction("Güncelle", AlertActionStyle.POSITIVE, {
            // Play Store'a yonlendir.
        }))
        alert.addAction(AlertAction("Vazgeç", AlertActionStyle.NEGATIVE, {


        }))

        alert.show(context)
    }
}