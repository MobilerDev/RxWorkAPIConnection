package com.mustafasuleymankinik.androidworkmanagerretrofitrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.gson.Gson
import com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.model.Continent
import com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.model.Country
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    var country: Country? =null
    var continent:Continent?=null
    val gson = Gson()
    lateinit var picassoImage:PicassoImage
    lateinit var workManager:WorkManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        headTextView.visibility= View.INVISIBLE
        continentMTextView.visibility=View.INVISIBLE
        countryMTextView.visibility=View.INVISIBLE
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val constraints2=Constraints.Builder().setRequiresCharging(true).build()

        val countryWorkRequest= OneTimeWorkRequestBuilder<CountryWorker>().setConstraints(constraints).build()
        val continentWorkRequest= OneTimeWorkRequestBuilder<ContinentWorker>().setConstraints(constraints).build()
        val continentModifierWorkRequest= OneTimeWorkRequestBuilder<ContinentModifierWorker>().setConstraints(constraints2).build()
        val countryModifierWorkRequest= OneTimeWorkRequestBuilder<CountryModifierWorker>().setConstraints(constraints2).build()
        val detailWorkRequest= OneTimeWorkRequestBuilder<DetailWorker>().setConstraints(constraints2).build()

        workManager= WorkManager.getInstance(this)

        workManager.beginWith(listOf(countryWorkRequest,continentWorkRequest)).then(listOf(continentModifierWorkRequest,countryModifierWorkRequest))
            .then(detailWorkRequest)
            .enqueue()

        workManager.getWorkInfoByIdLiveData(countryWorkRequest.id).observe(this, Observer {
            country = gson.fromJson<Country>(
                it.outputData.getString(COUNTRY_WORKER_OUTPUT),
                Country::class.java
            )
            picassoImage= PicassoImage()
            picassoImage.imageDownload(countryImageView,country?.country_logo)
            countryTextView.text = "Country Name: ${country?.country_name} \n" +
                    "Province Name: ${country?.country_province} \n" +
                    "Population(M): ${country?.country_population} "

        })
        workManager.getWorkInfoByIdLiveData(continentWorkRequest.id).observe(this, Observer {
            continent=gson.fromJson(it.outputData.getString(CONTINENT_WORKER_OUTPUT),Continent::class.java)
            picassoImage=PicassoImage()
            picassoImage.imageDownload(continentImageView,continent?.continent_logo)

            continentTextView.text="Continent Name: ${continent?.continent_name} \n" +
                    "Province Name: ${continent?.continent_province} \n" +
                    "Population(M): ${continent?.continent_population} "
        })

        workManager.getWorkInfoByIdLiveData(countryModifierWorkRequest.id).observe(this, Observer {
            headTextView.visibility = View.VISIBLE
            countryMTextView.visibility = View.VISIBLE
            //countryMTextView.text = it.outputData.getFloat(COUNTRY_MODIFIER_WORKER_OUTPUT,0f).toString()

        })

        workManager.getWorkInfoByIdLiveData(continentModifierWorkRequest.id).observe(this, Observer {
            continentMTextView.visibility=View.VISIBLE
            //continentMTextView.text=it.outputData.getFloat(CONTINENT_MODIFIER_WORKER_OUTPUT,0f).toString()
        })

        workManager.getWorkInfoByIdLiveData(detailWorkRequest.id).observe(this, Observer {

            var arrayListBar = ArrayList<BarEntry>()
            var floatArray = it.outputData.getFloatArray(DETAIL_WORKER_OUTPUT)

            val floatVal1 = floatArray?.component1()
            val floatVal2 = floatArray?.component2()
            floatVal2?.let { it1 -> BarEntry(1f, it1) }?.let { it2 -> arrayListBar.add(it2) }
            floatVal1?.let { it1 -> BarEntry(2f, it1) }?.let { it2 -> arrayListBar.add(it2) }

            val barDataSet = BarDataSet(arrayListBar, "Chart")
            val barData: BarData = BarData()
            barData.addDataSet(barDataSet)
            chart.data = barData
            chart.invalidate()


        })

    }
}