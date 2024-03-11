package com.property.home.data

import com.property.core.network.GenericException
import com.property.core.network.handleCall
import com.property.core.network.handleResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class ResponseHandlerTest {

    @Test
    fun `handleResponse returns body on successful response`() = runTest {
        val successResponse = Response.success("Test Body")
        assertEquals("Test Body", successResponse.handleResponse())
    }

    @Test(expected = GenericException::class)
    fun `handleResponse throws GenericException on null body`() = runTest {
        val nullBodyResponse: Response<String> = Response.success(null)
        nullBodyResponse.handleResponse()
    }

    @Test(expected = GenericException::class)
    fun `handleResponse throws GenericException on unsuccessful response`() = runTest {
        val errorResponse = Response.error<String>(400, okhttp3.ResponseBody.create(null, ""))
        errorResponse.handleResponse()
    }

    @Test(expected = GenericException::class)
    fun `handleCall throws GenericException on exception`() = runTest {
        handleCall<String> {
            throw RuntimeException("Network error")
        }
    }
}