# K-IMMO Real Estate Management System

## Overview

K-IMMO is a real estate company specializing in apartment sales. This project aims to develop a comprehensive information system for managing their sales operations. The system includes functionality for managing buildings, apartments, clients, sales processes, and legal aspects.

## Features

- **Building Management**: Maintain information about buildings, including name and address. Each building contains multiple apartments.

- **Apartment Details**: Record details for each apartment, such as apartment number, size, number of bedrooms, and expected price.

- **Public Access**: The application allows the general public to view the company's apartment listings.

- **Client Information**: Collect client information when they express interest in purchasing an apartment, including ID, name, address, phone number, and profession.

- **Apartment Visits**: Schedule and document apartment visits for clients. Record visit dates, client remarks, and decisions.

- **Sales Process**: Clients can only purchase an apartment after visiting it. Generate a sales promise that includes all necessary details, including the final selling price, buyer information, and advance payment.

- **Legal Aspects**: Ensure legal compliance by involving an attorney in the sales process. Record attorney details and authorization numbers.

- **Cancellation**: Clients can cancel a sale until the final sales contract is signed. Record cancellations with reasons and handle advance payment refunds.

- **Sales Contract**: Generate and sign sales contracts with apartment details, price, payment method, and signing date.

- **Key Handover**: Apartments are handed over to buyers after full payment. Document the handover with a signed protocol.

## Technical Architecture

The system's technical architecture consists of the following components:

1. **Main Server**: A server with the following specifications: Core i5 processor, 16 GB RAM, 17" monitor, 2 TB RAID storage. This server hosts a web server (IIS) for application deployment.

2. **Oracle Database Server**: A separate server hosting the Oracle database system, communicating with the main server via TCP/IP.

3. **Backup Server**: A backup server responsible for database replication.

4. **Client Workstation**: A client workstation with a web browser for connecting to the server via the internet using HTTP requests.

## Installation and Setup

- Clone this repository to your local machine.
- Configure the servers and database as per the system requirements.
- Deploy the application on the main server using the provided deployment instructions.

## Usage

- Access the application through a web browser on the client workstation.
- Follow the documented processes for managing buildings, apartments, clients, and sales.
