**Real Estate Sort**

**Purpose:**

Sort a list of houses based on two methods: Rating and zip code average

**Implementation:**

*Sort by rating:*

The program will read each line from the "House Data.csv" excel file which contains each house's information, and "ZipRateTable.csv" excel file which contains the rating for each house based on zip code. The houses are sorted by rating, lowest price, and lowest dollar per Sq Ft respectively. Only houses are considered which follow the contraints provided below.

![](https://i.imgur.com/ypGWako.png)

*Sort by zip average:*

The program will read each line from the "House Data.csv" excel file which contains each house's information. Zip codes are sorted in numerical order with the number of homes, average home price, average home square feet, average home beds, average baths, average dollar per square feet, average days on market, average year built, and average hoa per month information. 

[**RealEstateMainFunctional.java**](https://github.com/Jcisneros92/Lambda_Expressions/blob/master/Real%20Estate%20Sort/RealEstateMainFunctional.java)

- Contains main method and performs program functions.

[**RealEstateClass.java**](https://github.com/Jcisneros92/Lambda_Expressions/blob/master/Real%20Estate%20Sort/RealEstateClass.java)

- Class for each individual house on the market with relative information.

[**ZipRateTable.java**](https://github.com/Jcisneros92/Lambda_Expressions/blob/master/Real%20Estate%20Sort/ZipRateTable.java)

- Class for each zip code which contains the associated rating.

[**HouseData.csv**](https://github.com/Jcisneros92/Lambda_Expressions/blob/master/Real%20Estate%20Sort/House%20Data.csv)

- Excel file which contains each individual house along with its relative information.

[**ZipRateTable.csv**](https://github.com/Jcisneros92/Lambda_Expressions/blob/master/Real%20Estate%20Sort/ZipRateTable.csv)

- Excel file which contains the rating associated with each zip code.
