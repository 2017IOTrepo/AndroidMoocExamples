package org.nuc.stockpre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kittinunf.fuel.Fuel
import org.nuc.stockpre.databinding.ActivityMainBinding
import java.nio.charset.Charset
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewAdapter: StockAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showInfo("sh000001", binding.szdq, binding.szzf)
        showInfo("sz399001", binding.scdq, binding.sczf)
        showInfo("sz399006", binding.cydq, binding.cyzf)

        binding.addButton.setOnClickListener {
            val edt = binding.stockIdEditText.text.trim()
            if (edt.length == 0) {
                Toast.makeText(this, "can't empty!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            Fuel.get(
                "https://hq.sinajs.cn/list=${
                when (edt[0]) {
                    '6' -> "sh"
                    '0', '3' -> "sz"
                    else -> "sh"
                }
                }${edt}"
            )
                .responseString { request, response, result ->
                    result.fold({
                        val tmp = String(response.body().toByteArray(), Charset.forName("GB18030"))
                        val sps = tmp
                            .subSequence(tmp.indexOf("\"") + 1, tmp.lastIndexOf("\""))
                            .split(",")

                        val zd = sps[3].toFloat() - sps[2].toFloat()
                        val zf = zd / sps[2].toFloat() * 100
                        val name = sps[0]
                        val bj = sps[3]
                        Log.d("zhazha", tmp)

                        viewAdapter.addData(StockData(name, edt.toString(), bj, zf, zd))
                    }, {
                        Log.d("zhazha", it.message)
                    })
                }
        }

        viewAdapter = StockAdapter(mutableListOf(), this)
        manager = GridLayoutManager(this, 1)

        binding.rv.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = manager
        }
    }

    fun showInfo(code: String, dqText: TextView, zfText: TextView) =
        Fuel.get("https://hq.sinajs.cn/list=${code}")
            .responseString { request, response, result ->
                result.fold({
                    val sps = it
                        .subSequence(it.indexOf("\"") + 1, it.lastIndexOf("\""))
                        .split(",")

                    val zf = (sps[3].toFloat() - sps[2].toFloat()) / sps[2].toFloat() * 100

                    if (zf < 0) {
                        dqText.setTextColor(getColor(R.color.green))
                        zfText.setTextColor(getColor(R.color.green))
                    } else if (zf.equals(0)) {
                        dqText.setTextColor(getColor(R.color.nomarl))
                        zfText.setTextColor(getColor(R.color.nomarl))
                    } else {
                        dqText.setTextColor(getColor(R.color.red))
                        zfText.setTextColor(getColor(R.color.red))
                    }

                    dqText.text = sps[3]
                    zfText.text = DecimalFormat("0.00").format(zf)

                }, {
                    Log.d("zhazha", it.message)
                })
            }
}
