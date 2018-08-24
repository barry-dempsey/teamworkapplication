package com.dempsey.teamworkapp.presenter

import com.dempsey.teamwork.data.model.Account
import com.dempsey.teamwork.data.net.ApiClient
import com.dempsey.teamwork.service.account.AccountRequest
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

  @Mock lateinit var okHttpClient: OkHttpClient

  @Mock lateinit var okHttpClientBuilder: OkHttpClient.Builder

  @Before
  fun `set up`() {
    MockitoAnnotations.initMocks(this)
    testScheduler = TestScheduler()
    presenter = LoginPresenter(view, testScheduler, testScheduler)
  }

  @Test
  fun `log in the user`() {
    Mockito.`when`(okHttpClientBuilder.build()).thenReturn(OkHttpClient())
    Mockito.`when`(apiClient.withPath(anyString())).thenReturn(mockApiClient().withPath("thePath"))
    Mockito.doReturn(Observable.just(mockAccount())).`when`(accountRequest).newAuthenticateRequest("apiKey")
    presenter.loginUser("api")
    testScheduler.triggerActions()
    Mockito.verify(view).showProgress()
    Mockito.verify(view).showSuccess()
    Mockito.verify(view).hideProgress()
  }

  @Test
  fun getProjects() {

  }

  private fun mockAccount() : Account =
      Account.newBuilder().id("accountId").build()

  private fun mockApiClient() : ApiClient =
      ApiClient.Builder().build()

  private fun mockOkHttpclient() : OkHttpClient.Builder =
      OkHttpClient().newBuilder()
}