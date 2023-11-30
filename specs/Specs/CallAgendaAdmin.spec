Call Agenda Admin
=====================
Created by znkldk on 2.03.2022

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

## Create New Risk Engineer Call Agenda Admin 1
Tags:Regression
* Create New "risk engineer" With Post Method

## Call Agenda Admin create a Appointment it day feature
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda Click oclock "23:00:00" in day mode
* Call Agenda click save btn

## Call Agenda Admin Check Created Appointment and update it
Tags:Regression
* Login with Admin Account
* Go to "Call Agenda"
* Call Agenda Admin Disable All RiskEngineers
* Click last Risk Engineer On Checkbox list
* CAll Agenda open Day mode
* Call Agenda click event by hours "11:00"
* Call Agenda Set hour "22:15" "22:30"
* Call Agenda click update btn

## Call Agenda Admin Check appointment which is updated by admin
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda click event by hours "10:30"
* Call Agenda Admin Click Save Button
* Call Agenda click event by hours "22:30"
* Call Agenda click update btn
* Call Agenda if hours "10:30" is exist fail

## Call Agenda Admin Check deleted appointment
Tags:Regression
* Login with Admin Account
* Go to "Call Agenda"
* Call Agenda Admin Disable All RiskEngineers
* Click last Risk Engineer On Checkbox list
* CAll Agenda open Day mode
* Call Agenda if hours "10:30" is exist fail

## Call Agenda Admin Create an appointment with last created risk engineer
Tags:Regression
* Login with Admin Account
* Go to "Call Agenda"
* Call Agenda Admin Disable All RiskEngineers
* Click last Risk Engineer On Checkbox list
* CAll Agenda open Day mode
* Call Agenda Click oclock "22:00:00" in day mode
* Call Agenda Select Last Risk Engineer On Caller dd
* Call Agenda click save btn
* Call Agenda click event by hours "10:00"

## Call Agenda Admin Check Appointmen Which is created By Admin
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda click event by hours "10:00"

## Call Agenda check risk engineers on checkbox list and Caller Dropdown
Tags:Regression
* Login with Admin Account
* Go to "Risk Engineers"
* Store all Risk Engineers Names
* Go to "Call Agenda"
* Call Agenda Admin Disable All RiskEngineers
* Call Agenda Check All risk Engineers names On Left Checkbox List
* Call Agenda Click oclock "21:00:00" in day mode
* Call Agenda Check All risk Engineers names On CallerDropdown