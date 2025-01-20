# Climbing Wall Scoring App 🧗‍♂️

This repository contains the source code for the **Climbing Wall Scoring App**, an Android application designed to track and manage scores for a climbing wall challenge. The app features a user-friendly interface and implements key Android development practices.

---

## Features ✨

- **Dynamic Scoring System**: Automatically updates scores based on user interactions.
- **Reset and Retry**: Allows users to reset scores or retry after a fall.
- **Visual Feedback**: Color-coded score updates for an engaging user experience.
- **Lifecycle Management**: Preserves data across configuration changes using `savedInstanceState`.
- **Material Design**: Implements a modern UI adhering to Android's Material Design guidelines.

---

## Project Structure 📂

```
📦 Climbing Wall Scoring
├─ .gitignore
├─ README.md
├─ app
│  ├─ build.gradle.kts
│  ├─ proguard-rules.pro
│  └─ src
│     ├─ androidTest
│     │  └─ java
│     │     └─ com.example.climbingwallscoreboard
│     │          └─ ExampleInstrumentedTest.kt
│     ├─ main
│     │  ├─ AndroidManifest.xml
│     │  ├─ java
│     │  │  └─ com.example.climbingwallscoreboard
│     │  │      ├─ MainActivity.kt
│     │  │      └─ ui.theme
│     │  │         ├─ Color.kt
│     │  │         ├─ Theme.kt
│     │  │         └─ Type.kt
│     │  ├─ res
│     │  │  ├─ drawable
│     │  │  ├─ layout
│     │  │  ├─ mipmap
│     │  │  ├─ values
│     │  │  └─ xml
│     └─ test
│         └─ java
│             └─ com.example.climbingwallscoreboard
│                 ├─ ExampleUnitTest.kt
│                 └─ UnitTests.kt
├─ build.gradle.kts
├─ gradle.properties
├─ gradle
│  ├─ libs.versions.toml
│  └─ wrapper
├─ gradlew
├─ gradlew.bat
└─ settings.gradle.kts
```

---

## Prerequisites 🛠️

- Android Studio (latest version recommended)
- JDK 11 or higher
- Gradle 7.x

---

## Getting Started 🚀

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/your-username/climbing-wall-scoring.git
   cd climbing-wall-scoring
   ```

2. **Open in Android Studio**:

   - Launch Android Studio.
   - Open the project from the cloned directory.

3. **Build the Project**:

   - Let Gradle sync.
   - Build the project using `Build > Make Project`.

4. **Run the App**:

   - Connect an Android device or start an emulator.
   - Click the `Run` button to deploy the app.

---

## Testing 🧪

### Unit Tests

- Unit tests are located in the `test/java/com.example.climbingwallscoreboard` directory.
- Run tests using the following Gradle command:
  ```bash
  ./gradlew test
  ```

### Instrumented Tests

- Instrumented tests are located in the `androidTest/java/com.example.climbingwallscoreboard` directory.
- Execute them with:
  ```bash
  ./gradlew connectedAndroidTest
  ```

---

## Contributing 🤝

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature.
3. Commit your changes.
4. Submit a pull request.

---

## License 📜

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Acknowledgments 🙏

- Android Studio and Gradle documentation.
- Material Design guidelines.

Happy coding!

