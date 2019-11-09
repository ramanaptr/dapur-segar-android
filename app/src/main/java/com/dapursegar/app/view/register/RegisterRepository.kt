package com.dapursegar.app.view.register

import com.dapursegar.app.base.extension.createFlowBackPress
import com.dapursegar.app.base.network.BaseResult
import com.dapursegar.app.network.apiService
import com.dapursegar.app.network.helper.getResponse
import com.dapursegar.app.network.loginUserEndpoint
import com.dapursegar.app.network.response.register.RegisterData
import com.dapursegar.app.network.response.register.RegisterError
import io.reactivex.Flowable
import java.util.*

class RegisterRepository {

    fun registerUser(
        payload: HashMap<String, String>
    ): Flowable<BaseResult<RegisterData, RegisterError>> {
        return createFlowBackPress {
            apiService.registerUser(loginUserEndpoint, payload).getResponse({
                body()?.apply { onNext(this) }
                onComplete()
            }, {
                printStackTrace()
                onError(this)
                onComplete()
            })
        }
    }

}
