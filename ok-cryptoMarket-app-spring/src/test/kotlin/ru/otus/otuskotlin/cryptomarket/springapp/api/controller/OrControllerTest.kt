package ru.otus.otuskotlin.cryptomarket.springapp.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import cryptomarket.stubs.OrderStub
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.otus.otuskotlin.cryptomarket.common.CrmktContext
import ru.otus.otuskotlin.cryptomarket.common.models.CrmktState
import ru.otus.otuskotlin.cryptomarket.mappers.toTransportCreate
import ru.otus.otuskotlin.cryptomarket.openapi.models.OrCreateRequest


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
internal class OrControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private lateinit var mvc: MockMvc

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    fun CreateOr(){
        val request = mapper.writeValueAsString(OrCreateRequest())
        val response = mapper.writeValueAsString(
            CrmktContext().apply {
                orResponse = OrderStub.getModel()
                state = CrmktState.RUNNING
            }.toTransportCreate()
        )

        mvc.perform (post("/or/create").contentType(MediaType.APPLICATION_JSON)
            .content(request)).andExpect(status().isOk).andExpect(content().json(response))
    }
}