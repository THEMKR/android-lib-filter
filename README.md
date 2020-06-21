# MKR-ANDROID-LIB-NETWORK

#	Project Level Gradle
		repositories {
			maven { url "https://api.bitbucket.org/2.0/repositories/THEMKR/android-libs/src/releases" }
		}

#	APP Level Gradle

    implementation 'com.lory.library:filter:1.0.0'

	<!-- DEPENDENCY INCLUDE IN LIB -->
	implementation"org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
