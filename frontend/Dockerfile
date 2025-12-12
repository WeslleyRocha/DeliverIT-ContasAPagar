#Build
FROM node:22-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .

# Gera o build de produção
RUN npm run build

#Servidor NGINX
FROM nginx:alpine

COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html

# Configuração para o Router do Angular funcionar no Nginx
RUN echo 'server { listen 80; location / { root /usr/share/nginx/html; index index.html index.htm; try_files $uri $uri/ /index.html; } }' > /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
