Dashboard
=====================
Created by znkldk on 7.04.2022

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.


## Admin Engineer Detail Create New Risk Engineer DashBoard
Tags:Regression
* Create New "risk engineer" With Post Method

## Admin Dashboard Hamburger on off check
Tags:Regression
* Login with Admin Account
* Go to "Dashboard"
* Close menu field
* Open menu field

## Admin Dashboard Risk Engineers Count
Tags:Regression
* Login with Admin Account
* Go to "Dashboard"
* Dashboard Store All values
* Create New "risk engineer" With Post Method
* Go to "Risk Engineers"
* Go to "Dashboard"
* Wait "2" seconds
* DashBoard check RiskEngineer Count plus one

## Admin Dashboard Client Count
Tags:Regression
* Login with Admin Account
* Go to "Dashboard"
* Dashboard Store All values
* Go to "Clients"
* Clients Open New Client Page
* Client Open new Address window
* Client Open Location Tab
* Client Seleckt "İstanbul" on map
* Client open address tab
* Client fill Address tab
* Client Fill Name
* Client Save Client
* Go to "Dashboard"
* DashBoard check Clients Count plus "1"

## Admin Dashboard Survey Count
Tags:Regression
* Login with Admin Account
* Go to "Dashboard"
* Dashboard Store All values
* Create Risk Engineer and Assign Inspection with This "Maxi - Business"
* Go to "Clients"
* Go to "Dashboard"
* Dashboard CheckAll survey values increase "1"

## Admin Dashboard Upcoming surveys
Tags:Regression
* Create Risk Engineer and Assign Inspection with This "Maxi - Business"
* Login with Admin Account
* Go to "Dashboard"
* Dashboard Upcoming surveys check last inspection