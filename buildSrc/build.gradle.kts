plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    jcenter()
    mavenCentral()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

object PluginsVersions {
    const val GRADLE_ANDROID = "4.1.3"
    const val GRADLE_VERSIONS = "0.28.0"
    const val KOTLIN = "1.4.32"
    const val NAVIGATION = "2.3.4"
    const val HILT = "2.33-beta"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginsVersions.GRADLE_VERSIONS}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("org.jetbrains.kotlin:kotlin-allopen:${PluginsVersions.KOTLIN}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginsVersions.HILT}")
}
