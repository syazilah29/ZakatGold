// The package name must be the very first line in the file.
package com.example.zakatgold

// Imports should come after the package name.
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * This is the Activity for the "About" screen. It displays information about the app
 * and the developer.
 */
class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This line connects your Kotlin code to your XML layout file (activity_about.xml).
        setContentView(R.layout.activity_about)

        // Set the title for this screen's top Action Bar.
        supportActionBar?.title = "About"

        // This line adds the back arrow (up button) to the Action Bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * This function is called when the user taps the back arrow in the Action Bar.
     * It closes the current screen (AboutActivity) and returns to the previous one (MainActivity).
     */
    override fun onSupportNavigateUp(): Boolean {
        finish() // Closes this activity.
        return true
    }
}
