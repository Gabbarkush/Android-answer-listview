package com.example.demoofmvpandroid.data.RetrofitRequest

import android.app.Application
import android.content.Context
import com.example.demoofmvpandroid.View.Common.Common
import com.example.demoofmvpandroid.View.Common.Constants

class AppController: Application() {

     var dataManager: DataManager? = null
    private var mInstance: AppController? = null
    private var sharedPrefsHelper: SharedPrefsHelper? = null

    override fun onCreate() {
        super.onCreate()

            instance = this

            sharedPrefsHelper = SharedPrefsHelper(getSharedPreferences(Constants.SHARED_PREFERENCE_BASE, MODE_PRIVATE))
            val common = Common()

            dataManager = DataManager(sharedPrefsHelper!!, ServiceHandler(APIClient(sharedPrefsHelper!!),
                sharedPrefsHelper!!, common!!), common)

        }
    companion object {
        var instance: AppController? = null
            private set

        operator fun get(context: Context): AppController {
            return context.applicationContext as AppController
        }
    }
    fun getsharedPrefsHelper():SharedPrefsHelper{
        return sharedPrefsHelper!!
    }
    /*fun getDataManager(): DataManager? {
        return dataManager
    }*/
}