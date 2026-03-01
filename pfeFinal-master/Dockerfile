FROM node:16-alpine AS build

WORKDIR /app

# config npm pour éviter timeout
RUN npm config set fetch-retries 5 \
 && npm config set fetch-retry-mintimeout 20000 \
 && npm config set fetch-retry-maxtimeout 120000

COPY package*.json ./
RUN npm install


COPY . .

RUN npm run build 


# ===== Étape 2 : Nginx =====
FROM nginx:alpine
COPY --from=build /app/dist/skote /usr/share/nginx/html
EXPOSE 80
