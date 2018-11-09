package com.test.postalcode

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    private var etPostalCode: EditText? = null
    private var etAddress1: EditText? = null
    private var etAddress2: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPostalCode = findViewById<View>(R.id.etPostalCode) as EditText
        etAddress1 = findViewById<View>(R.id.etAddress1) as EditText
        etAddress2 = findViewById<View>(R.id.etAddress2) as EditText

        val btnSearch = findViewById<View>(R.id.btSearch) as Button
        btnSearch.setOnClickListener { goToPostPage() }
    }


    private fun goToPostPage() {
        val intent = Intent(this, DaumPostalCodeActivity::class.java)
        startActivityForResult(intent, 0)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0) {
                etPostalCode!!.setText(data!!.getStringExtra("zipCode"))
                etAddress1!!.setText(data.getStringExtra("address"))
                etAddress2!!.setText("")
                etAddress2!!.requestFocus()
            }
        }
    }

}
