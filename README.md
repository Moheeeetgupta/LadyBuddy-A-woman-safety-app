# LadyBuddy- A Complete women safety app with Emergency SOS Service and Hidden camera detection


<img src="https://user-images.githubusercontent.com/57634381/131633790-5c6a360e-738b-4a15-81d9-d0e7c8205794.png" align="left"
width="200" hspace="10" vspace="10">

LadyBudyy addresses the most devastating problem of human civilization till date,by an innovative technology and simulation.The app manifests mainly four features which are Emergency SOS service, Hidden camera detector manually or by the Electromagntic field detection simulation developed by us, Women's struggle and empowerment news to get empowered, Siren alarm.

LadyBuddy is available on the Google Play Store.

<p align="left">
<a href="https://play.google.com/store/apps/details?id=com.teamDroiders.ladybuddy">
    <img alt="Get it on Google Play"
        height="80"
        src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" />
       </a>  
       </p>


       
## Description
It is a complete women safety app where a girl or women has to save the phone no.s of her relatives,and whenever she is in trouble ,she can tap the power button three times and then an emrgency message with live location of her is sent to her relatives and a phone call is also made to her first relative even when the phone is locked,no need to unlock the phone and open the app.If she is in more trouble and wants to deviate the culprit then she can tap the power button 3 more times to start a siren alarm.

## Features

The android app lets you:
- Save phone numbers of your trusted relatives in your local storage
- Send Message with live location and make phone call by just tapping the power button three times
- Start Siren alarm by just tapping the power button again three times to deviate the culprit
- Stop Siren alarm by just tapping the power button again three times  
- Detect any hidden spy camera by just scanning the suspected places
- Get the tips on manual detection of spy cameras
- Read women's empowerement and struggle related news to get empowered
- Contact Us Feature 
- Share app feature 

## Brief Process Flow 

- When we tap the launcher icon,then a splash screen merged with the lotttie animations is displayed.
- After that we need to give some permissions to the app then  the home screen with 7 CardView Buttons is displayed.
- These Seven Card View buttons  include Emergency SOS ,Hidden Camera Detector, women's News ,Siren Alarm, App Tour, About Us, Share and save lives.
- When we enter into :-

1.Emergency SOS service then phone no. of relatives is saved by the help of shared preferences in the form of key value pair and when we tap on the try it button then a phone call is made by the help of implicit intent and a message is sent by the help of SMS Manager class and this message is also embedded with the live location of the user which is provided by the help of fusedlocationprovider client API.

- Now,As we also need to trigger this functionality even when the phone is locked .So, whenever the phone is in locked state then the app is in paused state therefore we can't perform any action in background .So a background service is registered inside the app so that even if the phone is in locked state some functionalities could get executed.
- Now,A broadcast receiver is registered inside the background service in order to receive the broadcast message from OS about the screen ON/OFF state to count the power button tap count.
- And a timer also gets started when we tap the power button first time and if we tap power button three times then the try it fuinctionality is executed and if tapped six times before 30 secs  then a siren alarm gets started in order to deviate the culprit and if again tapped power button 3 times then alarm will stop.

2. Hidden camera detector  then we must be knowing that all the electronic cameras emit electromagnetic radiations and these electromagnetic radiations strength in all three directions are detected by the magnetometer sensor of our phone and if its resultant is greater than a certain range then the phone starts beeping.

3.Women’s News then it shows  only women’s news screen  which is populated by the data of a news api fetched by the help of retrofit library.

4.Siren Alarm then this screen starts a siren alarm in looping by the help of media player API.

5.About Us then user can read about us and can also contact us by the help of implicit intents.

6.App tour then get the whole tour of the app.

7.Share app then they can share and save lives.

## Technologies, Libraries and Components Used
Technologies and Libraries used:
- Java 
- XML
- Shared Preferences for storing local data
- Implicit intents for sms,call and email
- FusedLocationProviderClient API for efficient user location retrieval
- Background Service 
- Broadcast Receiver
- Media player api 
- Magnetometer Sensor
- SMS Manager API
- Sensor Manager API
- Retrofit Library
- News Api 
- Airbnb’s Lottie animations 


Android Components Used:
- RecyclerView
- LinearLayout 
- GridLayout 
- ConstraintLayout 
- RelativeLayout , 
- CardView 
- Material text fields ( TextInputEditText ) 
- Explicit and implicit intents 
- Intent filters 
- ProgressBar 
- MenuBar
- FrameLayout 
- FloatingActionButton 
- TextView, Button, EditText, ImageView, 
- ScrollView 
- NestedScrollView 
- SpeedometerView(self created view by ours) 
- LottieAnimationView
- Object Animator

## Screenshots

| HomeScreen | Emergency SOS Service  | Spy Camera Detector |
|:-:|:-:|:-:|
| ![First](https://user-images.githubusercontent.com/57634381/132382299-b1df131f-29f7-4b0d-b82b-f5579b200991.jpeg) | ![Sec](https://user-images.githubusercontent.com/57634381/132382430-823a704a-0481-447d-b6c6-4cc5636efc66.jpeg) | ![Third](https://user-images.githubusercontent.com/57634381/132382545-5e5b1248-4a60-4512-b925-922e5d11cda3.jpeg)

| Manual Detection | Women's News | App Tour |
|:-:|:-:|:-:|
|![Fourth](https://user-images.githubusercontent.com/57634381/132382643-be8854ba-ad53-4681-81b0-1a73c262df05.jpeg) | ![Fifth](https://user-images.githubusercontent.com/57634381/132382751-a2d06419-bce4-4399-b2d4-1c29d4d7667e.jpeg) | ![Sixth](https://user-images.githubusercontent.com/57634381/132382829-6658351b-5b56-4e44-ad04-fc94d51b1dfa.jpeg) |

| About Us | Share App |
|:-:|:-:|
| ![Third](https://user-images.githubusercontent.com/57634381/132382911-36f99188-3ae2-4fe7-b735-6b39ee3fe363.jpeg) | ![Fourth](https://user-images.githubusercontent.com/57634381/132383019-508dc1dd-39bf-4c79-9bb6-6725f18d9864.jpeg) |

## Authors

- [@Moheeeetgupta](https://github.com/Moheeeetgupta)
- [@sakshi-123-eng](https://github.com/sakshi-123-eng)
- [@shivi7519](https://github.com/shivi7519)

