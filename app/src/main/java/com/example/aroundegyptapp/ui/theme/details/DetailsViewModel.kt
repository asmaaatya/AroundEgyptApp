
package com.example.aroundegyptapp.ui.theme.details
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegyptapp.data.model.details.Data
import com.example.aroundegyptapp.data.repo.DetailsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: DetailsRepo) : ViewModel() {
    private val _details = MutableStateFlow<Data?>(null)
    val details: StateFlow<Data?> = _details

    fun fetchDetails(id: String) {
        viewModelScope.launch {
            try {
                _details.value = repository.getSingleExperience(id)
            } catch (e: Exception) {
                   e.message
            }
        }
    }
}
