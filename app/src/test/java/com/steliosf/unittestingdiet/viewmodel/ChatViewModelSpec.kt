package com.steliosf.unittestingdiet.viewmodel

import com.steliosf.unittestingdiet.domain.model.Message
import com.steliosf.unittestingdiet.fixtures.datasource.FakeChatDataSource
import com.steliosf.unittestingdiet.fixtures.factory.UserTestFactory
import com.steliosf.unittestingdiet.presentation.ui.model.MessageUiModel
import com.steliosf.unittestingdiet.presentation.viewmodel.ChatViewModel
import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extension
import org.koin.core.parameter.parametersOf
import org.koin.test.get
import org.koin.test.inject
import java.util.Date

@ExtendWith(Extension::class)
class ChatViewModelSpec : BaseBehaviorSpec() {

    private val viewModel: ChatViewModel by inject {
        parametersOf(UserTestFactory().produce())
    }

    init {
        Given("I have exchanged messages with a user") {
            fakeDataSourceResponse()

            Then("They are displayed") {
                viewModel.state.value shouldBeEqual ChatViewModel.State(
                    messages = listOf(
                        MessageUiModel(
                            text = "1st message", type = Message.Type.Received
                        )
                    )
                )
            }

            When("I send a text message") {
                var textMessage = ""
                beforeEach {
                    viewModel.onTextMessageSent(textMessage)
                }

                And("It is valid") {
                    textMessage = "2nd message"

                    Then("It is displayed") {
                        viewModel.state.value shouldBeEqual ChatViewModel.State(
                            messages = listOf(
                                MessageUiModel(
                                    text = "1st message", type = Message.Type.Received
                                ),
                                MessageUiModel(
                                    text = "2nd message", type = Message.Type.Pending
                                )
                            )
                        )
                    }
                }

                And("It is not valid") {
                    textMessage = (1..180).joinToString("") { "a" }

                    Then("It is not displayed") {
                        viewModel.state.value shouldBeEqual ChatViewModel.State(
                            messages = listOf(
                                MessageUiModel(
                                    "1st message", Message.Type.Received
                                )
                            )
                        )
                    }
                }
            }
        }
    }

    private fun fakeDataSourceResponse() {
        get<FakeChatDataSource>().setResponse(
            listOf(Message("id", "1st message", Date(), Message.Type.Received))
        )
    }
}
