CREATE TABLE `payment_mode` (
  `payment_mode_id` int,
  `name` varchar(100),
  PRIMARY KEY (`payment_mode_id`)
);

CREATE TABLE `paymemt_details` (
  `payment_details_id` int,
  `invoice_id` int,
  `payment_mode_id` int,
  `status` int,
  `date recieved ` datetime,
  `date_updated` datetime,
  `date created` datetime,
  PRIMARY KEY (`payment_details_id`)
);

CREATE TABLE `Order_line` (
  `order_item_id` int,
  `invoice_id` int,
  `product_id` int,
  `quantity` int,
  `total` float,
  PRIMARY KEY (`order_item_id`)
);

CREATE TABLE `Branches` (
  `branch_id` int,
  `location_Name` varchar(20),
  `phone_number` bitInt,
  `Image` image,
  PRIMARY KEY (`branch_id`)
);

CREATE TABLE `return_type` (
  `return_type_id` int,
  `name` varChar(15),
  PRIMARY KEY (`return_type_id`)
);

CREATE TABLE `invoice` (
  `invoice_id` int,
  `order_line` int,
  `customer id ` int,
  `branch_id` int,
  `status` varchar(50),
  `order_refrence` varchar(255),
  `discount ` int,
  `order_total` int,
  `date_added` datetime,
  PRIMARY KEY (`invoice_id`)
);

CREATE TABLE `product_supplier` (
  `product_id` int,
  `supplier_id` int
);

CREATE TABLE `Employees` (
  `employee_id` Int,
  `branch_id` int,
  `role_id` int,
  `first_name` varchar(20),
  `last_name` varchar(20),
  `username` varchar(20),
  `date_of_birth` date,
  `telephone` bigint,
  `company_email` varchar(100),
  `personal_email` varchar(100),
  `data_hired` date,
  PRIMARY KEY (`employee_id`)
);

CREATE TABLE `Purchase` (
  `purchase_id` int,
  `branch_id` int,
  `supplier_id` int,
  `purchase_refrence ` varchar(255),
  `status` varchar(50),
  `mrp` int,
  `quantity_purchased` int,
  `order_total ` int,
  `date_ordered` datetime,
  PRIMARY KEY (`purchase_id`)
);

CREATE TABLE `Returns` (
  `invoice_id` int,
  `order_item_id` int,
  `return_type_id` int,
  `date created ` datetime,
  `requested_date` datetime,
  `status` varchar(20),
  `completed_date` datetime,
  PRIMARY KEY (`invoice_id`)
);

CREATE TABLE `role` (
  `role_id` int,
  `role_name` varchar(20),
  PRIMARY KEY (`role_id`)
);

CREATE TABLE `Products` (
  `product_id` int,
  `product_name` varchar(50),
  `summary` Type,
  `unit` varchar,
  `price` float,
  `created_date` datetime,
  `image` image,
  PRIMARY KEY (`product_id`)
);

CREATE TABLE `purchase_orderline` (
  `purchase_orderline_id` int,
  `purchase_id` int,
  `product_id` int,
  `quantity` int,
  `amount` float,
  PRIMARY KEY (`purchase_orderline_id`)
);

CREATE TABLE `Customers` (
  `customer_id` int,
  `fIrst_name` varChar(20),
  `last_name` varChar(20,
  `comapany_name` varchar(50),
  `phone_number ` bigInt,
  `work_number` bigint,
  `line_1` varchar(100),
  `line_2` varchar(100),
  `city` varchar(20),
  `postcode` varchar(7),
  `state` varchar(20),
  `country` varchar(20),
  `data_created` date,
  PRIMARY KEY (`customer_id`)
);

CREATE TABLE `Supplier` (
  `supplier_id` int,
  `address_id` int,
  `supplier_company_name ` varchar(50),
  `first_name` varchar(20),
  `last_name` varchar(20),
  `phone_number` bigint,
  PRIMARY KEY (`supplier_id`)
);

CREATE TABLE `Stocks` (
  `stock_id` int,
  `product_id` int,
  `branch_id` int,
  `quantity` int,
  PRIMARY KEY (`stock_id`)
);


