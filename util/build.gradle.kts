plugins {
    alias(libs.plugins.android.library)
    id("maven-publish")
}

android {
    namespace = "com.elegidocodes.android.util"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        aarMetadata {
            minCompileSdk = 21
        }

    }

    /* For different flavors:
    productFlavors {
        register("foo") {
            aarMetadata {
                minCompileSdk = 21
            }
        }
    }*/

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    publishing {
        singleVariant("release") {
            withSourcesJar() // Optional: Include sources jar
        }
    }

    /*testFixtures {
        enable = true
    }*/

}

/*
afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.elegidocodes"
                artifactId = "util"
                version = "1.0.0-alpha"
            }
        }
    }
}
*/

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.elegidocodes"
            artifactId = "util"
            version = "1.0.0-beta"

            // Include AAR file in the publication
            afterEvaluate {
                from(components["release"])
            }
        }
    }

    /*
    repositories {
        maven {
            name = "maven"
            url = uri("${layout.buildDirectory}/repos") // This allows JitPack to locate the artifacts
        }
    }
    */
}


dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.swiperefreshlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}