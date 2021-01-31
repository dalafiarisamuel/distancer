package ng.sam.distancer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ng.sam.distancer.dao.HumanDistanceDao
import ng.sam.distancer.database.Database
import ng.sam.distancer.models.HumanLocation

class HumanDistanceViewModel(application: Application) : AndroidViewModel(application) {

    private var database: Database = Database.getInstance(application)
    private var humanDistanceDao: HumanDistanceDao

    init {
        humanDistanceDao = database.humanDistanceDao()
    }

    fun insertHumanDistance(humanLocation: HumanLocation) {
        viewModelScope.launch(Dispatchers.IO) {
            humanDistanceDao.insertHumanLocation(humanLocation)
        }
    }

    fun cleanTable() {
        viewModelScope.launch(Dispatchers.IO) { humanDistanceDao.cleanTable() }
    }

    suspend fun getCount() = humanDistanceDao.getCount()

    fun updateHumanLocation(humanLocation: HumanLocation) {
        viewModelScope.launch(Dispatchers.IO) {
            humanDistanceDao.updateHumanLocation(humanLocation)
        }
    }

    fun getHumanLocationLive(): LiveData<MutableList<HumanLocation>> =
        humanDistanceDao.getHumanLocationLive()
}