# Étape 1 : Build Angular
FROM node:18 AS build

WORKDIR /app

COPY package*.json ./

RUN npm install --legacy-peer-deps

COPY . .

RUN npm run build

# Étape 2 : Nginx
FROM nginx:alpine

RUN rm /etc/nginx/conf.d/default.conf

COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build /app/dist/skote /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]