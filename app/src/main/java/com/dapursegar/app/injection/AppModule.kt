package com.dapursegar.app.injection

import com.dapursegar.app.view.basket.BasketRepository
import com.dapursegar.app.view.basket.BasketViewModel
import com.dapursegar.app.view.detail.DetailProductRepository
import com.dapursegar.app.view.detail.DetailProductViewModel
import com.dapursegar.app.view.detail.EditDetailProductRepository
import com.dapursegar.app.view.detail.EditDetailProductViewModel
import com.dapursegar.app.view.forgot.ForgotPasswordRepository
import com.dapursegar.app.view.forgot.ForgotPasswordViewModel
import com.dapursegar.app.view.forgot.ResetPasswordRepository
import com.dapursegar.app.view.forgot.ResetPasswordViewModel
import com.dapursegar.app.view.login.LoginRepository
import com.dapursegar.app.view.login.LoginViewModel
import com.dapursegar.app.view.main.bill.BillRepository
import com.dapursegar.app.view.main.bill.BillViewModel
import com.dapursegar.app.view.main.favorite.FavoriteRepository
import com.dapursegar.app.view.main.favorite.FavoriteViewModel
import com.dapursegar.app.view.main.home.HomepageRepository
import com.dapursegar.app.view.main.home.HomepageViewModel
import com.dapursegar.app.view.main.profile.ProfileRepository
import com.dapursegar.app.view.main.profile.ProfileViewModel
import com.dapursegar.app.view.main.profile.edit.EditProfileRepository
import com.dapursegar.app.view.main.profile.edit.EditProfileViewModel
import com.dapursegar.app.view.payment.PaymentDetailRepository
import com.dapursegar.app.view.payment.PaymentDetailViewModel
import com.dapursegar.app.view.register.RegisterRepository
import com.dapursegar.app.view.register.RegisterViewModel
import com.dapursegar.app.view.search.SearchRepository
import com.dapursegar.app.view.search.SearchResultRepository
import com.dapursegar.app.view.search.SearchResultViewModel
import com.dapursegar.app.view.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by Ramana on 03-Nov-19.
 */

internal fun getAllModules(): MutableList<Module> {
    val modules = mutableListOf<Module>()
    modules.add(loginModule)
    modules.add(registerModule)
    modules.add(forgotPasswordModule)
    modules.add(resetPasswordModule)
    modules.add(homepageModule)
    modules.add(billModule)
    modules.add(favoriteModule)
    modules.add(profileModule)
    modules.add(editProfileModule)
    modules.add(basketModule)
    modules.add(detailProductModule)
    modules.add(editDetailProductModule)
    modules.add(paymentModule)
    modules.add(searchModule)
    modules.add(searchResultModule)
    return modules
}

private val loginModule = module {
    single { LoginRepository() }
    viewModel { LoginViewModel(get()) }
}

private val registerModule = module {
    single { RegisterRepository() }
    viewModel { RegisterViewModel(get()) }
}

private val forgotPasswordModule = module {
    single { ForgotPasswordRepository() }
    viewModel { ForgotPasswordViewModel(get()) }
}

private val resetPasswordModule = module {
    single { ResetPasswordRepository() }
    viewModel { ResetPasswordViewModel(get()) }
}

private val homepageModule = module {
    single { HomepageRepository() }
    viewModel { HomepageViewModel(get()) }
}

private val billModule = module {
    single { BillRepository() }
    viewModel { BillViewModel(get()) }
}

private val favoriteModule = module {
    single { FavoriteRepository() }
    viewModel { FavoriteViewModel(get()) }
}

private val profileModule = module {
    single { ProfileRepository() }
    viewModel { ProfileViewModel(get()) }
}

private val editProfileModule = module {
    single { EditProfileRepository() }
    viewModel { EditProfileViewModel(get()) }
}

private val basketModule = module {
    single { BasketRepository() }
    viewModel { BasketViewModel(get()) }
}

private val detailProductModule = module {
    single { DetailProductRepository() }
    viewModel { DetailProductViewModel(get()) }
}

private val editDetailProductModule = module {
    single { EditDetailProductRepository() }
    viewModel { EditDetailProductViewModel(get()) }
}

private val paymentModule = module {
    single { PaymentDetailRepository() }
    viewModel { PaymentDetailViewModel(get()) }
}

private val searchModule = module {
    single { SearchRepository() }
    viewModel { SearchViewModel(get()) }
}

private val searchResultModule = module {
    single { SearchResultRepository() }
    viewModel { SearchResultViewModel(get()) }
}