# Simple Cafe Management web application

## Installation

Clone git repository (or download sources).
```bash
git clone https://github.com/gpetrosyan-ggm/CafeManagement.git
```

Before running application, need to create 'cafe_management' database.
```bash
CREATE DATABASE  cafe_management;
```

After running, will be imported default user as a **MANAGER**, 
you can use it to create your own one.

>User Name: admin

>Password:  admin

## Implementation details

- **Spring Framework** as IOC container
- **Thymeleaf** for front-end part. 
- **Hibernate** for the database.
- **PostgreSQL** as database provider.
- **Lombok** to generate getters and setters.
- **Model Mapper** for mapping DTO object to Entity and vice version.
- **Flyway** for database version control.

## Functional specification of project

Application should allow creation and management of orders. The application has 2 distinct
interfaces and menu layouts for cafe managers and waiters.
The domain entities of the application are:

Application has the following 2 roles:

**Manager** - Is allowed to:
- Create Users
- Create Tables
- Create Products
- Assign Tables to Waiters, each Table can be assigned only to 1 waiter

**Waiter** - Is allowed to:
- See Tables assigned to him
- Create order for table (if that table does not have Order which is still Open)
- Create ProductInOrder for Order
- Edit ProductInOrder fields
- Edit Order fields
