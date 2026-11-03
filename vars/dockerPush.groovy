
def call(String imageName, String imageTag, String dockerHubUser) {

    withCredentials([usernamePassword(
        credentialsId: "CICDcred",
        passwordVariable: "dockerHubPass",
        usernameVariable: "credentialUser"
    )]) {

        sh """
            docker tag ${imageName}:${imageTag} ${dockerHubUser}/${imageName}:${imageTag}

            echo "\$dockerHubPass" | docker login -u "\$credentialUser" --password-stdin

            docker push ${dockerHubUser}/${imageName}:${imageTag}

     
        """
    }

    echo "Docker image pushed successfully!"
}
