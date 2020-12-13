package fragments

import android.content.ContentValues.TAG
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.DateTime
import java.time.LocalDateTime
import java.util.*


class Fragment1 : Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_1, container, false)

        val textDisplay: TextView = v.findViewById(R.id.textView7)

        val db = FirebaseFirestore.getInstance()

        //**********Query total income and display to a TextView : textDisplay
        val query1 = db.collection("data")

        val c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        //fix error of month
        month = if (month == 12){
            1
        } else{
            month + 1
        }
        //fix month_end
        var month2: Int = 0
        month2 = if (month == 12){
            1
        } else{
            month + 1
        }
        //fix year_end
        var year2 = year
        if(month2 == 1){
            year2 += 1
        }
        val start = "$year/$month/01"
        val end = "$year2/$month2/01"
        //array [start,end)
        //query of total income in month current
        query1
            .whereEqualTo("type", "Income")
            .whereGreaterThanOrEqualTo("date", start)
            .whereLessThan("date", end)
            .get()
            .addOnSuccessListener { documents ->
                var total = 0.0
                for (document in documents) {
                    val itemCost = document.getDouble("amount")!!
                    total += itemCost
                }
                textDisplay.text = total.toString()
            }

            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
        return v
    }
}