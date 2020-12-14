package fragments

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class Fragment4 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_4, container, false)
        val data: TextView = v.findViewById(R.id.data)
        data.setOnClickListener {
            val intent = Intent(this@Fragment4.context, GetData::class.java)
            startActivity(intent)
        }

        val saving: TextView = v.findViewById(R.id.saving)
        saving.setOnClickListener {
            val intent = Intent(this@Fragment4.context, GetSaving::class.java)
            startActivity(intent)
        }

        //**********Logout
        var auth = FirebaseAuth.getInstance()
        auth.addAuthStateListener {
            if (auth.currentUser == null){
                activity?.finish()
            }
        }
        val logout: Button = v.findViewById(R.id.btn_logout)
        logout.setOnClickListener {
            Toast.makeText(v.context, "Logging Out...", Toast.LENGTH_LONG).show()
            auth.signOut()

        }


        return v
    }




}
