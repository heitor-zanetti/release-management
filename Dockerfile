FROM gradle:8.5-jdk17 AS build

WORKDIR /app
COPY . .

RUN gradle wasmJsBrowserDistribution --no-daemon

# Stage 2 - use lightweight nginx image to serve static content
FROM nginx:alpine
COPY --from=build /app/build/dist/wasmJs/browser /usr/share/nginx/html