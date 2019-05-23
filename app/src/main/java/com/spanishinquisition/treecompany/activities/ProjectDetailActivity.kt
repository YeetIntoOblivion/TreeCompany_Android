package com.spanishinquisition.treecompany.activities

import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView

class ProjectDetailActivity : AppCompatActivity() {

    private lateinit var title:TextView
    private lateinit var currenPhase:TextView
    private lateinit var projectProgress:ProgressBar
    private lateinit var beginDate:TextView
    private lateinit var endDate:TextView
    private lateinit var dropdownPhase:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)*/
        

        initialiseViews()
        addEventHandlers()
    }


}