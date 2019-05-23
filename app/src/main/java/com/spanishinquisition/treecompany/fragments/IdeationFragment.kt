package com.spanishinquisition.treecompany.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.IdeationAdapter
import com.spanishinquisition.treecompany.models.projects.Ideation
import com.spanishinquisition.treecompany.rest.getClient
import kotlinx.android.synthetic.main.fragment_ideation.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 *
 */
class IdeationFragment : Fragment() {


    private lateinit var listener: IdeationAdapter.OnIdeationSelectionListener


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IdeationAdapter.OnIdeationSelectionListener)
            listener = context
        else
            throw RuntimeException("Parent context of IdeationFragment is incorrect")
    }

    var projectId: Int = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ideation, container, false)

        view.rvIdeation.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = IdeationAdapter(/*context,*/ listener)
        }
        GetIdeations(projectId)

        return view
    }

    fun GetIdeations(projectId: Int) {
        val call = getClient().GetIdeations(projectId)
        call.enqueue(object : Callback<List<Ideation>> {

            override fun onResponse(call: Call<List<Ideation>>, response: Response<List<Ideation>>) {
                val ideations = response.body()
                (view!!.rvIdeation.adapter as IdeationAdapter).ideations =
                    ideations!!.toTypedArray()
            }

            override fun onFailure(call: Call<List<Ideation>>, t: Throwable) {
                Toast.makeText(
                    this@IdeationFragment.context,
                    getString(R.string.connection_title),
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }


}
