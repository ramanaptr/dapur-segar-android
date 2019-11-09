package com.dapursegar.app.view.register

import androidx.lifecycle.ViewModel
import com.dapursegar.app.base.network.BaseResult
import com.dapursegar.app.network.response.register.RegisterData
import com.dapursegar.app.network.response.register.RegisterError
import io.reactivex.Flowable
import java.util.HashMap

class RegisterViewModel(private val repository: RegisterRepository) : ViewModel() {

    fun registerUser(
        payload: HashMap<String, String>
    ): Flowable<BaseResult<RegisterData, RegisterError>> {
        return repository.registerUser(payload)
    }

}
