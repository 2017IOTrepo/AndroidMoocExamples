package org.nuc.translate

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import org.nuc.translate.databinding.ActivityMainBinding
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var au: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.comfirmButton.setOnClickListener {
            Fuel.get(
                "http://fy.webxml.com.cn/webservices/EnglishChinese.asmx/TranslatorString",
                listOf("wordKey" to binding.input.text.toString())
            ).responseString { request, response, result ->
                result.fold({
                    val l = parseXML(it, "string")
                    binding.announceText.text = l[1]
                    binding.explainText.text = l[3]
                    au = l[4]
                }, {
                    Log.d("zhazha", it.message)
                })
            }

            Fuel.get(
                "http://fy.webxml.com.cn/webservices/EnglishChinese.asmx/TranslatorSentenceString",
                listOf("wordKey" to binding.input.text.toString())
            ).responseString { request, response, result ->
                result.fold({
                    binding.simpleText.text =
                        parseXML(it, "string").joinToString(separator = "\n")
                }, {
                    Log.d("zhazha", it.message)
                })
            }
        }

        binding.announceButton.setOnClickListener {
            au?.also {
                val mp = MediaPlayer()
                mp.setDataSource("http://fy.webxml.com.cn/sound/${au}")
                mp.prepare()
                mp.start()
            } ?: Toast.makeText(this, "没有读音", Toast.LENGTH_LONG).show()
        }


    }

    private fun parseXML(
        content: String,
        tagName: String
    ): List<String> {
        val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val parser = documentBuilder.parse(content.byteInputStream())
        val astr = parser.getElementsByTagName(tagName)

        val l = mutableListOf<String>()
        for (i in 0 until astr.length) {
            //遍历每个book节点的所有属性的集合
            l.add(astr.item(i).textContent)
        }

        return l
    }
}
