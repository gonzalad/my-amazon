FROM httpd:2.4.33
COPY ./maven/html/ /usr/local/apache2/htdocs/

# Needed for redirecting all URLs to index.html
# This solves 404 Not found error on page refresh
# See https://github.com/mringer/DockerNgHttpd
COPY ./.htaccess /usr/local/apache2/htdocs/
COPY ./httpd.conf /usr/local/apache2/conf/httpd.conf

CMD ["httpd-foreground"]
