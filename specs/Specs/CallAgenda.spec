Call Agenda
=====================
Created by znkldk on 27.01.2022

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

## Create New Risk Engineer Call Agenda
Tags:Regression
* Create New "risk engineer" With Post Method

## Call Agenda Page Controls Day Mode
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda Check All Page Items day mode

## Call Agenda today yesterday tomorrow
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* CAll Agenda Check date in Day mode
* CAll Agenda Open nextday
* Call Agenda Check Date for Day mode add "1"
* CAll Agenda Open previous day
* CAll Agenda Open previous day
* Call Agenda Check Date for Day mode add "-1"

## Call Agenda next mounth this mounth previus mounth
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open month mode
* Call Agenda Check Date for Month mode add "0"
* CAll Agenda Open next month
* Call Agenda Check Date for Month mode add "1"
* CAll Agenda Open previous month
* CAll Agenda Open previous month
* Call Agenda Check Date for Month mode add "-1"

## Call Agenda week mode test
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open week mode
* Call Agenda check Are week date numbers proper

## Call Agenda today button Enable and Disable day mode
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* CAll Agenda Check Is today btn DISABLED
* CAll Agenda Open nextday
* CAll Agenda Check Is today btn ENABLED
* CAll Agenda Open previous day
* CAll Agenda Open previous day
* CAll Agenda Check Is today btn ENABLED

## Call Agenda today button Enable and Disable week mode
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open week mode
* CAll Agenda Check Is today btn DISABLED
* CAll Agenda Open nextweek
* CAll Agenda Check Is today btn ENABLED
* CAll Agenda Open previous week
* CAll Agenda Open previous week
* CAll Agenda Check Is today btn ENABLED

## Call Agenda today button Enable and Disable month mode
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open month mode
* Call Agenda open today
* CAll Agenda Check Is today btn DISABLED
* CAll Agenda Open next month
* CAll Agenda Check Is today btn ENABLED
* CAll Agenda Open previous month
* CAll Agenda Open previous month
* CAll Agenda Check Is today btn ENABLED

## Create New Risk Engineer Call Agenda 1
Tags:Regression
* Create New "risk engineer" With Post Method

## Call Agenda Video Call Appointment Items check day
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda Click oclock "23:00:00" in day mode
* Call Agenda Check All Items on Call Appointment

## Call Agenda Video Call Appointment Items check week
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open week mode
* Call Agenda Click oclock "23:00:00" in day mode
* Call Agenda Check All Items on Call Appointment

## Call Agenda Video Call Appointment Items check year
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open month mode
* Call Agenda Click day "today" in mounth mode
* Call Agenda Check All Items on Call Appointment

## Call Agenda create a Appointment and delete it day feature
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda Open nextweek
* CAll Agenda open Day mode
* Call Agenda Click oclock "23:00:00" in day mode
* Call Agenda click save btn
* Call Agenda click event by hours "11:00"
* Call Agenda delete Appointment
* Call Agenda if hours "11:00 - 11:30" is exist fail

## Call Agenda create a Appointment and delete it day past
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda Click oclock "01:00:00" in day mode
* Call Agenda click save btn
* Call Agenda if hours "01:00 - 01:30" is exist fail past

## Call Agenda create a Appointment and delete it week
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open week mode
* Call Agenda Click oclock "22:00:00" in day mode
* Call Agenda click save btn
* Call Agenda click event by hours "10:00"
* Call Agenda delete Appointment
* Call Agenda if hours "10:00 - 10:30" is exist fail

## Call Agenda create a Appointment and delete it next week
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open week mode
* CAll Agenda Open nextweek
* Call Agenda Click oclock "23:00:00" in day mode
* Call Agenda click save btn
* Call Agenda click event by hours "11:00"
* Call Agenda delete Appointment
* Call Agenda if hours "11:00 - 11:30" is exist fail

## Create New Risk Engineer Call Agenda 2
Tags:Regression
* Create New "risk engineer" With Post Method

## Call Agenda create a Appointment and delete it month today previus hour
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open month mode
* Call Agenda Click day "today" in mounth mode
* Call Agenda Set hour "01:00" "01:30"
* Call Agenda click save btn
* Call Agenda if event in "today" exist fail in mounth mode

## Create New Risk Engineer Call Agenda 3
Tags:Regression
* Create New "risk engineer" With Post Method

## Call Agenda create a Appointment and delete it month today feature hour
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open month mode
* Call Agenda Click day "today" in mounth mode
* Call Agenda Set hour "23:00" "23:30"
* Call Agenda click save btn
* Call Agenda Click Month "today" in mounth mode
* Call Agenda delete Appointment
* Call Agenda if event in "today" exist fail in mounth mode

## Call Agenda create a Appointment and delete it month tomorrow
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open month mode
* Call Agenda Click day "tomorrow" in mounth mode
* Call Agenda click save btn
* Call Agenda Click Month "tomorrow" in mounth mode
* Call Agenda delete Appointment
* Call Agenda if event in "tomorrow" exist fail in mounth mode

## Create New Risk Engineer Call Agenda 4
Tags:Regression
* Create New "risk engineer" With Post Method

## Call Agenda add Appointment with Change hour day mode
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda Click oclock "23:00:00" in day mode
* Call Agenda Set hour "22:45" "23:00"
* Call Agenda click save btn
* Call Agenda if hours "10:45 - 11:00" is not exist fail
* Call Agenda click event by hours "10:45 "
* Call Agenda delete Appointment

## Call Agenda add Appointment with Change hour week mode
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open week mode
* Call Agenda Click oclock "23:00:00" in day mode
* Call Agenda Set hour "22:15" "22:30"
* Call Agenda click save btn
* Call Agenda click event by hours "10:15"
* Call Agenda delete Appointment
* Call Agenda if hours "10:15 - 10:30" is exist fail

## Call Agenda add Appointment with Change hour mounth mode
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open month mode
* Call Agenda Click day "today" in mounth mode
* Call Agenda Set hour "20:15" "20:30"
* Call Agenda click save btn
* CAll Agenda open week mode
* Call Agenda click event by hours "8:15"
* Call Agenda delete Appointment
* CAll Agenda open month mode
* Call Agenda if event in "today" exist fail in mounth mode

## Call Agenda add Appointment with update hour day mode
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda Click oclock "23:00:00" in day mode
* Call Agenda Set hour "22:45" "23:00"
* Call Agenda click save btn
* Call Agenda if hours "10:45 - 11:00" is not exist fail
* Call Agenda click event by hours "10:45"
* Call Agenda Set hour "19:45" "20:00"
* Call Agenda delete Appointment

## Call Agenda add Appointment with update Subject of meeting day mode
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda Click oclock "15:00:00" in day mode
* Call Agenda Set hour "23:30" "23:45"
* Call Agenda click save btn
* Call Agenda click event by hours "11:30"
* Call Agenda set Subject Meeting "Subject"
* Call Agenda click update btn
* Call Agenda Check Appointment Sucject "Subject"

## Create New Risk Engineer Call Agenda 5
Tags:Regression
* Create New "risk engineer" With Post Method

## Call Agenda add Appointment with update hour week mode
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open week mode
* Call Agenda Click oclock "23:00:00" in day mode
* Call Agenda Set hour "22:15" "22:30"
* Call Agenda click save btn
* Call Agenda click event by hours "10:15"
* Call Agenda delete Appointment
* Call Agenda if hours "10:15 - 10:30" is exist fail

## Call Agenda add Appointment with update hour mounth mode
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open month mode
* Call Agenda Click day "today" in mounth mode
* Call Agenda Set hour "20:15" "20:30"
* Call Agenda click save btn
* CAll Agenda open week mode
* Call Agenda click event by hours "8:15"
* Call Agenda delete Appointment
* CAll Agenda open month mode
* Call Agenda if event in "today" exist fail in mounth mode

## Create New Risk Engineer Call Agenda 6
Tags:Regression
* Create New "risk engineer" With Post Method

## Call Agenda Set Call Direcly Elements Control
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda Click oclock "23:00:00" in day mode
* Call Agenda click save btn
* Call Agenda click event by hours "11:00"
* Call Agenda Open Add Contact Call Page
* Call Agenda Set Call Direcly Check Elements

## Call Agenda Set Call with Engineers Elements Control
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda click event by hours "11:00"
* Call Agenda Open Add Contact Call Page
* Call Agenda Open Set Call With Engineers page
* Call Agenda Set Call With Engineers Check Elements

## Call Agenda Set Call with Client Elements Control
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda click event by hours "11:00"
* Call Agenda Open Add Contact Call Page
* Call Agenda Open Set Call With Client page
* Call Agenda Set Call With Client Check Elements

## Call Agenda Go Start Call Page
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda click event by hours "11:00"
* CAll Agenta Open Go to call page

## Call Agenda Save Patricipant Directly
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda click event by hours "11:00"
* Call Agenda Open Add Contact Call Page
* Call Agenda Add Participant Directly "Contact name direcly" "test@test.com"
* CallAgenda Check participants list "Contact name direcly" "test@test.com"
* CallAgenda Click Edit participants list "Contact name direcly" "test@test.com"
* Call Agenda Add Participant Directly "Edited name direcly" "edited@test.com"
* CallAgenda Check participants list "Edited name direcly" "edited@test.com"
* CallAgenda delete participants list "Edited name direcly" "edited@test.com"

## Call Agenda Save Patricipant with Engineers
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda click event by hours "11:00"
* Call Agenda Open Add Contact Call Page
* Call Agenda Open Set Call With Engineers page
* Call Agenda Add Participant withEngineers "Otomasyon Test" "test@test.com"
* CallAgenda Check participants list "Otomasyon Test" "test@test.com"
* CallAgenda Click Edit participants list "Otomasyon Test" "test@test.com"
* Call Agenda Add Participant withEngineers "a" "Editedtest@test.com"
* CallAgenda Check participants list "a" "Editedtest@test.com"
* CallAgenda delete participants list "a" "Editedtest@test.com"

## Call Agenda Save Patricipant with Client
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda click event by hours "11:00"
* Call Agenda Open Add Contact Call Page
* Call Agenda Open Set Call With Client page
* Call Agenda Add Participant with Client "name" "test@test.com"
* CallAgenda Check participants list "name" "test@test.com"
* CallAgenda Click Edit participants list "name" "test@test.com"
* Call Agenda Add Participant with Client "name" "Editedtest@test.com"
* CallAgenda Check participants list "name" "Editedtest@test.com"
* CallAgenda delete participants list "name" "Editedtest@test.com"

## Call Agenda Add Participants Mail Formad
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda click event by hours "11:00"
* Call Agenda Open Add Contact Call Page
* Call Agenda Check mail formad

## Call Agenda Add Participants sms Formad
Tags:Regression
* Login last created risk engineer
* Go to "Call Agenda"
* CAll Agenda open Day mode
* Call Agenda click event by hours "11:00"
* Call Agenda Open Add Contact Call Page
* Call Agenda Check sms formad