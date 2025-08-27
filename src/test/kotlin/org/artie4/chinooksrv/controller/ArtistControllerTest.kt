package org.artie4.chinooksrv.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.artie4.chinooksrv.dto.ArtistDto
import org.artie4.chinooksrv.service.ArtistService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(controllers = [ArtistController::class])
class ArtistControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var artistService: ArtistService

    @WithMockUser(username = "test-user", roles = ["USER"])
    @Test
    fun `get artist by id returns expected artist`() {
        // given
        val artist = ArtistDto(id = 1L, name = "Radiohead")
        every { artistService.getArtistById(1L) } returns artist

        // when + then
        mockMvc.get("/api/v1/artist/1") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content {
                json("""{"id":1,"name":"Radiohead"}""")
            }
        }
    }

    @WithMockUser(username = "test-user", roles = ["USER"])
    @Test
    fun `get artist by name returns list`() {
        // given
        val artists =
            listOf(
                ArtistDto(id = 1L, name = "Radiohead"),
                ArtistDto(id = 2L, name = "Rammstein"),
            )
        every { artistService.getArtistByName("Ra") } returns artists

        // when + then
        mockMvc.get("/api/v1/artists/by_name?name=Ra") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.length()") { value(2) }
            jsonPath("$[0].name") { value("Radiohead") }
        }
    }
}
