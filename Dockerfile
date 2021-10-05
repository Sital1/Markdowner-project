FROM nginx:alpine

# Removes the current directory to serve
RUN rm -f /usr/share/nginx/html/*

#Copies the actual directory to be served
COPY dist/frontend /usr/share/nginx/html

#Overwriting Nginx config file
COPY nginx.conf /etc/nginx/nginx.conf

# Exposing port 80 that nginx use
EXPOSE 80

# Hold the docker image running
CMD ["nginx","-g","dameon off;"]