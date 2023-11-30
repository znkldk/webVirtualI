Clients
=====================
Created by znkldk on 27.01.2022

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
## Clients Create Risk Engineer
Tags:Regression
* Create New "risk engineer" With Post Method

## Clients Add New Client
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Clients Open New Client Page
* Client Open new Address window
* Client Open Location Tab
* Client Seleckt "İstanbul" on map
* Client open address tab
* Client fill Address tab
* Client Fill Name
* Client Save Client

## Clients Add New Adress
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Client Check Filter for "rank"
* Click Btn with text on grid "Edit" ""
* Client Open new Address window
* Client Open Location Tab
* Client Seleckt "İstanbul" on map
* Client open address tab
* Client fill Address tab second
* Does Grid Has the text "ShortNameSecond"
* Client Save Client
* Click Btn with text on grid "Edit" ""
* Does Grid Has the text "ShortNameSecond"

## Clients Edit Address
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Client Check Filter for "rank"
* Click Btn with text on grid "Edit" ""
* Client Open Adres Edit window
* Write "Edited" to "Clients-ShortName-TextBox"
* Wait "1" seconds
* Does Grid Has the text "Edited"

## Clients Edit Address Mandotory fields
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Click Btn with text on grid "Edit" ""
* Client Open Adres Edit window
* Click Button With Text "Save Address"
* Check Mandotory fileds "address name.address line.country.city.district"

## Client Filter
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Client Check Filter for "name"

## Client InActive CheckBox
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Client Click Inactive Checkbox and open first client details and check it

## Client Try To Add New Client all areas blank
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Clients Open New Client Page
* Client Click Save Btn
* Does Error Message Appear id not Fail message= "Error message did not observed"

## Client Try To Add New Client just name
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Clients Open New Client Page
* Client Fill Name
* Client Click Save Btn
* Does Error Message Appear id not Fail message= "Error message did not observed"

## Client Try To Add New Client just Adress
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Clients Open New Client Page
* Client Open new Address window
* Client Open Location Tab
* Client Seleckt "İstanbul" on map
* Client open address tab
* Client fill Address tab
* Client Save Client
* Fail if "Client-BlankName-Err" is NOT exist fail message "nama cant be blank"

## Client Industry Check
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Clients Open New Client Page
* Client Industry Dropdown Control

## Client Industry Check Write to DropDown
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Clients Open New Client Page
* Client Industry Dropdown Write Control

## Client Active And DeActife Toogle Btn
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Click Btn with text on grid fist row "Edit" and store cell text "1"
* Make Client inactive
* Client Save Client
* Client open ShowInactif
* Click Btn with text on grid "Edit" "cellValue"
* Make Client active
* Client Save Client
* Client open ShowInactif
* Click Btn with text on grid "Edit" "cellValue"

## Client Close Btn
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Click Btn with text on grid fist row "Edit" and store cell text "1"
* Click Close Btn
* Click Btn with text on grid fist row "Edit" and store cell text "1"

## Cliend Adress Window IndustryType content
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Clients Open New Client Page
* Client Open new Address window
* Client New Adress Window Industy Type select "NACE"
* Fail if "AdresWind-IndustryCode-dd" is NOT exist fail message "-"
* Client New Adress Window Industy Type select "IIB"
* Fail if "AdresWind-IndustryCode-dd" is NOT exist fail message "-"
* Client New Adress Window Industy Type select "NAICS"
* Fail if "AdresWind-IndustryCode-dd" is NOT exist fail message "-"

## Cliend Adress Window Next Btn
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Clients Open New Client Page
* Client Open new Address window
* Client Click Next Btn
* Client Click Next Btn
* Client fill Address tab

## Client SAve Adress With Using location Btn
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Clients Open New Client Page
* Client Open new Address window
* Client Click Next Btn
* Client Click Next Btn
* Client Click upgrade adres with location btn
* Cleint Save Adress
* Does Grid Has the text "ShortName"

## Client Edit Try To Update New Client Name Blank
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Click Btn with text on grid "Edit" ""
* Cleat Text "Clients-Name-TextBox"
* Client Click Save Btn
* Does Error Message Appear id not Fail message= "Error message did not observed"

## Clients delete Address
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Clients Open New Client Page
* Client Open new Address window
* Client Open Location Tab
* Client Seleckt "İstanbul" on map
* Client open address tab
* Client fill Address tab
* Client Delete Address