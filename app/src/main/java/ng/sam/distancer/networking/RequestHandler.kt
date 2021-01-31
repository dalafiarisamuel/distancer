package ng.sam.distancer.networking

import androidx.lifecycle.LifecycleCoroutineScope
import retrofit2.Retrofit

open class RequestHandler constructor(lifecycleCoroutineScope: LifecycleCoroutineScope) {

    private var client: ApiInterface
    private var lifecycleCoroutineScope: LifecycleCoroutineScope

    init {
        val retrofit: Retrofit = RetrofitCompat.getInstance("false")
        this.client = retrofit.create(ApiInterface::class.java)
        this.lifecycleCoroutineScope = lifecycleCoroutineScope
    }


}