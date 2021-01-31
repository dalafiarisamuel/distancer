package ng.sam.distancer.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ng.sam.distancer.adapters.HumanDistanceAdapter
import ng.sam.distancer.callbacks.OnContextMenuClicked
import ng.sam.distancer.databinding.ActivityMainBinding
import ng.sam.distancer.models.HumanLocation

class MainActivity : BaseActivity(), OnContextMenuClicked {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.activity = this

        makeActivityFullScreen(this.window)
        changeStatusBarColorToTransparent(this.window)
        setLightStatusBar(binding.titleLayout)

        val adapter = HumanDistanceAdapter(this) {

            Toast.makeText(
                this@MainActivity,
                it.createdDate ?: "Human Location",
                Toast.LENGTH_LONG
            ).show()
        }.also {

            it.addData(dummyData())
        }


        val layoutMan = LinearLayoutManager(this)
        binding.locationRecyclerview.layoutManager = layoutMan
        binding.locationRecyclerview.adapter = adapter

        binding.noResultDisplay.visibility = View.GONE
        binding.locationRecyclerview.visibility = View.VISIBLE


    }

    fun addLocation() {
        startActivity(MapsActivity.start(this))
    }

    private fun dummyData(): MutableList<HumanLocation> {
        val list: MutableList<HumanLocation> = ArrayList()
        (1..10).forEach {
            val data = HumanLocation()
            data.resolvedEndLocation = "7$it Shola Bus stop, Idumota Lagos, Nigeria"
            data.resolvedStartLocation = "${it}6 Muritala Mohammad Way, Ikeja"
            data.createdDate = "24th May, 2019. 10:34:15pm"
            list.add(data)
        }

        return list
    }

    override fun onContextMenuClicked(location: HumanLocation, position: Int) {
        Toast.makeText(
            this@MainActivity,
            location.resolvedEndLocation ?: "Human Location",
            Toast.LENGTH_LONG
        ).show()
    }


}