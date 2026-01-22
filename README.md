# Food Delivery LLD (Low Level Design)

This project is a Food Delivery Order Management System that simulates the process of placing a food order, applying restaurant-level pricing rules, and tracking the order lifecycle.

---

## Features Supported

The system supports the following operations:

- Add food items to a cart
- Apply restaurant-level discounts
- Add delivery charges
- Add packaging charges
- Calculate the final payable amount
- Validate and progress order status through defined stages

---

## Order Status Lifecycle

The order follows a realistic status workflow:

PLACED → ACCEPTED → PREPARING → OUT_FOR_DELIVERY → DELIVERED

Each transition is validated to ensure only valid state changes occur.

---

## Object-Oriented Principles Used in This Project

This project applies core OOP principles to keep the design modular, extensible, and maintainable. Below is an explanation of each principle with examples from the code.

---

## Project Structure

The project is organized into a typical Gradle-based Java folder structure. The main application code is separated into logical packages for models and pricing policies.

```
Food_Delivery_LLD/
└── app/
    ├── build.gradle
    └── src/
        ├── main/
        │   └── java/
        │       └── Food/
        │           ├── Main.java
        │           ├── model/
        │           │   ├── FoodOrder.java
        │           │   ├── OrderItem.java
        │           │   └── OrderStatus.java
        │           └── pricing/
        │               ├── ChargePolicy.java
        │               ├── PricingEngine.java
        │               ├── PercentageDiscountPolicy.java
        │               ├── DeliveryChargePolicy.java
        │               └── PackagingChargePolicy.java
        └── test/
            └── java/
```
           

### 1. Abstraction

Abstraction means exposing only essential details while hiding unnecessary internal logic.

Example in this project:

- The `ChargePolicy` interface exposes only one method `apply(double amount)`
- The caller does not need to know how a discount or delivery charge is actually computed.

In `PricingEngine`, the engine simply calls:
policy.apply(amount);

without knowing how each policy internally modifies the amount.

This hides implementation complexity from the rest of the system.

---

### 2. Encapsulation

Encapsulation ensures that data is protected and accessed through controlled methods rather than exposed directly.

Example in this project:

- `OrderItem` stores `name`, `price`, and `quantity` as private fields.
- Access to these fields is provided through getter methods such as `getName()` and `getQuantity()`.

This prevents external code from modifying internal state directly and helps maintain data integrity.

---

### 3. Inheritance

Inheritance allows classes to derive common functionality from a base class or interface.

Example in this project:

All pricing rules implement the same interface:

public interface ChargePolicy {
double apply(double amount);
}

Concrete implementations include:

- `PercentageDiscountPolicy`
- `DeliveryChargePolicy`
- `PackagingChargePolicy`

These classes inherit the contract from `ChargePolicy` and provide their own calculation logic.

This approach promotes code reuse and consistency across pricing strategies.

---

### 4. Polymorphism

Polymorphism allows objects of different types to be treated uniformly through a common interface.

Example in this project:

In `PricingEngine`, the engine loops through all charge policies:



At runtime, `apply()` could:

- subtract a percentage discount,
- add a delivery charge,
- add packaging charges.

This dynamic behavior is possible because all policies share the same interface and can be applied interchangeably.

---

## Summary of OOP Benefits in This Project

- Abstraction hides billing and state transition complexity
- Encapsulation ensures data safety for entities like `OrderItem`
- Inheritance allows new pricing rules to be added easily
- Polymorphism enables flexible rule application without modifying existing code

This leads to a system that is easy to extend, test, and maintain.

## Screenshots:

<img width="727" height="708" alt="image" src="https://github.com/user-attachments/assets/e3dcb205-98fa-4d68-b47d-1ac4049966fa" />
<img width="915" height="887" alt="image" src="https://github.com/user-attachments/assets/1e406a9c-ac16-4bce-85b3-adbb58818296" />
<img width="775" height="886" alt="image" src="https://github.com/user-attachments/assets/825fd759-626f-4bef-8c60-98d7a23d39bc" />
<img width="706" height="865" alt="image" src="https://github.com/user-attachments/assets/88891e97-23a0-4c9d-8c85-26c78cd62e2b" />
<img width="563" height="223" alt="image" src="https://github.com/user-attachments/assets/82c77a55-b4e3-43dc-82bf-8021044d05bf" />
