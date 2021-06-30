package com.test.retirmentcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "testing", Toast.LENGTH_SHORT).show()
        AppCenter.start(
            application,
            "cbea57b8-922b-44a0-ad9f-10431c06f7b9",
            Analytics::class.java,
            Crashes::class.java
        )
        findViewById<Button>(R.id.calculate).setOnClickListener {
            //throw Exception("Something went wrong")
            // Crashes.generateTestCrash()
            try {

                val interestRate =
                    findViewById<TextView>(R.id.intrestRate).text.toString().toFloat()
                val currentAge = findViewById<TextView>(R.id.age).text.toString().toInt()
                val retirementAge =
                    findViewById<TextView>(R.id.retirmentAge).text.toString().toInt()
                val monthlySaving =
                    findViewById<TextView>(R.id.monthlySaving).text.toString().toFloat()
                val currentSaving =
                    findViewById<TextView>(R.id.currentSaving).text.toString().toFloat()

                val properties: HashMap<String, String> = HashMap<String, String>()
                properties["interest_rate"] = interestRate.toString()
                properties["currentAge"] = currentAge.toString()
                properties["retirementAge"] = retirementAge.toString()
                properties["monthlySaving"] = monthlySaving.toString()
                properties["currentSaving"] = currentSaving.toString()
                if (interestRate <= 0) {
                    Analytics.trackEvent("wrong_interest_rate", properties)
                }
                if (retirementAge <= currentAge) {
                    Analytics.trackEvent("wrong_age")
                }
                findViewById<TextView>(R.id.resultTextView).text  = "At this $interestRate your Saving is $monthlySaving"
            } catch (ex: Exception) {
                Analytics.trackEvent(ex.message)
            }
        }
    }
}
