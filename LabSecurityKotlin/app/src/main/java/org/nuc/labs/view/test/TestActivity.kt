package org.nuc.labs.view.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import org.nuc.labs.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private val model by viewModels<TestViewModel>()
    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
