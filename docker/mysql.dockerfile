FROM mysql:5.7

ADD ./scriptsql/mutantesdb.sql /docker-entrypoint-initdb.d
