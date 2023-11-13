-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 11, 2023 at 02:05 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `product_warehouse_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cartID` int(11) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `productID` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `detailtransaction`
--

CREATE TABLE `detailtransaction` (
  `detailTransactionID` int(11) NOT NULL,
  `transactionID` int(11) DEFAULT NULL,
  `productID` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productID` int(11) NOT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `productQuantity` int(11) DEFAULT NULL,
  `productPrice` double DEFAULT NULL,
  `productRating` decimal(3,2) DEFAULT NULL,
  `onSale` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productID`, `productName`, `productQuantity`, `productPrice`, `productRating`, `onSale`) VALUES
(1, 'KALLAX Shelf Unit', 25, 79.99, 4.60, 1),
(2, 'STOCKHOLM Sofa', 10, 699.99, 4.90, 1),
(3, 'FRIHETEN Sleeper Sofa', 15, 549.99, 4.30, 1),
(4, 'BESTÅ TV Unit', 20, 129.99, 4.50, 1),
(5, 'RÖDTOPPA Comforter', 35, 39.99, 4.80, 1),
(6, 'RIBBA Frame', 50, 9.99, 4.00, 1),
(7, 'TOFTBO Bath Mat', 40, 14.99, 4.70, 1),
(8, 'LURVIG Cat Bed', 8, 19.99, 4.20, 1),
(9, 'GRÖNLID Sectional', 12, 899.99, 4.60, 1),
(10, 'SINNERLIG Pendant Lamp', 18, 59.99, 4.40, 1);

-- --------------------------------------------------------

--
-- Table structure for table `productlist`
--

CREATE TABLE `productlist` (
  `productID` int(11) NOT NULL,
  `warehouseID` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `productlist`
--

INSERT INTO `productlist` (`productID`, `warehouseID`, `quantity`, `isActive`) VALUES
(1, 1, 30, 1),
(2, 2, 20, 1),
(3, 3, 25, 1),
(4, 4, 15, 1),
(5, 5, 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transactionID` int(11) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `transaction_date` datetime DEFAULT NULL,
  `transactionType` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `userPassword` varchar(255) DEFAULT NULL,
  `roleType` varchar(255) DEFAULT NULL,
  `membership_type` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `userName`, `userPassword`, `roleType`, `membership_type`) VALUES
(1, 'Gary', 'gary123', 'Customer', NULL),
(2, 'John', 'john123', 'StaffOnline', NULL),
(3, 'Bixy', 'bixy123', 'StaffOnline', NULL),
(4, 'Niel', 'niel123', 'StaffOnsite', NULL),
(5, 'Joe', 'joe123', 'StaffOnsite', NULL),
(6, 'Chen', 'chen123', 'Owner', NULL),
(7, 'Sharon', 'sharon123', 'Customer', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `wallet`
--

CREATE TABLE `wallet` (
  `userID` int(11) DEFAULT NULL,
  `walletID` int(11) NOT NULL,
  `walletAmount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `wallet`
--

INSERT INTO `wallet` (`userID`, `walletID`, `walletAmount`) VALUES
(1, 1, 200),
(7, 2, 150);

-- --------------------------------------------------------

--
-- Table structure for table `warehouse`
--

CREATE TABLE `warehouse` (
  `warehouseID` int(11) NOT NULL,
  `warehouseName` varchar(100) DEFAULT NULL,
  `warehouseLocation` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `warehouse`
--

INSERT INTO `warehouse` (`warehouseID`, `warehouseName`, `warehouseLocation`) VALUES
(1, 'Jakarta Warehouse', 'Jakarta'),
(2, 'Surabaya Warehouse', 'Surabaya'),
(3, 'Bandung Warehouse', 'Bandung'),
(4, 'Medan Warehouse', 'Medan'),
(5, 'Makassar Warehouse', 'Makassar');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cartID`),
  ADD KEY `cart_product_fk` (`productID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `detailtransaction`
--
ALTER TABLE `detailtransaction`
  ADD PRIMARY KEY (`detailTransactionID`),
  ADD KEY `productID` (`productID`),
  ADD KEY `transactionID` (`transactionID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productID`);

--
-- Indexes for table `productlist`
--
ALTER TABLE `productlist`
  ADD PRIMARY KEY (`productID`,`warehouseID`),
  ADD KEY `product_list_warehouse_fk` (`warehouseID`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transactionID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `user_pk` (`userName`);

--
-- Indexes for table `wallet`
--
ALTER TABLE `wallet`
  ADD PRIMARY KEY (`walletID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `warehouse`
--
ALTER TABLE `warehouse`
  ADD PRIMARY KEY (`warehouseID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cartID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `detailtransaction`
--
ALTER TABLE `detailtransaction`
  MODIFY `detailTransactionID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `productID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transactionID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `wallet`
--
ALTER TABLE `wallet`
  MODIFY `walletID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_product_fk` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cart_user_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`);

--
-- Constraints for table `detailtransaction`
--
ALTER TABLE `detailtransaction`
  ADD CONSTRAINT `detailtransaction_product_fk` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`),
  ADD CONSTRAINT `detailtransaction_transaction_fk` FOREIGN KEY (`transactionID`) REFERENCES `transaction` (`transactionID`);

--
-- Constraints for table `productlist`
--
ALTER TABLE `productlist`
  ADD CONSTRAINT `product_list_product_fk` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `product_list_warehouse_fk` FOREIGN KEY (`warehouseID`) REFERENCES `warehouse` (`warehouseID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_user_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`);

--
-- Constraints for table `wallet`
--
ALTER TABLE `wallet`
  ADD CONSTRAINT `wallet_user_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
