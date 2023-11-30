echo Containerler hazirlaniyor

docker network create grid

docker container stop selenium-hub
docker container stop node1
docker container stop node2
docker container stop node3
docker container stop node4
docker container stop node5
docker container stop node6
docker container stop node7
docker container stop node8


docker container prune

docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub:4.4.0-20220812

docker run -d --net grid --name node1 -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome:4.4.0-20220812
docker run -d --net grid --name node2 -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome:4.4.0-20220812
docker run -d --net grid --name node3 -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome:4.4.0-20220812
docker run -d --net grid --name node4 -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome:4.4.0-20220812
docker run -d --net grid --name node5 -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome:4.4.0-20220812
docker run -d --net grid --name node6 -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome:4.4.0-20220812
docker run -d --net grid --name node7 -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome:4.4.0-20220812
docker run -d --net grid --name node8 -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome:4.4.0-20220812

echo Prapering media files...
docker cp .\ImagesDocuments\. node1:/media
docker cp .\ImagesDocuments\. node2:/media
docker cp .\ImagesDocuments\. node3:/media
docker cp .\ImagesDocuments\. node4:/media
docker cp .\ImagesDocuments\. node5:/media
docker cp .\ImagesDocuments\. node6:/media
docker cp .\ImagesDocuments\. node7:/media
docker cp .\ImagesDocuments\. node8:/media

docker ps
pause