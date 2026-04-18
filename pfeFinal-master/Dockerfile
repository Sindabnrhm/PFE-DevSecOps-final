FROM node:16-alpine AS build

WORKDIR /app

COPY package*.json ./
RUN npm ci --legacy-peer-deps

COPY . .
RUN npm run build


FROM nginx:alpine

# 🔥 IMPORTANT : supprimer config par défaut
RUN rm /etc/nginx/conf.d/default.conf

# 🔥 copier ton config
COPY nginx.conf /etc/nginx/conf.d/default.conf

# 🔥 copier build Angular
COPY --from=build /app/dist/skote /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]