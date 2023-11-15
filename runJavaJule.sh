docker-compose down
docker volume rm $(docker volume ls -q --filter dangling=true)
docker rmi $( docker image ls --format '{{.Repository}}:{{.Tag}}' | grep '^java_jule')
docker-compose -f docker-compose.yml up