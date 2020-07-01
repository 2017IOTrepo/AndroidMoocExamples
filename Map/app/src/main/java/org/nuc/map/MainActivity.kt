package org.nuc.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MapView

class MainActivity : AppCompatActivity() {
    private lateinit var mMapView: MapView
    private lateinit var mBaiduMap: BaiduMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMapView = findViewById(R.id.bmapView)
        mBaiduMap = mMapView.map
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15.0f))
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.apply {
            add(0, 1, 0, "普通地图")
            add(0, 2, 0, "卫星地图")
            add(0, 3, 0, "交通地图(ON)")
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            1 -> mBaiduMap.mapType = BaiduMap.MAP_TYPE_NORMAL
            2 -> mBaiduMap.mapType = BaiduMap.MAP_TYPE_SATELLITE
            3 -> {
                item.title = if (mBaiduMap.isTrafficEnabled) "交通地图(OFF)" else "交通地图(ON)"
                mBaiduMap.isTrafficEnabled = !mBaiduMap.isTrafficEnabled
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
