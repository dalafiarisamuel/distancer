package ng.sam.distancer.callbacks

import ng.sam.distancer.models.HumanLocation

interface HumanDistanceOnClick {
    fun onDistanceClicked(location: HumanLocation)
}