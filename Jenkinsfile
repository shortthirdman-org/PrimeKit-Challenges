pipeline {
    agent any

    environment {
        // Define the required versions for Gradle and Maven
        GRADLE_VERSION = '8.10.2'  // Update this to the desired version
        MAVEN_VERSION = '3.9.6'  // Update this to the desired version
        GIT_TAG = "v${env.BUILD_NUMBER}"  // Use Jenkins Build number as version for the tag
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the repository
                checkout scm
            }
        }

        stage('Set Up Gradle') {
            steps {
                script {
                    // Ensure the correct Gradle version is installed
                    sh "sdk install gradle ${GRADLE_VERSION}"
                }
            }
        }

        stage('Set Up Maven') {
            steps {
                script {
                    // Ensure the correct Maven version is installed
                    sh "sdk install maven ${MAVEN_VERSION}"
                }
            }
        }

        stage('Build with Gradle') {
            steps {
                script {
                    // Run the Gradle build (assuming you have a build.gradle file in your project)
                    sh './gradlew clean build'
                }
            }
        }

        stage('Build with Maven') {
            steps {
                script {
                    // Run the Maven build (assuming you have a pom.xml file in your project)
                    sh 'mvn clean install'
                }
            }
        }

        stage('Create Git Tag') {
            steps {
                script {
                    // Create and push the Git tag
                    sh "git tag ${GIT_TAG}"
                    sh "git push origin ${GIT_TAG}"
                }
            }
        }

        stage('Create Release Assets') {
            steps {
                script {
                    // Assuming you want to upload the artifacts from build as release assets
                    def gradleArtifact = "build/libs/*.jar"  // Replace with your actual build artifact location
                    def mavenArtifact = "target/*.jar"  // Replace with your actual Maven build artifact location

                    // Example: Upload to a custom release system (GitHub, GitLab, etc.)
                    // This can vary based on the integration or plugin used. Below is a general idea.

                    // GitHub CLI example
                    sh "gh release create ${GIT_TAG} ${gradleArtifact} ${mavenArtifact} --title 'Release ${GIT_TAG}'"
                }
            }
        }
    }

    post {
        success {
            echo "Build and release process completed successfully!"
        }
        failure {
            echo "The build or release process failed."
        }
    }
}
