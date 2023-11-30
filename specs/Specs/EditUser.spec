Edit User
=====================
Created by znkldk on 11.04.2022

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
## Edit User Create New Risk Engineer
Tags:Regression
* Create New "risk engineer" With Post Method

## Edit User Check All Elements
Tags:Regression
* Login last created risk engineer
* Edit User Open Edit User Page
* Edit User Check All Elements

## Edit User Change Name
Tags:Regression
* Login last created risk engineer
* Edit User Open Edit User Page
* Edit User Change Name

## Edit user Change password Confirm area is blank
Tags:Regression
* Login last created risk engineer
* Edit User Open Edit User Page
* Edit User Fill Password Area "password11.A" "" ""
* Sing Up if Text Not Exist Fail "Please enter confirm password" "-"
* Sing Up if Text Not Exist Fail "Please enter your current password" "-"

## Edit user Change password current password area is blank
Tags:Regression
* Login last created risk engineer
* Edit User Open Edit User Page
* Edit User Fill Password Area "password11.A" "password11.A" ""
* Sing Up if Text Not Exist Fail "Please enter your current password" "-"

## Edit user Change password current password is wrong
Tags:Regression
* Login last created risk engineer
* Edit User Open Edit User Page
* Edit User Fill Password Area "password11.A" "password11.A" "wrongPassword"
* Does Error Message Appear id not Fail message= "Something is wrong"

## Edit user close page
Tags:Regression
* Login last created risk engineer
* Edit User Open Edit User Page
* Edit User Close page

## Edit user Change password succes
Tags:Regression
* Login last created risk engineer
* Edit User Open Edit User Page
* Edit User Fill Password Area "newPassword" "newPassword" "123123"
* Does Success Message Appear if not Fail message "Something is wrong"
* Logout
* Login UserName current Password "newPassword" not first step

## Edit User Create New Risk Engineer 2
Tags:Regression
* Login with Admin Account
* Go to "Risk Engineers"
* Open Engineer Detail
* Create new risk engineer