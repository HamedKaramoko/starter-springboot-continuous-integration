echo 'Mamanjetaime92@' | docker login --username hamedkaramoko --password-stdin
docker run --rm -d -p 8080:8080 hamedkaramoko/continuous-integration:1.0