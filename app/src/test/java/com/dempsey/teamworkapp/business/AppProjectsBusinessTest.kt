package com.dempsey.teamworkapp.business

import com.dempsey.teamwork.Teamwork
import com.dempsey.teamwork.data.model.Projects
import com.dempsey.teamwork.service.project.ProjectRequest
import io.reactivex.Observable
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AppProjectsBusinessTest {

    private lateinit var business: AppProjectsBusiness

    @Spy private lateinit var teamwork: Teamwork

    @Mock private lateinit var projectRequest: ProjectRequest

    @Before
    fun `set up`() {
        business = AppProjectsBusiness()
    }

    @Test
    fun `test we get projects`() {
        val result = Observable.just(Projects())
        Mockito.`when`(Teamwork.projectRequest()).thenReturn(projectRequest)
        Mockito.`when`(projectRequest.newGetAllProjectsRequest()).thenReturn(result)
        assertNotNull(business.getProjects())
    }

}