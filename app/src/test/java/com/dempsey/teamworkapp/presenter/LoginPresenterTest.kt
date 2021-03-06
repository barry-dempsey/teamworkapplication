package com.dempsey.teamworkapp.presenter

import com.dempsey.teamwork.data.model.Account
import com.dempsey.teamwork.data.net.ApiClient
import com.dempsey.teamwork.service.account.AccountRequest
import com.dempsey.teamworkapp.presenter.login.LoginContract
import com.dempsey.teamworkapp.presenter.login.LoginPresenter
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

  private lateinit var presenter: LoginPresenter

  private lateinit var testScheduler: TestScheduler

  @Mock lateinit var view: LoginContract.View

  @Mock lateinit var accountRequest: AccountRequest

  @Mock lateinit var apiClient: ApiClient

  @Mock lateinit var okHttpClientBuilder: OkHttpClient.Builder

  @Before
  fun `set up`() {
    MockitoAnnotations.initMocks(this)
    testScheduler = TestScheduler()
    presenter = LoginPresenter(view, testScheduler, testScheduler)
  }

  @Test
  fun `log in the user`() {
    val client = OkHttpClient.Builder().build()
    Mockito.`when`(okHttpClientBuilder.build()).thenReturn(client)
    Mockito.`when`(apiClient.withPath(anyString())).thenReturn(mockApiClient().withPath(""))
    Mockito.doReturn(Observable.just(mockAccount())).`when`(accountRequest).newAuthenticateRequest("apiKey")
    presenter.loginUser("apiKey")
    testScheduler.triggerActions()
    Mockito.verify(view).showLoading()
    Mockito.verify(view).showSuccess()
    Mockito.verify(view).hideLoading()
  }

  private fun mockAccount() : Account =
      Account.newBuilder().id("accountId").build()

  private fun mockApiClient() : ApiClient =
      ApiClient.Builder().build()

  private fun mockOkHttpclient() : OkHttpClient.Builder =
      OkHttpClient().newBuilder()
}