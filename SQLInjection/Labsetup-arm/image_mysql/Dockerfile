FROM mysql:8.0.37
ARG DEBIAN_FRONTEND=noninteractive

ENV MYSQL_ROOT_PASSWORD=dees
ENV MYSQL_USER=seed 
ENV MYSQL_PASSWORD=dees
ENV MYSQL_DATABASE=sqllab_users

COPY sqllab_users.sql /docker-entrypoint-initdb.d
