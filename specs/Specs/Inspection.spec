inspection
=====================
Created by znkldk on 11.02.2022

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

## Inspection Create New Risk Engineer 1
Tags:Regression
* Create New "risk engineer" With Post Method

## Inspection Check Calendar
Tags:Regression
* Login last created risk engineer
* Inspection Check Celendar

## Inspection Check All Elements New Inspection Window
Tags:Regression
* Login last created risk engineer
* Open New inspection window
* New inspection check All Items

## Inspection Create New Inspection mondatory fields
Tags:Regression
* Login last created risk engineer
* Open New inspection window
* New Inspection mondatory field Check

## Inspection Create New Inspection date field
Tags:Regression
* Login last created risk engineer
* Open New inspection window
* Inspection open celendar
* Survey new Inspection page Check Date Is today

## Inspection Create New Inspection
Tags:Regression
* Create New "risk engineer" With Post Method
* Login last created risk engineer
* Open New inspection window
* New Inspection create new Inspection "Maxi - Factory"
* Select Last Three Months From Calendar
* Survey Check icons before finalize By Text "Maxi "

## Inspection Edit Page
Tags:Regression
* Create Risk Engineer and Assign Inspection with This "Maxi - Factory"
* Login last created risk engineer
* Select Last Three Months From Calendar
* Survey Click "edit" icon By Text "Maxi - Factory"
* Inspection Edit page Elements Control
* Inspection close edit page

## Inspection Check All risk engineers names in New Inspection Dd
Tags:Regression
* Login last created risk engineer
* Go to "Risk Engineers"
* Store all Risk Engineers Names
* Go to "Survey/Claim Management"
* Open New inspection window
* Inspection Check All risk engineers names in New Inspection Dd

## Inspection Check All Client names in New Inspection Dd
Tags:Regression
* Login last created risk engineer
* Go to "Clients"
* Store all Clients Names
* Go to "Survey/Claim Management"
* Open New inspection window
* Inspection Check All Clients names in New Inspection Dd

## Inspection Call Page
Tags:Regression
* Create Risk Engineer and Assign Inspection with This "Maxi - Factory"
* Login last created risk engineer
* Select Last Three Months From Calendar
* Survey Click "eye" icon By Text "Maxi - "
* Inspection StartCAll Page Elements Control
* Inspection StartCall page Open Send Sms
* Inspection StartCall page Open Send email

## Inspection Call Page Start Call Directly
Tags:Regression
* Login last created risk engineer
* Select Last Three Months From Calendar
* Survey Click "eye" icon By Text "Maxi - "
* Inspection StartCAll page Start Call
* Inspection StartCall Check Call Page did open
* Call Page host Elements Control

## Inspection Call Page Start Call Sms formad
Tags:Regression
* Login last created risk engineer
* Select Last Three Months From Calendar
* Survey Click "eye" icon By Text "Maxi - "
* Inspection StartCall Check Sms Proper format

## Inspection Call Page Start Call Sms
Tags:Regression
* Login last created risk engineer
* Select Last Three Months From Calendar
* Survey Click "eye" icon By Text "Maxi - "
* Inspection StartCall page Open Send Sms
* Write "+905555555555" to "SetCall-Contact Phone-TextBox"
* Inspection StartCAll page Start Call
* Inspection StartCall Check Call Page did open
* Call Page host Elements Control

## Inspection Call Page Start Call Sms Empty
Tags:Regression
* Login last created risk engineer
* Select Last Three Months From Calendar
* Survey Click "eye" icon By Text "Maxi - "
* Inspection StartCall page Open Send Sms
* Inspection StartCAll page Start Call without wait
* Does Error Message Appear id not Fail message= "Sms field cant pass empty"

## Inspection Call Page Start Call Email formad
Tags:Regression
* Create Risk Engineer and Assign Inspection with This "Maxi - Factory"
* Login last created risk engineer
* Select Last Three Months From Calendar
* Survey Click "eye" icon By Text "Maxi - "
* Inspection StartCall page Open Send email
* Set Call Try Wrong Email Addresses

## Inspection Call Page Start Call Email Empty
Tags:Regression
* Create Risk Engineer and Assign Inspection with This "Maxi - Factory"
* Login last created risk engineer
* Select Last Three Months From Calendar
* Survey Click "eye" icon By Text "Maxi - "
* Inspection StartCall page Open Send email
* Inspection StartCAll page Start Call without wait
* Does Error Message Appear id not Fail message= "email cant pass empty"

## Inspection Call Page Start Call Email
Tags:Regression
* Login last created risk engineer
* Select Last Three Months From Calendar
* Survey Click "eye" icon By Text "Maxi - "
* Inspection StartCall page Open Send email
* Inspection Start Call Fill Email
* Inspection StartCAll page Start Call
* Inspection StartCall Check Call Page did open
* Call Page host Elements Control

## Inspection Delete Admin
Tags:Regression
* Login with Admin Account
* Open New inspection window
* New Inspection create new Inspection "Maxi - Factory" Static Risk engineer
* Survey Click "edit" icon By Text "a"
* Inspection delete inspection succesfull

## Inspection Delete
Tags:Regression
* Create Risk Engineer and Assign Inspection with This "Maxi - Factory"
* Login last created risk engineer
* Select Last Three Months From Calendar
* Survey Click "edit" icon By Text "a"
* Inspection delete inspection succesfull