package org.nuc.labs.view.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import org.nuc.labs.databinding.ActivityReviewBinding

class ReviewActivity : AppCompatActivity() {
    private val model by viewModels<ReviewViewModel>()
    private lateinit var binding: ActivityReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
