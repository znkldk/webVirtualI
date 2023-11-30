Call
=====================
Created by znkldk on 19.01.2022

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
## Create New Risk Engineer
Tags:Regression
* Create New "risk engineer" With Post Method

## Call Set Call
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Wait "5" seconds
* Answer The Call

## Call Set Call Check With Console Logs
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Answer The Call
* CAll Check Agora log for joined successfully

## Call Set Call Host Elements Control
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Call Page host Elements Control

## Call Set Call Guest Elements Control
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Answer The Call
* Call Page guest Elements Control

## Call Host Mute Video Stream
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Call set video mute

## Call Host Mute Voice Stream
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Call set voice mute

## Call Guest Mute Video Stream
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Answer The Call
* Call set video mute

## Call Guest Mute Voice Stream
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Answer The Call
* Call set voice mute

## Call Add Guest Form Control
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Open Add Guest Form
* Add Guest Check Pop Up Form

## Call Add Guest Copy Link
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Open Add Guest Form
* Call Add Partipand With Copylink
* Call Add to Call List Contains "Contact Name" else "Fail to add to Call"

## Call Add Guest Sms
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Open Add Guest Form
* Call Add Partipand With Sms
* Call Add to Call List Contains "Contact Name" else "Fail to add to Call"
* Call Add to Call List Contains "55" else "Fail to add to Call"

## Call Add Guest Mail
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Open Add Guest Form
* Call Add Partipand With Email
* Call Add to Call List Contains "Contact Name" else "Fail to add to Call"
* Call Add to Call List Contains "cdknn.test@inbox.testmail.app" else "Fail to add to Call"

## CAll Add Guest Page Close
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Open Add Guest Form
* Close Add Guest Form

## Call Message Elements Control
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Call Open Message Area
* Call Message Area Elements Control

## Call Send message Reciever
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Answer The Call
* Call Open Message Area
* Call Send a message "This is a text message!This is a text message!This is a text message!This is a text message!This is a text message!This is a text message!"
* Call open the host tab
* Wait "12" seconds
* Call Open Message Area
* Call did Message Received "This is a text message!"

## Call Send message Sender
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Answer The Call
* Call Open Message Area
* Call Send a message "This is a text message!"
* Call did Message Received "This is a text message!"

## Call Connetction Test
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Call open connection test

## Call end call Warning pop up
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Call press End Call btn end Check Pop up
* Call Cancel pop up and check it

## Call end call Agora log Checks
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Call press End Call btn end Check Pop up
* Call Ok pop up and check it
* Call check agora log for end stream

## Call host end call Shutdown tab
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Call press End Call btn end Check Pop up
* Call Ok pop up and check it
* Chrome popup enter and Close the tab

## CAll start Recort with out guest
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Call press record btn with out guest

## CAll take SS with out guest
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Call press SS btn with out guest

## Call Video Call Drive Controls
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* CAll Open video call drive

## CAll Guest ScreenShot
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Answer The Call
* Take an ScreenShot
* Call open the host tab
* Call Page Open the rigtside bar
* Check Screenshot

## CAll Host ScreenShot
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Answer The Call
* Call open the host tab
* Take an ScreenShot
* Call Page Open the rigtside bar
* Check Screenshot
* Call Delete Content

## CAll Host Video Record
Tags:Regression
* Login last created risk engineer
* Go to SetCall
* Start a Call With Copied Link
* Answer The Call
* Call open the host tab
* Take an Video Record
* Call Page Open the rigtside bar
* Check Video Record
* Call Delete Content