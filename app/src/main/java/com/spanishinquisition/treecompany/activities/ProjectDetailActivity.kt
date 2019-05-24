package com.spanishinquisition.treecompany.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.models.projects.Module
import com.spanishinquisition.treecompany.models.projects.Phase
import com.spanishinquisition.treecompany.models.projects.Project
import com.spanishinquisition.treecompany.models.projects.Questionnaire
import com.spanishinquisition.treecompany.rest.getClient
import kotlinx.android.synthetic.main.activity_project_detail.view.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest

class ProjectDetailActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    private lateinit var title:TextView
    private lateinit var currentPhase:TextView
    private lateinit var projectProgress:ProgressBar
    private lateinit var beginDate:TextView
    private lateinit var endDate:TextView
    private lateinit var dropdownPhase:Spinner

    private lateinit var moduleType:TextView
    private lateinit var moduleTitle:TextView
    private lateinit var moduleDescription:TextView
    private lateinit var moduleButton:Button

    private lateinit var project: Project



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)*/
        

        initialiseViews()
        addEventHandlers()
    }

    private fun initialiseViews(){
        title = findViewById(R.id.projectTitle)
        currentPhase = findViewById(R.id.curPhaseValue)
        projectProgress = findViewById(R.id.projectProgress)
        beginDate = findViewById(R.id.valueStart)
        endDate = findViewById(R.id.valueEnd)

        moduleType = findViewById(R.id.labelModuleType)
        moduleTitle = findViewById(R.id.labelModuleTitle)
        moduleDescription = findViewById(R.id.labelModuleDescription)
        moduleButton = findViewById(R.id.btnEnterModule)



        dropdownPhase = findViewById(R.id.phaseList)
        dropdownPhase.onItemSelectedListener = this

        project = getProject(intent.getIntExtra("projectid",0))

        val phasenames = mutableListOf<String>()

        for (phase in project.phases) {
            phasenames.add(phase.description.toString())
        }


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, phasenames )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dropdownPhase.adapter = adapter

        title.text = project.title
        currentPhase.text = project.currentPhase!!.description.toString()
        beginDate.text = project.currentPhase!!.startDate
        endDate.text = project.currentPhase!!.endDate




    }

    private fun addEventHandlers(){

    }

   

    private fun getPhaseWithModule(phaseId:Int, projectId:Int){

    }

    private fun changeModuleinformation( phase:Phase){
        val call = getClient().GetModuleForPhase(phase.id)

        call.enqueue(object : Callback<Module> {
            override fun onResponse(call: Call<Module>, response: Response<Module>) {
                val module:Module = response.body()!!


                moduleTitle.text = module.title
                moduleDescription.text = project.currentPhase!!.description.toString()



            }

            override fun onFailure(call: Call<Module>, t: Throwable) {
                Toast.makeText(
                    this@ProjectDetailActivity,
                    "Geen moule gevonden",
                    Toast.LENGTH_LONG
                ).show()
            }

        })

    }


    private fun getProject(projectId:Int):Project{
        var project = Project(0,null,null, null,null,null,null,null,null,null,null,null,null, emptyList(),null,null)
        val call = getClient().GetById(projectId)

        call.enqueue(object: Callback<Project>{
            override fun onResponse(call: Call<Project>, response: Response<Project>) {
                project = response.body()!!


            }

            override fun onFailure(call: Call<Project>, t: Throwable) {
                Toast.makeText(
                    this@ProjectDetailActivity,
                    "Geen project gevonden",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        return project


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    
        changeModuleinformation(project.phases[0])

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val selectedphase:Phase = project.phases[position]


        changeModuleinformation(selectedphase)
    }




}