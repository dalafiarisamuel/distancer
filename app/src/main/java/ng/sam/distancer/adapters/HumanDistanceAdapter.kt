package ng.sam.distancer.adapters

import android.view.*
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ng.sam.distancer.R
import ng.sam.distancer.callbacks.HumanDistanceOnClick
import ng.sam.distancer.callbacks.OnContextMenuClicked
import ng.sam.distancer.models.HumanLocation

class HumanDistanceAdapter(
    private val menuItemClick: OnContextMenuClicked,
    private val case: (it: HumanLocation) -> Unit
) :
    RecyclerView.Adapter<HumanDistanceAdapter.Holder>() {


    private val diffUtil = object : DiffUtil.ItemCallback<HumanLocation>() {
        override fun areItemsTheSame(oldItem: HumanLocation, newItem: HumanLocation): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HumanLocation, newItem: HumanLocation): Boolean {
            return oldItem.createdDate == newItem.createdDate
        }
    }

    private val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        return Holder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.human_location_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(differ.currentList[position], position)

    override fun getItemCount(): Int = differ.currentList.size


    fun addData(data: List<HumanLocation>) {
        differ.submitList(data)
    }

    inner class Holder(itemBinding: View) : RecyclerView.ViewHolder(itemBinding),
        View.OnCreateContextMenuListener {

        private val rootLayout = itemBinding.findViewById<RelativeLayout>(R.id.root_layout)
        private val endLocationTv = itemBinding.findViewById<TextView>(R.id.end_location)
        private val startLocationTv = itemBinding.findViewById<TextView>(R.id.start_location)
        private val createdDateTv = itemBinding.findViewById<TextView>(R.id.create_date)
        private lateinit var data: HumanLocation
        private var positionSelected = -1

        fun bind(data: HumanLocation, position: Int) {
            positionSelected = position
            this.data = data
            rootLayout.setOnClickListener { case(data) }
            endLocationTv.text = data.resolvedEndLocation
            startLocationTv.text = data.resolvedStartLocation
            createdDateTv.text = data.createdDate
            rootLayout.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {

            menu?.setHeaderTitle(rootLayout.context.getString(R.string.location_options))
            menu?.add(Menu.NONE, 1, 1, rootLayout.context.getString(R.string.delete))
                ?.also {
                    it.setOnMenuItemClickListener {
                        menuItemClick.onContextMenuClicked(data, positionSelected)
                        true
                    }
                }

            menu?.add(Menu.NONE, 2, 2, rootLayout.context.getString(R.string.plot))
                ?.also {
                    it.setOnMenuItemClickListener {
                        menuItemClick.onContextMenuClicked(data, positionSelected)
                        true
                    }
                }


        }
    }
}