# Foodics_Automation_task
This is a Simple Project for Aiming to do some complex automation scenarios on amazon.

## Prerequisites & Installation
1) Before starting with the project, you need to install Java on your system.
2) Use any IDE you want, I Chose Eclipse
3) Make Sure to install TestNG
4) While Initiating everything, make sure you have the correct driver. I Used Chrome Drive with the old version (114.0.5735.199)

To Avoid any confusion, refer to the dependencies below and add it to you pom.xml
```eclipse
<dependencies>
<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.11.0</version>
</dependency>
<dependency>
       <groupId>org.slf4j</groupId>
       <artifactId>slf4j-api</artifactId>
       <version>1.7.5</version>
   </dependency>
   <dependency>
       <groupId>org.slf4j</groupId>
       <artifactId>slf4j-log4j12</artifactId>
       <version>1.7.5</version>
   </dependency>
   <dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.13</version>
  <scope>test</scope>
</dependency>
  </dependencies>
```

## Important note
Because the Env used is the production of Amazon in additoin to the complexity of some steps of the required scenarios, some parts of the code may not function as hoped, so in the very least look at the
logic of the code and understand how it works.

## Code and Test Scripts
Now Let's Break down the Code, This solution was designed with POM as shown in the below image.
![Auto1](https://github.com/TarekAshraf96/FoodicsAutoTask/assets/44756402/751a0e0a-87dc-4451-9af1-74d50831e715)

### we will start with the First steps of the Scenraio, and that would be the Login

I order to be able to view the checkout page, you need an amazon account. i tested this code with my own account but i removed it before i push the project, so kindly after
cloneing the project provide your own credentials.

![Auto2](https://github.com/TarekAshraf96/FoodicsAutoTask/assets/44756402/058869c5-ee5a-4dd1-ac7c-78590a60303b)

![Auto3](https://github.com/TarekAshraf96/FoodicsAutoTask/assets/44756402/cb0aa402-d2cd-46c4-a38f-ad2d863a73d9)

### Now we will take about what each step in the test function does brifly, and as for the complex part, will discuss what they do in the next section
![Auto4](https://github.com/TarekAshraf96/FoodicsAutoTask/assets/44756402/1e8f10c5-4761-4c37-8220-4b9d2ac984cd)

### Now for the complex functions, We will start with the "Add_Cart.processProducts(targetItemPrice);"
The Scenario here is to add all products below that its cost below 15k EGP, if no product below 15k EGP move to next page after sorting from high to low based on price. after
finding all the items with less than 15K the we should add it to the cart. the challanges here is to loop through all items in the page, check their price is less than 15K.
in case no items at all applies the condition, the next page is navigated to.

The 2nd Challange is that while on the filtering page, there is no way to add item in the cart without opening it. which is an obstecle.

here is the proposed solution, We will first check if there are items lower than 15K, in case there were none, click on next page. in case there is,
open the item in a new tab. we will do this and open each item less than 15K in a new tab, and once ther are all added, we will switch between them. on each tab. we click on the
Add to cart button. after the button is clicked, switch to the next tab. on the last tab, we click on the go to cart button. here is how the code works:

![Auto5](https://github.com/TarekAshraf96/FoodicsAutoTask/assets/44756402/8e35878f-9a55-4a93-85d7-0f5856e809a6)

Below are how the processProducts Function works
![Auto6](https://github.com/TarekAshraf96/FoodicsAutoTask/assets/44756402/7620d7e7-d664-41f9-baf7-e6a271d1eccc)


### Now for the 2nd complex functions, the "Add_Cart.verifyTotalPrice();"
While on the check out page, there is a table that shows you the total price of the items you are about to but in addition to the fees, Delivery and if there was any promotions and finally the total
sum. so instead of creating mutiple functions retriving the test for each elemnt containg the prices, we made a big one that calculate and check that the price is right as expected. here is the code:

![Auto7](https://github.com/TarekAshraf96/FoodicsAutoTask/assets/44756402/dd975b0e-7dc3-43fb-adc3-dfb010199b47)

And That Will be the Quick Tour of what the Code is Doing and how it works. Thank you for your time :D
