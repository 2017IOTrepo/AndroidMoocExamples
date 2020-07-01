package org.nuc.camera

import android.graphics.BitmapFactory
import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.nuc.camera.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mCamera: Camera? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sf = binding.surfaceView.holder
        sf.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                mCamera?.also {
                    it.release()
                    mCamera = null
                }
            }

            override fun surfaceCreated(holder: SurfaceHolder?) {
            }
        })

        binding.openButton.setOnClickListener {
            mCamera = Camera.open()
            mCamera?.setPreviewDisplay(sf)
        }

        binding.takeButton.setOnClickListener {
            mCamera?.takePicture(null, null, Camera.PictureCallback {
                data, camera ->
                val pic = BitmapFactory.decodeByteArray(data, 0, data.size)
                binding.imgView.setImageBitmap(pic)

            }) ?: Toast.makeText(this, "请先打开相机!", Toast.LENGTH_LONG).show()
        }

        binding.closeButton.setOnClickListener {
            mCamera?.stopPreview()
            mCamera?.release()
            mCamera = null
        }
    }
}
