# Used Items Marketplace API

## Run instructions
1. Open Ubuntu (Windows)
2. run redis-server
3. run Java app

## Description
This project aims to create an API for a marketplace where users can sell their used items, similar to Facebook Marketplace.

## First Steps
1. Create basic schema
2. implement basic GET, POST, PUT, DELETE endpoints

   
## Tables
1. **Users**
   - `userId` (int, PK)
   - `name` (string)
   - `numItemsSold` (int)
   - `numAvailableItems` (int)

2. **Items**
   - `itemId` (int, PK)
   - `userId` (int, FK)
   - `name` (string)
   - `description` (string)
   - `condition` (string)
   - `price` (float)
   - `categoryId` (int, FK)

3. **Transactions**
   - `transactionId` (int, PK)
   - `buyerId` (int, FK)
   - `sellerId` (int, FK)
   - `itemId` (int, FK)

4. **Categories**
   - `categoryId` (int, PK)
   - `category` (string)

## API Endpoints
1. **Users**
   - `GET /users`
   - `POST /users`
   - `DELETE /users/{userId}`
   - `PUT /users/{userId}`

2. **Items**
   - `GET /items`
   - `POST /items`
   - `DELETE /items/{itemId}`
   - `PUT /items/{itemId}`

3. **Transactions**
   - `GET /transactions`
   - `POST /transactions`
   - `DELETE /transactions/{transactionId}`
   - `PUT /transactions/{transactionId}`

4. **Categories**
   - `GET /categories`
   - `POST /categories`
   - `DELETE /categories/{categoryId}`
   - `PUT /categories/{categoryId}`

## Next Steps

