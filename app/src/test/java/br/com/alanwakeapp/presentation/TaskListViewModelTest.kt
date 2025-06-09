package br.com.alanwakeapp.presentation

import app.cash.turbine.test
import br.com.alanwakeapp.MainDispatcherRule
import br.com.alanwakeapp.domain.model.Character
import br.com.alanwakeapp.domain.model.Game
import br.com.alanwakeapp.domain.model.GameInfo
import br.com.alanwakeapp.domain.model.GeneralInfo
import br.com.alanwakeapp.domain.model.ImageGalleryItem
import br.com.alanwakeapp.domain.model.Scenery
import br.com.alanwakeapp.domain.model.Universe
import br.com.alanwakeapp.domain.usecase.GetGameInfoUseCase
import br.com.alanwakeapp.presentation.viewmodel.AlanWakeViewModel
import br.com.alanwakeapp.presentation.viewmodel.GameState
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TaskListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getGameInfoUseCase: GetGameInfoUseCase

    private lateinit var viewModel: AlanWakeViewModel


    val mockGameInfo = GameInfo(
        Game(
            titulo = "Alan Wake",
            informacoes_gerais = GeneralInfo(
                desenvolvedora = "Remedy Entertainment",
                imagem_capa = "https://example.com/alanwake_cover.jpg"
            ),
            enredo_e_universo = Universe(
                sinopse = "Alan Wake é um thriller psicológico que segue o escritor Alan Wake em sua busca para descobrir a verdade por trás do desaparecimento de sua esposa, enquanto enfrenta forças sobrenaturais.",
                cenario = Scenery(
                    nome = "Bright Falls",
                    descricao = "Uma pequena cidade cercada por florestas densas e lagos misteriosos."
                )
            ),
            personagens = listOf(
                Character(
                    nome = "Alan Wake",
                    papel = "Protagonista",
                    descricao = "Um escritor de sucesso que luta contra a escuridão que ameaça sua vida e sua sanidade.",
                    imagem = "https://example.com/alanwake_character.jpg"
                ),
                Character(
                    nome = "Alice Wake",
                    papel = "Esposa de Alan",
                    descricao = "Desapareceu misteriosamente, levando Alan a uma busca desesperada por respostas.",
                    imagem = "https://example.com/alicewake_character.jpg"
                )
            ),
            galeria_de_imagens = listOf(
                ImageGalleryItem(
                    titulo = "Capa do Jogo",
                    url = "https://example.com/alanwake_cover.jpg"
                ),
                ImageGalleryItem(
                    titulo = "Alan Wake em Ação",
                    url = "https://example.com/alanwake_action.jpg"
                )
            )
        )
    )

    @Before
    fun setUp() {
        getGameInfoUseCase = mockk()

        every { getGameInfoUseCase() } returns flowOf(Result.success(mockGameInfo))

        viewModel = AlanWakeViewModel(
            getGameInfoUseCase
        )
    }

    @Test
    fun `init should load data and emit Loading then Success when use case is successful`() = runTest {

            viewModel.gameState.test {
                assertEquals(GameState.Success(mockGameInfo), awaitItem())

                cancelAndIgnoreRemainingEvents()
            }

            verify(exactly = 1) { getGameInfoUseCase() }
        }
}