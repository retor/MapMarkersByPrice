package pro.retor.mapmarkerstest.data.api

import io.reactivex.Flowable
import pro.retor.mapmarkerstest.data.api.entity.Marker
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by retor on 06.02.2018.
 */
interface MarkersApi {
    @GET("/v{v}/{id}")
    fun getMarkers(@Path("v") version: Int = 2, @Path("id") packageId: String): Flowable<Response<List<Marker>>>
}