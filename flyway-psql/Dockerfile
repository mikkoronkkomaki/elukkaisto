FROM flyway/flyway:latest-alpine
USER root
RUN apk --no-cache --update add postgresql-client && rm -rf /tmp/*.apk /tmp/gcc /tmp/gcc-libs.tar* /tmp/libz /tmp/libz.tar.xz /var/cache/apk/*
USER flyway