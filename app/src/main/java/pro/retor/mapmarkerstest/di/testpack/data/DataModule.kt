package pro.retor.mapmarkerstest.di.testpack.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pro.retor.mapmarkerstest.data.api.errors.ErrorAdapter
import pro.retor.mapmarkerstest.data.api.errors.ErrorHandler
import pro.retor.mapmarkerstest.data.api.errors.ServerError
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by retor on 19.01.2018.
 */
@Module
class DataModule(private val portal:String) {

    @Singleton
    @Provides
    fun providesHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(HttpLoggingInterceptor())
        client.connectTimeout((90 * 1000).toLong(), TimeUnit.MILLISECONDS)
        client.readTimeout((90 * 1000).toLong(), TimeUnit.MILLISECONDS)
        client.retryOnConnectionFailure(true)
        return client.build()
    }

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(ServerError::class.java, ErrorAdapter())
                .setLenient()
                .create()
    }

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(checkUrl(portal))
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    private fun checkUrl(url: String): String {
        return if (url.startsWith("http://") || url.startsWith("https://"))
            url
        else
            "http://$url"
    }

    @Singleton
    @Provides
    fun providesErrorHandler(retrofit: Retrofit): ErrorHandler {
        return ErrorHandler(retrofit.responseBodyConverter<ServerError>(ServerError::class.java, arrayOfNulls<Annotation>(0)))
    }
}