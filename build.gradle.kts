import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.20"
    id("com.diffplug.spotless") version "6.25.0"
    id("info.solidsoft.pitest") version "1.15.0"
    application
}

group = "com.isamadrid"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.mockk:mockk:1.13.8")
}

pitest {
    junit5PluginVersion = "1.2.1"
    testPlugin = "junit5"
    targetClasses = listOf("com.isamadrid90.*")
    outputFormats = listOf("HTML")
    threads = 2
    setWithHistory(false)
}

application {
    mainClass.set("com.isamadrid90.fizzbuzz.MainKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = java.sourceCompatibility.toString()
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

spotless {
    kotlin {
        ktlint()
            .editorConfigOverride(
                mapOf(
                    "insert_final_newline" to "true",
                ),
            )
    }
    kotlinGradle {
        ktlint()
    }
}

tasks.check {
    dependsOn(tasks.spotlessCheck)
}
