# ========== Builder Stage ==========
FROM node:20-alpine as builder
WORKDIR /app

# Copy everything first
COPY . .

# 👇 Inject NEXT_PUBLIC_ variables for build-time
ARG NEXT_PUBLIC_API_BASE_URL
ENV NEXT_PUBLIC_API_BASE_URL=${NEXT_PUBLIC_API_BASE_URL}

# Install deps and build
RUN npm install
RUN npm run build

# ========== Runner Stage ==========
FROM node:20-alpine as runner
WORKDIR /app

ENV NODE_ENV=production

# Copy necessary build files
COPY --from=builder /app/public ./public
COPY --from=builder /app/.next ./.next
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/package.json ./package.json

EXPOSE 3000

# Start production server
CMD ["npm", "start"]
