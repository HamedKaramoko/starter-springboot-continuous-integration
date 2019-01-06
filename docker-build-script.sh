docker build -t continuous-integration .
docker tag continuous-integration hamedkaramoko/continuous-integration:${TAG}
docker push hamedkaramoko/continuous-integration:${TAG}