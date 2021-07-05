package com.gtaskmanager.multiplelanuage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.gtaskmanager.multiplelanuage.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnChangeLanguage.setOnClickListener {
            showSelectLanguageDialog()
        }
        mBinding.btnlogin.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)


        }

    }

    private fun showSelectLanguageDialog() {

        val listofLanguages = arrayOf("اردو","हिंदी","عربى","English")
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle("Select Language")
        mBuilder.setSingleChoiceItems(listofLanguages,-1){ dialog, which->
            when (which) {
                0 -> {
                    setLocate("ur")
                    recreate()
                }
                1 -> {

                    setLocate("hi")
                    recreate()
                }
                2->{

                    setLocate("ar")
                    recreate()
                }
                3->{

                    setLocate("en")
                    recreate()
                }
            }
            dialog.dismiss()
        }
       val mDialog = mBuilder.create()
        mDialog.show()
    }

    private fun setLocate(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        baseContext.resources.updateConfiguration(configuration,baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings",Context.MODE_PRIVATE).edit()
        editor.putString("My Language",language)
        editor.apply()
    }
//    private fun loadLocate(){
//        val sharedPreferences = getSharedPreferences("Settings",Activity.MODE_PRIVATE)
//        val lang = sharedPreferences.getString("My Language","")
//        setLocate(lang!!)
//    }
}