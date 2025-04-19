FROM gradle:8.9-jdk17 AS build

WORKDIR /app
COPY . .

RUN gradle wasmJsBrowserDistribution --no-daemon

# Stage 2 - use lightweight nginx image to serve static content
FROM nginx:alpine
COPY --from=build /composeApp/build/dist/wasmJs/browser /usr/share/nginx/html