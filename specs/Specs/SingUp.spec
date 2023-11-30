Sign Up
=====================
Created by znkldk on 7.04.2022

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

## Registiration Elements Control
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* Sing Up Check All Items

## Registiration Empty Fields Check
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* Sing Up Empty Fields Check

## Registiration SingUp without email
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* SingUp Fill All Areas Correct
* Sing Up clear area "SingUp-Email-TextBox"
* SingUp Butoon must be disable

## Registiration SingUp without Correct Password
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* SingUp Fill All Areas Correct
* Sing Up clear area "SingUp-Password2-TextBox"
* SingUp Butoon must be disable

## Registiration SingUp without Phone Number
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* SingUp Fill All Areas Correct
* Sing Up clear area "SingUp-Phone-TextBox"
* SingUp Butoon must be disable

## Registiration SingUp without companyName
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* SingUp Fill All Areas Correct
* Sing Up clear area "SingUp-companyName-TextBox"
* SingUp Butoon must be disable

## Registiration Sing Up without Diffrent passwords
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* SingUp Fill All Areas Correct
* Singup Make Correct Password area wrong
* Wait "2" seconds
* Click to "Singup-Logo-Btn"
* Sing Up if Text Not Exist Fail "Password confirmation does not match" " "

## Registiration Sing Up Illagal Password
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* SingUp Fill All Areas Correct
* Singup Fill Passwords "123"
* Wait "2" seconds
* Click to "Singup-Logo-Btn"
* Sing Up if Text Not Exist Fail "Password confirmation does not match" " "

## Registiration Sing Up Illagal Password 2
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* SingUp Fill All Areas Correct
* Singup Fill Passwords "123123123123"
* Wait "2" seconds
* Click to "Singup-Logo-Btn"
* Sing Up if Text Not Exist Fail "Password confirmation does not match" " "

## Registiration Sing Up Illagal Password 3
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* SingUp Fill All Areas Correct
* Singup Fill Passwords "123123123123A."
* Wait "2" seconds
* Click to "Singup-Logo-Btn"
* Sing Up if Text Not Exist Fail "Password confirmation does not match" " "

## Registiration Singup Email Formad
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* SingUp Fill All Areas Correct
* Sing in Try Wrong Email Addesses

## Resgistiration SingUp Success
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* SingUp Fill All Areas Correct
* Sigup fill Emaill with uniq mail
* Sigup fill Company name with uniq text
* SingUp Click SingUp Btn
* Wait "10" seconds
* Fail if "Mainpage_Menu_Items" is NOT exist fail message "Unsuccesful login!!!"
* SingUp is Name Correct

## Registiration PhoneNumber Formad Check
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* SingUp Fill All Areas Correct

## Rigistiration Back Button
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* Singup Click Already Have An Accound Btn

## Registiration Google Check
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* SingUp Open Google Login

## Registiration Apple Check
Tags:Regression
* Accept Cookie
* Sing Up Open Registiration Page
* Singup Open Apple Login

## Forgot password Reset Password
Tags:Regression
* Accept Cookie
* Forgot Password "vrsautomationtest@gmail.com"
* Forgot passwords get reset password link and open it
* Reset Password Page Check Elements
* Reset Password Set New password
* Reset Password Login with New Password

## Forgot Password Reset Password Write Confirim password wrong
Tags:Regression
* Accept Cookie
* Forgot Password "vrsautomationtest@gmail.com"
* Forgot passwords get reset password link and open it
* Reset Password Page Check Elements
* Reset Password Set New password with wrong confirm