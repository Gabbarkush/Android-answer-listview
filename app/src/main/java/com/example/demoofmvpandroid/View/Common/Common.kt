package com.example.demoofmvpandroid.View.Common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.demoofmvpandroid.R
import com.example.demoofmvpandroid.data.RetrofitRequest.AppController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.common_alert_dialog.view.*
import java.io.File
import java.io.FileOutputStream
import java.util.*

class Common {

    private var progressDialog: Dialog? = null
    private var alertDialog: AlertDialog? = null


    fun showProgressDialog(c: Context) {
        try {
            progressDialog = Dialog(c)
            progressDialog!!.setContentView(R.layout.custom_progress_dialog)
            progressDialog!!.setCanceledOnTouchOutside(true)
            progressDialog!!.setCancelable(false)
            if (progressDialog!!.window != null) {
                progressDialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            progressDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun dismissDialog() {
        try {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showError(context: Context) {
        if (context == null) {
            showMsgOnUI("Please check your internet connection", AppController.instance)

        } else {
            showSneakBar(context, "Please check your internet connection")

        }
    }

    fun showMsgOnUI(msg: String, context: Context?) {
        val alert = AlertDialog.Builder(context!!).create()
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        alert.setCancelable(false)
        val view = inflater.inflate(R.layout.common_alert_dialog, null, false)
        alert.setView(view)
        view.msgAlert.text = msg
        view.okBtnAlert.setOnClickListener {
            alert.dismiss()
        }

        alert.show()
    }

    fun showSneakBar(context: Context?, message: String) {
        try {
            if (context != null) {
                val cxt = context as Activity
                val view = cxt.window.decorView.findViewById<View>(android.R.id.content)
                val snackbar = Snackbar
                    .make(view, message, Snackbar.LENGTH_LONG)
                val sbView = snackbar.view
                sbView.setBackgroundColor(cxt.resources.getColor(R.color.base_orange))
                val textView = sbView.findViewById<TextView>(R.id.snackbar_text)
                textView.setPadding(0, 0, 0, 0)
                textView.setTextColor(Color.WHITE)
                snackbar.show()
            }
        } catch (e: java.lang.Exception) {
            Toast.makeText(
                AppController.instance, message, Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun showToast(msg: String?, _context: Context?) {
        try {
            if (_context != null) {
                (_context as Activity).runOnUiThread(object : Runnable {
                    private var toast: Toast? = null
                    override fun run() {
                        toast = Toast.makeText(_context, msg, Toast.LENGTH_LONG)
                        toast?.show()
                    }
                })
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    companion object {

        // =================== INTERNET ===================//
        val isNetworkConnected: Boolean
            get() {
                val cm =
                    AppController.instance!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                if (wifiNetwork != null && wifiNetwork.isConnected) {
                    return true
                }

                val mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                if (mobileNetwork != null && mobileNetwork.isConnected) {
                    return true
                }

                val activeNetwork = cm.activeNetworkInfo

                return activeNetwork != null && activeNetwork.isConnected
            }
    }
    /*fun datePickerDialog(
        context: Context?,
        datePickerListener: DatePickerListener, mEdtStuPwd: EditText?
    ): DatePickerDialog? {
        val calendar = Calendar.getInstance()
        return DatePickerDialog(
            context!!, R.style.DialogTheme,
            { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                datePickerListener.onDateSetListener(
                    view,
                    year,
                    monthOfYear,
                    dayOfMonth,
                    mEdtStuPwd
                )
            },
            calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]
        )
    }*/

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    fun isValidPassword(password: String): Boolean {
        if(password.isEmpty())return false
        if (password.length < 8) return false
        if (password.filter { it.isDigit() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) return false
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false

        return true
    }

/*
    fun gettokenforfmc():String{
        var token = ""
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("Token", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                token = task.result

                // Log and toast
                val msg = "Token is .$token"
                Log.d("Token........", msg)
                //Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            })
        return  token
    }
*/

}