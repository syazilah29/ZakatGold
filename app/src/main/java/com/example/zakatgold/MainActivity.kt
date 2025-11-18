package com.example.zakatgold

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var etWeight: EditText
    private lateinit var etGoldValue: EditText
    private lateinit var rgType: RadioGroup
    private lateinit var tvTotalValue: TextView
    private lateinit var tvZakatPayable: TextView
    private lateinit var tvTotalZakat: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Gold Zakat Calculator"

        etWeight = findViewById(R.id.etWeight)
        etGoldValue = findViewById(R.id.etGoldValue)
        rgType = findViewById(R.id.rgType)
        tvTotalValue = findViewById(R.id.tvTotalValue)
        tvZakatPayable = findViewById(R.id.tvZakatPayable)
        tvTotalZakat = findViewById(R.id.tvTotalZakat)

        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val btnReset = findViewById<Button>(R.id.btnReset)

        btnCalculate.setOnClickListener { calculateZakat() }
        btnReset.setOnClickListener { resetInputs() }
    }

    /**
     * This function inflates (creates) the menu with your icons
     * on the top ActionBar.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * This function is called whenever an item in the ActionBar menu is tapped.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareUrl() // Call the function to handle sharing
                true // Indicate that we have handled the click
            }
            R.id.action_about -> {
                // Create an Intent to open the AboutActivity
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent) // Launch the new screen
                true // Indicate that we have handled the click
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * This function creates and launches the share dialog.
     */
    private fun shareUrl() {
        val shareMessage = "Check out this awesome Zakat Gold Calculator app! " +
                "You can find its source code here: ${getString(R.string.app_github_url)}"

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareMessage)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share URL via"))
    }

    // --- Your existing functions for calculation and reset are below ---

    private fun calculateZakat() {
        val weightText = etWeight.text.toString()
        val goldValueText = etGoldValue.text.toString()
        if (weightText.isEmpty() || goldValueText.isEmpty()) {
            if (weightText.isEmpty()) etWeight.error = "Please enter weight"
            if (goldValueText.isEmpty()) etGoldValue.error = "Please enter gold value"
            return
        }
        val weight = weightText.toDouble()
        val goldValue = goldValueText.toDouble()
        val isKeep = findViewById<RadioButton>(R.id.rbKeep).isChecked
        val X = if (isKeep) 85.0 else 200.0
        val totalValue = weight * goldValue
        var payableWeight = weight - X
        if (payableWeight < 0) payableWeight = 0.0
        val zakatPayableValue = payableWeight * goldValue
        val totalZakat = zakatPayableValue * 0.025
        tvTotalValue.text = "Total Value: RM %.2f".format(totalValue)
        tvZakatPayable.text = "Zakat Payable Value: RM %.2f".format(zakatPayableValue)
        tvTotalZakat.text = "Total Zakat (2.5%): RM ${"%.2f".format(totalZakat)}"
    }

    private fun resetInputs() {
        etWeight.text.clear()
        etGoldValue.text.clear()
        etWeight.error = null
        etGoldValue.error = null
        rgType.check(R.id.rbKeep)
        tvTotalValue.text = "Total Value: RM 0.00"
        tvZakatPayable.text = "Zakat Payable Value: RM 0.00"
        tvTotalZakat.text = "Total Zakat (2.5%): RM 0.00"
        etWeight.requestFocus()
    }
}
