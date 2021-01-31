package ng.sam.distancer.callbacks

import ng.sam.distancer.models.HumanLocation

fun interface OnContextMenuClicked {
    fun onContextMenuClicked(location: HumanLocation, position:Int)
}