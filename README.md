## Vending Machine Capstone  
This was a capstone project completed for [Merit America's](https://meritamerica.org/) Fullstack Java Web Developer Bootcamp - A 31-week intensive program focused on Full Stack Web Application Development, including hands-on 
coursework in Java Development, Client-Server Programming (SQL + Spring), and Frontend Development (Classic Web + React).  

Program Capstone Project 1 of 3.  

## Project Description
Virtual vending machine application with command-line interface allowing users to deposit money, choose a product, and return the correct change. Inventory is loaded via a text file and transactions are also logged to a text file.  


## Table of Contents
- [Vending Machine Capstone](#vending-machine-capstone)
- [Project Description](#project-description)
- [Table of Contents](#table-of-contents)
- [Project Requirements](#project-requirements)
- [Running the Application](#running-the-application)
  - [Requirements](#requirements)
  - [Getting Started](#getting-started)
- [Team Members](#team-members)


## Project Requirements
1. Each vending machine item has a name and a price
2. A main menu must display when the software runs, presenting the following options:
    > ```
    > (1) Display Vending Machine Items
    > (2) Purchase
    > (3) Exit
    > ```
3. Vending machine inventory is stocked via an input file when the vending machine starts
4. The vending machine is automatically restocked each time the application runs.
 When the customer selects "(1) Display Vending Machine Items", they're presented
with a list of all items in the vending machine with its quantity remaining:
    - Each vending machine product has a slot identifier and a purchase price.
    - Each slot in the vending machine has enough room for 5 of that product.
    - Every product is initially stocked to the maximum amount.
    - A product that has run out must indicate that it is SOLD OUT.
6. When the customer selects "(2) Purchase", they are guided through the purchasing
process menu:
    >```
    >(1) Feed Money
    >(2) Select Product
    >(3) Finish Transaction
    >
    > Current Money Provided: $2.00
    >```
    7. The purchase process flow is as follows:
    1. Selecting "(1) Feed Money" allows the customer to repeatedly feed money into the
    machine in valid, whole dollar amounts—for example, $1, $2, $5, or $10.
        - The "Current Money Provided" indicates how much money the customer
        has fed into the machine.
    2. Selecting "(2) Select Product" allows the customer to select a product to
    purchase.
        - Show the list of products available and allow the customer to enter
        a code to select an item.
        - If the product code does not exist, the customer is informed and returned
        to the Purchase menu.
        - If a product is sold out, the customer is informed and returned to the
        Purchase menu.
        - If a valid product is selected, it is dispensed to the customer.
        - Dispensing an item prints the item name, cost, and the money
        remaining. Dispensing also returns a message:
          - All chip items print "Crunch Crunch, Yum!"
          - All candy items print "Munch Munch, Yum!"
          - All drink items print "Glug Glug, Yum!"
          - All gum items print "Chew Chew, Yum!"
        - After the product is dispensed, the machine must update its balance
        accordingly and return the customer to the Purchase menu.
    3. Selecting "(3) Finish Transaction" allows the customer to complete the
    transaction and receive any remaining change.
        - The customer's money is returned using nickels, dimes, and quarters
        (using the smallest amount of coins possible).
        - The machine's current balance must be updated to $0 remaining.
    4. After completing their purchase, the user is returned to the "Main" menu to
    continue using the vending machine.
8. All purchases must be audited to prevent theft from the vending machine:
   - Each purchase must generate a line in a file called `Log.txt`.
   - The audit entry must be in the format:
        >```
        > 01/01/2016 12:00:00 PM FEED MONEY: $5.00 $5.00
         >01/01/2016 12:00:15 PM FEED MONEY: $5.00 $10.00
         >01/01/2016 12:00:20 PM Crunchie B4 $10.00 $8.50
         >01/01/2016 12:01:25 PM Cowtales B2 $8.50 $7.50
         >01/01/2016 12:01:35 PM GIVE CHANGE: $7.50 $0.00
         >```
9. Create as many of your classes as possible to be "testable" classes. Limit console
input and output to as few classes as possible.
10. Optional - Sales Report
    - Provide a "Hidden" menu option on the main menu ("4") that writes to a sales
    report that shows the total sales since the machine was started. The name of the
    file must include the date and time so each sales report is uniquely named.
    - An example of the output format is provided below.
11. Provide unit tests demonstrating that your code works correctly.

--- 

## Running the Application

### Requirements
- [JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- IDE - The program built with [IntelliJ](https://www.jetbrains.com/idea/)

### Getting Started
1. Download code and open with IDE capable of running Java programs
2. Load the Maven file in the root folder
3. To begin the application, run the VendingMachineCLI.java in an IDE.
   - The program requires at least Java 10 to be run. 
   - If running in an IDE, ensure a resource folder is included with all files in the same location they are set in this zip file


--- 

## Team Members
- Erin Czarnecki - <a href="https://www.linkedin.com/in/erin-czarnecki" target="_blank">LinkedIn</a> - <a href="https://github.com/erinczarnecki" target="_blank">GitHub</a>
- Patrick Tucker - <a href="https://www.linkedin.com/in/PatrickETucker/" target="_blank">LinkedIn</a> - <a href="https://github.com/PatrickETucker" target="_blank">GitHub</a>
