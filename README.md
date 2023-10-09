# The Unit Testing Diet Sample App

How can you effectively unit test an MVVM app at scale?

This is a sample Android chat application that demonstrates how to follow Behavior-Driven Development (BDD), by writing unit tests that test the behavior that a user performs and the outcome that they perceive, without testing implementation details.

The tests are written in the Given-When-Then style and they cover the ViewModel, UseCase, and Repository layers, without using mocks.

For a comprehensive guide on how to follow this methodology, please refer to the ["Unit Testing Diet" blog post](https://proandroiddev.com/the-unit-testing-diet-1607aac5f434).

## Setup

1. Clone the repository
2. Checkout the `main` branch for Kotlin Flow or `rxjava` for RxJava
3. Examine the structure of the tests in the `ChatViewModelSpec.kt` file.

## Libraries Used

- Dependency Injection: [Koin](https://github.com/InsertKoinIO/koin)
- Testing Framework: [Kotest](https://github.com/kotest/kotest)
- Reactive Framework: Both [Kotlin Flow](https://github.com/Kotlin/kotlinx.coroutines) and [RxJava](https://github.com/ReactiveX/RxJava) are supported

## Contact

For further queries or feedback, feel free to reach out to me on Twitter: [@SteliosFran](https://twitter.com/SteliosFran).
