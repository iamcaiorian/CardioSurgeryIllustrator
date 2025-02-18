package com.example.cardiosurgeryillustrator.view_models.patient.feedback

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.core.services.FeedbackService
import com.example.cardiosurgeryillustrator.models.patient.feedback.FeedbackRequest
import kotlinx.coroutines.launch
import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance

class FeedbackViewModel : ViewModel() {
    private val feedbackService: FeedbackService = RetrofitInstance.feedbackService

    fun submitFeedback(feedback: FeedbackRequest, onComplete: () -> Unit) {
        viewModelScope.launch {
            try {
                feedbackService.saveFeedback(feedback)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                onComplete()
            }
        }
    }
}