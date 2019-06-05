package com.spanishinquisition.treecompany.fragments


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_account.view.*

class AccountFragment : Fragment() {
    private lateinit var logout: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        initialiseViews(view)
        addEventHandlers()
        return view
    }

    private fun initialiseViews(view: View) {
        logout = view.logoutTv
    }

    private fun addEventHandlers() {
        logout.setOnClickListener{
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(this.context)
        }
        builder.apply {
            setMessage("Ben je zeker dat je wilt afmelden?")
            setTitle("Afmelden")
            setPositiveButton("Ja") { _, _ -> activity!!.getSharedPreferences(getString(R.string.app_pref), Context.MODE_PRIVATE).edit()
                .putBoolean("accountLoggedIn", false).apply()
                startActivity(Intent(this.context, MainActivity::class.java))
                activity!!.finish() }
            setNegativeButton("Nee") { dialog, _ ->
                dialog.cancel()
            }
            setCancelable(false)
        }
        builder.create().show()
    }
}
