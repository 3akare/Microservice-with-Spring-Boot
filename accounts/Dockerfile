# First get base image
FROM openjdk:17-jdk-slim

# The maintainer of the image (information of who maintains the image)
LABEL name="3akare"
LABEL email="bakaredavid007@gmail.com"

# Copy from target folder, and paste in docker image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# The commands to run when the image is placed in the image
# It is in an array because of spaces
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]

# dockerCLI command to generate image, and run dockerfile
# 1. Building the Image: docker build [root-containing-dockerfile] -t [docker-account-username]/[name-of-the-docker-image]:[version-name]
# 2. List images: docker images
# 3. Create a container: docker run -p [the-port-to-expose]:[the-port-the-app-runs-on] [docker-account-username]/[name-of-the-docker-image]:[version-name]
# Optional (run in detached mode): docker run -d -p [the-port-to-expose]:[the-port-the-app-runs-on] [docker-account-username]/[name-of-the-docker-image]:[version-name]